package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.phone_inject;


import com.ckw.zfsoft.ckwapparchitecture.base.BasePresenter;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseView;

/**
 * Created by ckw
 * on 2018/1/29.
 */

public interface PhoneInfoContract {
    interface View extends BaseView{
        void showPhoneInfoResult(PhoneInfo phoneInfo);

        void showError(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getPhoneInfo(String phoneNumber,String userId);
    }
}
