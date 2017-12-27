package com.ckw.zfsoft.ckwapparchitecture.home;

import com.ckw.zfsoft.ckwapparchitecture.di.FragmentScoped;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.MedalFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.HeartFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.fourthmodule.FlagFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule.CupFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.thirdmodule.DiplomaFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2017/12/11.
 */
@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HeartFragment heartFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CupFragment cupFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DiplomaFragment diplomaFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract FlagFragment flagFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MedalFragment medalFragment();
}
