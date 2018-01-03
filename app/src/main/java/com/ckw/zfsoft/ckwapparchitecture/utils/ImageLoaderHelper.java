package com.ckw.zfsoft.ckwapparchitecture.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.ckw.zfsoft.ckwapparchitecture.R;

import java.io.File;

import com.ckw.zfsoft.ckwapparchitecture.widget.CircleTransform;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * @date: 2017/3/23
 * @Description: 图片加载的工具类
 */

public class ImageLoaderHelper {

    private ImageLoaderHelper() {

    }

    private static int id = -1;

    /**
     * 加载图片
     *
     * @param context   上下文对象
     * @param imageView 显示图片的控件
     * @param imagePath 图片路径 可能是 网络路径 也可能是本地路径
     */
    public static void loadImage(Context context, ImageView imageView, String imagePath, ImageLoaderOptions options) {

        if (context == null || imageView == null) {
            return;
        }


        RequestBuilder<Drawable> load = Glide.with(context).load(imagePath);
        if (options == null) {
            load.into(imageView);
            return;
        }

        int placeholderId = options.placeholderId();
        if (placeholderId != -1) {
//            load.placeholder(placeholderId);
        }

        int errorId = options.errorId();
        if (errorId != -1) {
//            request.error(errorId);
        }

        DiskCacheStrategy strategy = options.strategy();
//        load.diskCacheStrategy(strategy);

        int crossFade = options.crossFade();
//        request.crossFade(crossFade);

        load.into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context   上下文对象
     * @param imageView 显示图片的控件
     * @param imagePath 图片路径 可能是 网络路径 也可能是本地路径
     */
    public static void loadImage(Context context, ImageView imageView, String imagePath) {
        if (context == null || imageView == null) {
            return;
        }
        Glide.with(context)
                .load(imagePath)
//                .crossFade(500)
//                .error(R.mipmap.ic_launcher)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    /**
     * 加载图片---高斯模糊
     *
     * @param context   上下文对象
     * @param imageView 显示图片的控件
     * @param imagePath 图片路径 可能是 网络路径 也可能是本地路径
     */
    public static void loadImageWithBlur(Context context, ImageView imageView, String imagePath) {
        if (context == null || imageView == null) {
            return;
        }
        Glide.with(context)
                .load(imagePath)
//                .bitmapTransform(new BlurTransformation(context))
//                .crossFade(500)
//                .error(R.mipmap.ic_launcher)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }


    /**
     * 加载图片---圆角
     *
     * @param context   上下文对象
     * @param imageView 显示图片的控件
     * @param imagePath 图片路径 可能是 网络路径 也可能是本地路径
     */
    public static void loadImageWithRoundedCorners(Context context, ImageView imageView, String imagePath) {
        if (context == null || imageView == null) {
            return;
        }

        int radius = context.getResources().getDimensionPixelSize(R.dimen.common_margin_right);
        Glide.with(context)
                .load(imagePath)
//                .bitmapTransform(new RoundedCornersTransformation(context, radius, 0, RoundedCornersTransformation.CornerType.ALL))
//                .crossFade(500)
//                .error(R.mipmap.ic_launcher)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }


    /**
     * Glide自配置加载图片
     * create by sy on 2017/5/15
     *
     * @param context        上下文
     * @param imageView      目标图形控件
     * @param imagePath      下载链接
     * @param resID          默认图片资源id 没有则传0
     * @param centerCrop     是否居中修剪
     * @param circle         是否圆形裁剪
     * @param noAnimate      是否显示渐显加载动画
     * @param cacheTransform 缓存策略(缓存的图片是否是TransForm后的)
     */
    public static void loadConfigImage(Context context,
            ImageView imageView,
            String imagePath,
            int resID,
            boolean centerCrop,
            boolean circle,
            boolean noAnimate,
            boolean cacheTransform) {
        if (context == null || imageView == null) {
            return;
        }
        RequestBuilder<Drawable> builder;
        builder = Glide.with(context).load(imagePath);
        if (centerCrop) {
//            builder.centerCrop();
        }
        if (circle) {
//            builder.transform(new CircleTransform(context));
        }
        if (noAnimate) {
//            builder.dontAnimate();
        }
        if (resID != 0) {
//            builder.placeholder(resID);
        }
        if (cacheTransform) {
//            builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
        }

        builder.into(imageView);
    }

    /**
     * Glide默认圆形图片配置加载图片
     * create by sy on 2017/5/15
     *
     * @see #loadConfigImage  自配置
     */
    public static void loadDefConfCirCleImage(Context context,
            ImageView imageView,
            String imagePath,
            int resID) {
        loadConfigImage(context, imageView, imagePath, resID, true, true, true, true);
    }


    /**
     * 清除内存缓存---在主线程
     *
     * @param context 上下文对象
     */
    private static void clearMemory(Context context) {
        if (context == null) {
            return;
        }
        Glide.get(context).clearMemory();
    }


    /**
     * 清除磁盘缓存---在子线程
     *
     * @param context 上下文对象
     */
    private static void clearDisCache(final Context context) {
        if (context == null) {
            return;
        }

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Glide.get(context).clearDiskCache();
                e.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                    }
                });
    }


    /**
     * 获取图片的缓存文件
     *
     * @param context 上下文对象
     * @return 缓存文件
     */
    private static File getCacheFile(Context context) {
        if (context == null) {
            return null;
        }
        File cacheDirectory = context.getCacheDir();
        if (cacheDirectory == null) {
            return null;
        }
        String diskCacheName = DiskCache.Factory.DEFAULT_DISK_CACHE_DIR;
        return new File(cacheDirectory, diskCacheName);
    }


    /**
     * 清除缓存 内存缓存和磁盘缓存
     *
     * @param context 上下文对象
     */
    public static void clear(final Context context) {
        if (context == null) {
            return;
        }
        clearMemory(context);
        clearDisCache(context);
    }

    /**
     * 获取缓存图片的大小
     *
     * @param context 上下文对象
     * @return 缓存图片的大小
     */
    public static String getCacheSize(Context context) {
        return FormatUtils.getFormatSize(FileUtils.getDirLength(getCacheFile(context)));
    }
}
