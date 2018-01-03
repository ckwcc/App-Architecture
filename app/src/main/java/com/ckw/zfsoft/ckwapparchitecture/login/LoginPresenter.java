package com.ckw.zfsoft.ckwapparchitecture.login;

import android.util.Log;

import com.ckw.zfsoft.ckwapparchitecture.NetLoader.ApiService;
import com.ckw.zfsoft.ckwapparchitecture.NetLoader.CallBackListener;
import com.ckw.zfsoft.ckwapparchitecture.NetLoader.HttpManager;
import com.ckw.zfsoft.ckwapparchitecture.common.Config;
import com.ckw.zfsoft.ckwapparchitecture.entity.User;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

        mLoginView.showLoginSuccess("登录成功");
    }

    @Override
    public void updateUserImg(Map<String, RequestBody> map, MultipartBody.Part file) {
        mHttpManager.request(mApiService.updateUserImg(map, file), mCompositeDisposable,
                mLoginView, new CallBackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        mLoginView.updateUserImgSuccess(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mLoginView.updateUserImgFailure(errorMsg);
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
