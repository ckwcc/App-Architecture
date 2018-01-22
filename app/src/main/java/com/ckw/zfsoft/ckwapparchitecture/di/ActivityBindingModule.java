package com.ckw.zfsoft.ckwapparchitecture.di;

import com.ckw.zfsoft.ckwapparchitecture.home.HomeActivity;
import com.ckw.zfsoft.ckwapparchitecture.home.HomeModule;
import com.ckw.zfsoft.ckwapparchitecture.login.LoginActivity;
import com.ckw.zfsoft.ckwapparchitecture.login.LoginModule;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.IjkActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.IjkModule;
import com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule.photo_view.BigPictureActivity;

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

    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = IjkModule.class)
    abstract IjkActivity ijkActivity();

}
