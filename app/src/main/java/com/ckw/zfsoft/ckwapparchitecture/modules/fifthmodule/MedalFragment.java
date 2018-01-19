package com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewTreeObserver;

import com.ckw.zfsoft.ckwapparchitecture.MainActivity;
import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2017/12/27.
 */
@ActivityScoped
public class MedalFragment extends BaseFragment {

    @BindView(R.id.movie)
    MovieView movieView;

    @Inject
    public MedalFragment() {
    }


    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_medal;
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


    @Override
    public void onStop() {
        movieView.pause();
        super.onStop();
    }
    


}
