package com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule;

import android.os.Bundle;
import android.view.View;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2017/12/27.
 */
@ActivityScoped
public class MedalFragment extends BaseFragment {

    @Inject
    public MedalFragment() {
    }

    public static MedalFragment newInstance() {

        Bundle args = new Bundle();

        MedalFragment fragment = new MedalFragment();
        fragment.setArguments(args);
        return fragment;
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
    protected void initViews(View view) {

    }

    @Override
    protected void initListener() {

    }
}
