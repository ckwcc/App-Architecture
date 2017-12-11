package com.ckw.zfsoft.ckwapparchitecture.login;

import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
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
    @Binds
    abstract LoginContract.Presenter loginPresenter(LoginPresenter loginPresenter);
}
