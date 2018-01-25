package com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.photoview.BigPictureActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.ToastUtils;
import com.github.chrisbanes.photoview.PhotoView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2017/12/27.
 * 图片显示
 */
@ActivityScoped
public class CupFragment extends BaseFragment {


    @Inject
    public CupFragment(){
    }

    
    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_cup;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }



    @Override
    protected void operateViews(View view) {

    }

    @Override
    protected void initListener() {




    }




}
