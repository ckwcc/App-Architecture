package com.ckw.zfsoft.ckwapparchitecture.login;

import com.ckw.zfsoft.ckwapparchitecture.base.BasePresenter;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseView;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public class LoginContract {
    interface View extends BaseView{

        void showLoginSuccess(String msg);

        void showLoginFailure(String msg);

        void updateUserImgSuccess(String msg);

        void updateUserImgFailure(String msg);
    }

    interface Presenter extends BasePresenter<View>{

        void doLogin(String username,String userPwd);

        void updateUserImg(Map<String, RequestBody> map, MultipartBody.Part file);

    }
}
