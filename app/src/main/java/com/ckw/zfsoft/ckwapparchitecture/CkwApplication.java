package com.ckw.zfsoft.ckwapparchitecture;

import android.content.Context;
//import android.support.multidex.MultiDex;

import com.ckw.zfsoft.ckwapparchitecture.di.AppComponent;
import com.ckw.zfsoft.ckwapparchitecture.di.DaggerAppComponent;
import com.ckw.zfsoft.ckwapparchitecture.utils.Utils;
import com.ckw.zfsoft.ckwapparchitecture.utils.subutils.SPUtils;
import com.squareup.leakcanary.LeakCanary;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import skin.support.SkinCompatManager;

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
        //AndroidUtilCode 的初始化，具体使用参考  已将代码移植到utils包下
        //https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/README-CN.md
        Utils.init(this);

        //主题换肤 目前不支持状态栏换肤
        SkinCompatManager.withoutActivity(this).loadSkin();
        SPUtils.init(this);

        //检测内存泄漏的工具
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }else {
            LeakCanary.install(this);
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
