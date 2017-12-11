package com.ckw.zfsoft.ckwapparchitecture;

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


}
