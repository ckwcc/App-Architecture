package com.ckw.zfsoft.ckwapparchitecture.login;

import android.util.Log;

import com.ckw.zfsoft.ckwapparchitecture.NetLoader.ApiService;
import com.ckw.zfsoft.ckwapparchitecture.NetLoader.CallBackListener;
import com.ckw.zfsoft.ckwapparchitecture.NetLoader.HttpManager;
import com.ckw.zfsoft.ckwapparchitecture.common.Config;
import com.ckw.zfsoft.ckwapparchitecture.entity.User;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private LoginContract.View mLoginView;
    private HttpManager mHttpManager;
    private ApiService mApiService;

    @Inject
    public LoginPresenter(HttpManager httpManager, ApiService apiService) {
        this.mHttpManager = httpManager;
        this.mApiService = apiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void doLogin(String username, String userPwd) {
        mHttpManager.request(mApiService.login(username,userPwd, Config.STRkEY), mCompositeDisposable, mLoginView,
                new CallBackListener<User>() {
                    @Override
                    public void onSuccess(User data) {
                        Log.d("----", "onSuccess: 请求成功");
                        mLoginView.showLoginSuccess("登录成功");
                    }

                    @Override
                    public void onError(String errorMsg) {
                        Log.d("----", "onError: 请求失败");
                        mLoginView.showLoginFailure(errorMsg);
                    }
                });

    }

    @Override
    public void takeView(LoginContract.View view) {
        mLoginView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mLoginView = null;
    }




}
