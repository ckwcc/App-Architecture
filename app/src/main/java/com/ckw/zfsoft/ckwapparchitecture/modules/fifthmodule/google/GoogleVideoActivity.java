package com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.google;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.MovieView;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2018/1/25.
 */

public class GoogleVideoActivity extends BaseActivity {

    @BindView(R.id.movie)
    MovieView movieView;
    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_google_video;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean needToolbar() {
        return false;
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onStop() {
        movieView.pause();
        super.onStop();
    }
}
