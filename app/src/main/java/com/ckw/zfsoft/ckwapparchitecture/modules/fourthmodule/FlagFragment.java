package com.ckw.zfsoft.ckwapparchitecture.modules.fourthmodule;

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
public class FlagFragment extends BaseFragment {

    @Inject
    public FlagFragment(){

    }
    public static FlagFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FlagFragment fragment = new FlagFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_flag;
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
