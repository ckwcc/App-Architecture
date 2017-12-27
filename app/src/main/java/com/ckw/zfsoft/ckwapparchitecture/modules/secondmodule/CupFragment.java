package com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule;

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
public class CupFragment extends BaseFragment {

    @Inject
    public CupFragment(){

    }

    public static CupFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CupFragment fragment = new CupFragment();
        fragment.setArguments(args);
        return fragment;
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
    protected void initViews(View view) {

    }

    @Override
    protected void initListener() {

    }
}
