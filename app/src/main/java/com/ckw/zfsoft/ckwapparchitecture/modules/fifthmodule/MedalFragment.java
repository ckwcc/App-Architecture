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
import android.widget.Button;

import com.ckw.zfsoft.ckwapparchitecture.MainActivity;
import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.gsy.GsyActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.IjkActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.ActivityUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.IntentUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2017/12/27.
 */
@ActivityScoped
public class MedalFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.movie)
    MovieView movieView;

    @BindView(R.id.btn_ijk)
    Button mGoIjk;

    @BindView(R.id.btn_gsy_video_player)
    Button mGoGsy;

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
        mGoIjk.setOnClickListener(this);
        mGoGsy.setOnClickListener(this);
    }


    @Override
    public void onStop() {
        movieView.pause();
        super.onStop();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_ijk:
                ActivityUtils.startActivity(getActivity(), IjkActivity.class);
                break;
            case R.id.btn_gsy_video_player:
                ActivityUtils.startActivity(getActivity(), GsyActivity.class);
                break;
        }
    }
}
