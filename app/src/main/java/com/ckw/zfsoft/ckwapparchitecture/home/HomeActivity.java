package com.ckw.zfsoft.ckwapparchitecture.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.eventbus.NightMessageEvent;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.MedalFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.HeartFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.fourthmodule.FlagFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule.CupFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.thirdmodule.DiplomaFragment;
import com.ckw.zfsoft.ckwapparchitecture.utils.ActivityUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.FragmentUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.LogUtils;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import devlight.io.library.ntb.NavigationTabBar;
import skin.support.SkinCompatManager;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public class HomeActivity extends BaseActivity implements NavigationTabBar.OnTabBarSelectedIndexListener {

    @BindView(R.id.ntb)
    NavigationTabBar mNavigationTabBar;

    @Inject
    HeartFragment getHeartFragment;
    @Inject
    CupFragment getCupFragment;
    @Inject
    DiplomaFragment getDiplomaFragment;
    @Inject
    FlagFragment getFlagFragment;
    @Inject
    MedalFragment getMedalFragment;

    private int mCurrentIndex = 0;
    private FragmentManager mFragmentManager;
    private static final String TAG_HEART_FRAGMENT = "HeartFragment";
    private static final String TAG_CUP_FRAGMENT = "CupFragment";
    private static final String TAG_DIPLOMA_FRAGMENT = "DiplomaFragment";
    private static final String TAG_FLAG_FRAGMENT = "FlagFragment";
    private static final String TAG_MEDAL_FRAGMENT = "MedalFragment";
    private HeartFragment mHeartFragment;
    private CupFragment mCupFragment;
    private DiplomaFragment mDiplomaFragment;
    private FlagFragment mFlagFragment;
    private MedalFragment medalFragment;



    @Override
    protected void initView(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        initNavigationTabBar(false);
        initSaveInstanceState(savedInstanceState);

        showFragment(mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initListener() {
        mNavigationTabBar.setOnTabBarSelectedIndexListener(this);
    }

    @Override
    protected boolean needToolbar() {
        return true;
    }

    @Override
    public void setToolbar() {
        setDisplayHomeAsUpEnabled(true);
        setToolBarTitle("toolbar");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("currentIndex",mCurrentIndex);
    }



    @Override
    public void onStartTabSelected(NavigationTabBar.Model model, int index) {

    }

    @Override
    public void onEndTabSelected(NavigationTabBar.Model model, int index) {
        switch (index){
            case 0:
                mCurrentIndex = 0;
                showFragment(0);
                break;
            case 1:
                mCurrentIndex = 1;
                showFragment(1);
                break;
            case 2:
                mCurrentIndex = 2;
                showFragment(2);
                break;
            case 3:
                mCurrentIndex = 3;
                showFragment(3);
                break;
            case 4:
                mCurrentIndex = 4;
                showFragment(4);
                break;
        }
    }

    private void showFragment(int currentIndex){
        hideAllFragment();
        switch (currentIndex){
            case 0:
                FragmentUtils.show(mHeartFragment);
                break;
            case 1:
                FragmentUtils.show(mCupFragment);
                break;
            case 2:
                FragmentUtils.show(mDiplomaFragment);
                break;
            case 3:
                FragmentUtils.show(mFlagFragment);
                break;
            case 4:
                FragmentUtils.show(medalFragment);
                break;
        }

    }

    private void addAllFragment(){
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        FragmentUtils.add(mFragmentManager,mHeartFragment,R.id.rl_home_container);
        FragmentUtils.add(mFragmentManager,mCupFragment,R.id.rl_home_container);
        FragmentUtils.add(mFragmentManager,mDiplomaFragment,R.id.rl_home_container);
        FragmentUtils.add(mFragmentManager,mFlagFragment,R.id.rl_home_container);
        FragmentUtils.add(mFragmentManager,medalFragment,R.id.rl_home_container);
    }

    private void hideAllFragment(){
        FragmentUtils.hide(mHeartFragment);
        FragmentUtils.hide(mCupFragment);
        FragmentUtils.hide(mDiplomaFragment);
        FragmentUtils.hide(mFlagFragment);
        FragmentUtils.hide(medalFragment);
    }

    private void initSaveInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt("currentIndex");
            mHeartFragment = (HeartFragment) FragmentUtils.findFragment(mFragmentManager,HeartFragment.class);
            mCupFragment = (CupFragment) mFragmentManager.findFragmentByTag(TAG_CUP_FRAGMENT);
            mDiplomaFragment = (DiplomaFragment) mFragmentManager.findFragmentByTag(TAG_DIPLOMA_FRAGMENT);
            mFlagFragment = (FlagFragment) mFragmentManager.findFragmentByTag(TAG_FLAG_FRAGMENT);
            medalFragment = (MedalFragment) mFragmentManager.findFragmentByTag(TAG_MEDAL_FRAGMENT);
        }else {
            mHeartFragment = getHeartFragment;
            mHeartFragment.setModeListener(modeChooseListener);
            mDiplomaFragment = getDiplomaFragment;
            mCupFragment = getCupFragment;
            mFlagFragment = getFlagFragment;
            medalFragment = getMedalFragment;
        }

        addAllFragment();
    }

    private void initNavigationTabBar(boolean isNight) {
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        String[] colors = getResources().getStringArray(R.array.default_preview);



        NavigationTabBar.Model firstModel = new NavigationTabBar.Model.Builder(
                ContextCompat.getDrawable(this, R.mipmap.ic_first),
                Color.parseColor(colors[0])
        ).title("Heart")
                .badgeTitle("NTB")
                .build();

        if(isNight){
            firstModel = new NavigationTabBar.Model.Builder(
                    ContextCompat.getDrawable(this, R.mipmap.ic_second),
                    Color.parseColor(colors[0])
            ).title("Night")
                    .badgeTitle("NTB")
                    .build();
        }

        NavigationTabBar.Model secondModel = new NavigationTabBar.Model.Builder(
                ContextCompat.getDrawable(this, R.mipmap.ic_second),
                Color.parseColor(colors[1])
        ).title("Cup")
                .badgeTitle("with")
                .build();

        NavigationTabBar.Model thirdModel = new NavigationTabBar.Model.Builder(
                ContextCompat.getDrawable(this, R.mipmap.ic_third),
                Color.parseColor(colors[2])
        ).title("Diploma")
                .badgeTitle("state")
                .build();

        NavigationTabBar.Model forthModel = new NavigationTabBar.Model.Builder(
                ContextCompat.getDrawable(this, R.mipmap.ic_fourth),
                Color.parseColor(colors[3])
        ).title("Flag")
                .badgeTitle("icon")
                .build();

        NavigationTabBar.Model fifthModel = new NavigationTabBar.Model.Builder(
                ContextCompat.getDrawable(this, R.mipmap.ic_fifth),
                Color.parseColor(colors[4])
        ).title("Medal")
                .badgeTitle("777")
                .build();

        models.add(firstModel);
        models.add(secondModel);
        models.add(thirdModel);
        models.add(forthModel);
        models.add(fifthModel);

        //需要调用showBadge方法才会使badge显示
        fifthModel.showBadge();

        mNavigationTabBar.setModels(models);
        mNavigationTabBar.setModelIndex(0);//需要在setModels方法后设置才有效
        //当被选中时才显示title
//        mNavigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        //设置是否显示badge
//        mNavigationTabBar.setIsBadged(false);
        //是否显示title
//        mNavigationTabBar.setIsTitled(true);
        //设置ntb的自定义字体
//        mNavigationTabBar.setIsBadgeUseTypeface(true);
        //badge的背景色
//        mNavigationTabBar.setBadgeBgColor(Color.RED);
        //badge的title颜色
//        mNavigationTabBar.setBadgeTitleColor(Color.WHITE);
        //设置NavigationTabBar的背景色
        mNavigationTabBar.setBgColor(getResources().getColor(R.color.colorPrimary));
        mNavigationTabBar.setBadgeSize(10);
        mNavigationTabBar.setTitleSize(12);
        //以上的设置都可以在xml中设置

    }


    private View.OnClickListener modeChooseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_night:
                    SkinCompatManager.getInstance().loadSkin("night.skin", new SkinCompatManager.SkinLoaderListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess() {
                            //这里是切换成功后的回调，可以做一些自己想要的设置
                            EventBus.getDefault().post(new NightMessageEvent(true));
                        }

                        @Override
                        public void onFailed(String s) {
                        }
                    });
                    break;
                case R.id.btn_normal:
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                    EventBus.getDefault().post(new NightMessageEvent(false));
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onNightEvent(NightMessageEvent nightMessageEvent){
        boolean night = nightMessageEvent.isNight();
        initNavigationTabBar(night);

    }
}
