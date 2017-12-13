package com.ckw.zfsoft.ckwapparchitecture;

import com.blankj.utilcode.util.Utils;
import com.ckw.zfsoft.ckwapparchitecture.di.AppComponent;
import com.ckw.zfsoft.ckwapparchitecture.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by ckw
 * on 2017/12/7.
 */

public class CkwApplication extends DaggerApplication {

    private AppComponent mAppComponent;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        mAppComponent = DaggerAppComponent.builder().application(this).build();
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //AndroidUtilCode 的初始化，具体使用参考
        //https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/README-CN.md
        Utils.init(this);
    }
}
