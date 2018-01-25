package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.eventbus.NightMessageEvent;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.CallPhoneActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.photoview.PhotoViewActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.thirdmodule.DiplomaAdapter;
import com.ckw.zfsoft.ckwapparchitecture.utils.ActivityUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.SizeUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;
import skin.support.SkinCompatManager;

/**
 * Created by ckw
 * on 2017/12/27.
 * Android的换肤功能
 */
@ActivityScoped
public class HeartFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.easy_recycler)
    EasyRecyclerView mEasyRecyclerView;

    private DiplomaAdapter mAdapter;
    private List<String> mData;


    @Inject
    public HeartFragment(){

    }

    @Override
    public void initPresenter() {
        
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_heart;
    }

    @Override
    protected void initVariables() {
        initEasyRecyclerViewData();
    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        //资源文件
        List<Integer> images = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            images.add(R.mipmap.xxx1);
        }
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置指示器在右边，默认是中间
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        SpaceDecoration itemDecoration = new SpaceDecoration((int) SizeUtils.dp2px(8));//参数是距离宽度
        itemDecoration.setPaddingEdgeSide(true);//是否为左右2边添加padding.默认true.
        itemDecoration.setPaddingStart(true);//是否在给第一行的item添加上padding(不包含header).默认true.
        itemDecoration.setPaddingHeaderFooter(false);//是否对Header与Footer有效,默认false.
        mEasyRecyclerView.addItemDecoration(itemDecoration);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(2));
        mEasyRecyclerView.setVerticalScrollBarEnabled(false);
        //去除recycleView滑动到边界时的阴影效果
        mEasyRecyclerView.getRecyclerView().setOverScrollMode(View.OVER_SCROLL_NEVER);
        mEasyRecyclerView.setLayoutManager(gridLayoutManager);
        mEasyRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        SkinCompatManager.getInstance().loadSkin("night.skin", new SkinCompatManager.SkinLoaderListener() {
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onSuccess() {
                                //这里是切换成功后的回调，可以做一些自己想要的设置
                                EventBus.getDefault().post(new NightMessageEvent(true));
                            }

                            @Override
                            public void onFailed(String s) {
                            }
                        });
                        break;
                    case 1:
                        SkinCompatManager.getInstance().restoreDefaultTheme();
                        EventBus.getDefault().post(new NightMessageEvent(false));
                        break;

                    case 2:
                        ActivityUtils.startActivity(getActivity(), CallPhoneActivity.class);
                        break;

                    case 3:
                        ActivityUtils.startActivity(getActivity(), PhotoViewActivity.class);
                        break;


                    default:
                        ToastUtils.showShort("position:"+position);
                        break;
                }
            }
        });


    }


    private void initEasyRecyclerViewData(){
        mData = new ArrayList<>();

        mData.add("夜间模式");
        mData.add("默认模式");
        mData.add("拨打电话");
        mData.add("PhotoView");
        for (int i = 0; i < 6; i++) {
            mData.add("未完待续");
        }
        mAdapter = new DiplomaAdapter(getContext());
        mAdapter.addAll(mData);
    }



}
