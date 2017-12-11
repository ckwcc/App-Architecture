package com.ckw.zfsoft.ckwapparchitecture.di;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ckw.zfsoft.ckwapparchitecture.CkwApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by ckw
 * on 2017/12/7.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
                      ActivityBindingModule.class,
                      ApplicationModule.class
})
public interface AppComponent extends AndroidInjector<CkwApplication> {

    Context getContext();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
