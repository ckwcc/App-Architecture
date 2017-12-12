package com.ckw.zfsoft.ckwapparchitecture.login;

import com.ckw.zfsoft.ckwapparchitecture.NetLoader.HttpManager;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.di.ApiService;
import com.ckw.zfsoft.ckwapparchitecture.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2017/12/11.
 * {@link LoginPresenter}
 */
@Module
public abstract class LoginModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ActivityScoped
    @Provides
    LoginContract.Presenter loginPresenter(HttpManager httpManager, ApiService apiService){
        LoginPresenter loginPresenter = new LoginPresenter(httpManager,apiService);
        return loginPresenter;
    }
}
