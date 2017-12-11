package com.ckw.zfsoft.ckwapparchitecture.di;

import com.ckw.zfsoft.ckwapparchitecture.MainActivity;
import com.ckw.zfsoft.ckwapparchitecture.login.LoginActivity;
import com.ckw.zfsoft.ckwapparchitecture.login.LoginModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2017/12/7.
 * 所有的activity的绑定在这里
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();
}
