package com.ckw.zfsoft.ckwapparchitecture.di;

import com.ckw.zfsoft.ckwapparchitecture.home.HomeActivity;
import com.ckw.zfsoft.ckwapparchitecture.home.HomeModule;
import com.ckw.zfsoft.ckwapparchitecture.login.LoginActivity;
import com.ckw.zfsoft.ckwapparchitecture.login.LoginModule;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.google.GoogleVideoActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.google.GoogleVideoModule;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.gsy.GsyActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.gsy.GsyModule;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.IjkActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.IjkModule;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.CallPhoneActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.CallPhoneModule;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.photoview.PhotoViewActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.photoview.PhotoViewModule;

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

    @ActivityScoped
    @ContributesAndroidInjector(modules = GsyModule.class)
    abstract GsyActivity gsyActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PhotoViewModule.class)
    abstract PhotoViewActivity photoViewActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = GoogleVideoModule.class)
    abstract GoogleVideoActivity googleVideoActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = CallPhoneModule.class)
    abstract CallPhoneActivity callPhoneActivity();
}
