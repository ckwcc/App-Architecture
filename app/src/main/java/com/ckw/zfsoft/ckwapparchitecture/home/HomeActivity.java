package com.ckw.zfsoft.ckwapparchitecture.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.MedalFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.HeartFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.fourthmodule.FlagFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule.CupFragment;
import com.ckw.zfsoft.ckwapparchitecture.modules.thirdmodule.DiplomaFragment;

import java.util.ArrayList;

import butterknife.BindView;
import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public class HomeActivity extends BaseActivity implements NavigationTabBar.OnTabBarSelectedIndexListener {

    @BindView(R.id.ntb)
    NavigationTabBar mNavigationTabBar;

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
        initNavigationTabBar();
        initSaveInstanceState(savedInstanceState);
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
        return false;
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("currentIndex",mCurrentIndex);
    }

    private void initSaveInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt("currentIndex");
            mHeartFragment = (HeartFragment) mFragmentManager.findFragmentByTag(TAG_HEART_FRAGMENT);
            mCupFragment = (CupFragment) mFragmentManager.findFragmentByTag(TAG_CUP_FRAGMENT);
            mDiplomaFragment = (DiplomaFragment) mFragmentManager.findFragmentByTag(TAG_DIPLOMA_FRAGMENT);
            mFlagFragment = (FlagFragment) mFragmentManager.findFragmentByTag(TAG_FLAG_FRAGMENT);
            medalFragment = (MedalFragment) mFragmentManager.findFragmentByTag(TAG_MEDAL_FRAGMENT);
        }else {
//            mHeartFragment = HeartFragment.
        }
    }

    private void initNavigationTabBar() {
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        String[] colors = getResources().getStringArray(R.array.default_preview);

        NavigationTabBar.Model firstModel = new NavigationTabBar.Model.Builder(
                ContextCompat.getDrawable(this, R.mipmap.ic_first),
                Color.parseColor(colors[0])
        ).title("Heart")
                .badgeTitle("NTB")
                .build();

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

    @Override
    public void onStartTabSelected(NavigationTabBar.Model model, int index) {

    }

    @Override
    public void onEndTabSelected(NavigationTabBar.Model model, int index) {

    }
}
