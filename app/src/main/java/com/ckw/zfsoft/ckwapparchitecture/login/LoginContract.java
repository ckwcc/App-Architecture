package com.ckw.zfsoft.ckwapparchitecture.login;

import com.ckw.zfsoft.ckwapparchitecture.base.BasePresenter;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseView;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public class LoginContract {
    interface View extends BaseView{

        void showLoginSuccess(String msg);

        void showLoginFailure(String msg);
    }

    interface Presenter extends BasePresenter<View>{

        void doLogin(String username,String userPwd);

    }
}
