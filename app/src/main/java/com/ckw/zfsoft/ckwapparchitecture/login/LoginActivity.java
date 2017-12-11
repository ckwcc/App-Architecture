package com.ckw.zfsoft.ckwapparchitecture.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public class LoginActivity extends BaseActivity {

    private FragmentManager manager;

    @Inject
    LoginFragment mLoginFragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setDisplayHomeAsUpEnabled(true);
        setToolBarTitle("登录界面");
        setToolBarSubTitle("ckw");

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.rl_login_container);
        if (loginFragment == null) {
            loginFragment = mLoginFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),loginFragment,R.id.rl_login_container);

        }
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
