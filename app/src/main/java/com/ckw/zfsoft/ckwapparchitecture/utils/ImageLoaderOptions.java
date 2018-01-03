package com.ckw.zfsoft.ckwapparchitecture.utils;

import android.graphics.Bitmap;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @date: 2017-8-4
 * @Description: 图片加载选项---建造者模式
 */

public class ImageLoaderOptions {

    private int placeholderId = -1; //加载之前的图片---图片的资源id
    private int errorId = -1; //加载失败的图片---图片资源id
//    private DiskCacheStrategy strategy = DiskCacheStrategy.RESULT; //图片缓存策略
    private DiskCacheStrategy strategy = DiskCacheStrategy.AUTOMATIC; //图片缓存策略
    private int crossFade = 500;
    private List<Transformation<Bitmap>> bitmapTransformations;

    private ImageLoaderOptions(Builder builder) {
        this.placeholderId = builder.placeholderId;
        this.errorId = builder.errorId;
        this.strategy = builder.strategy;
        this.crossFade = builder.crossFade;
        this.bitmapTransformations = builder.bitmapTransformations;
    }


    int placeholderId() {
        return placeholderId;
    }

    int errorId() {
        return errorId;
    }


    DiskCacheStrategy strategy() {
        return strategy;
    }


    int crossFade() {
        return crossFade;
    }

    public List<Transformation<Bitmap>> bitmapTransformation() {
        return bitmapTransformations;
    }


    public static class Builder {

        private int placeholderId = -1; //加载之前的图片---图片的资源id
        private int errorId = -1; //加载失败的图片---图片资源id
//        private DiskCacheStrategy strategy = DiskCacheStrategy.RESULT; //图片缓存策略
        private DiskCacheStrategy strategy = DiskCacheStrategy.AUTOMATIC; //图片缓存策略
        private int crossFade = 500;
        private List<Transformation<Bitmap>> bitmapTransformations;

        public Builder placeholder(int placeholderId) {
            this.placeholderId = placeholderId;
            return this;
        }

        public Builder error(int errorId) {
            this.errorId = errorId;
            return this;
        }


        public Builder diskCacheStrategy(DiskCacheStrategy strategy) {
            this.strategy = strategy;
            return this;
        }


        public Builder crossFade(int crossFade) {
            this.crossFade = crossFade;
            return this;
        }

        public Builder bitmapTransform(Transformation<Bitmap>... bitmapTransformation) {
            bitmapTransformations = new ArrayList<>();
            Collections.addAll(bitmapTransformations, bitmapTransformation);
            return this;
        }

        public ImageLoaderOptions build() {
            return new ImageLoaderOptions(this);
        }
    }

}
