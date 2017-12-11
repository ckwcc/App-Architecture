package com.ckw.zfsoft.ckwapparchitecture.login;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void takeView(LoginContract.View view) {
        mLoginView = view;
    }

    @Override
    public void dropView() {
        mLoginView = null;
    }

    @Override
    public void doLogin(String username, String userPwd) {
        Log.d("----", "doLogin: 发起网络请求："+username+";"+userPwd);
        mLoginView.showLoginSuccess("登录成功");
    }


}
