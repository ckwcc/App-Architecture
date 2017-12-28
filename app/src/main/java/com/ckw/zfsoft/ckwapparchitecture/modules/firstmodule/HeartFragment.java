package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2017/12/27.
 * 这个界面打算做Android的换肤功能
 */
@ActivityScoped
public class HeartFragment extends BaseFragment {

    @BindView(R.id.btn_night)
    Button mNightMode;

    @BindView(R.id.btn_normal)
    Button mNormalMode;

    private View.OnClickListener mModeChoose;

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

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initListener() {
        mNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModeChoose.onClick(v);
            }
        });

        mNormalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModeChoose.onClick(v);
            }
        });
    }

    public void setModeListener(View.OnClickListener modeListener){
        this.mModeChoose = modeListener;
    }




}
