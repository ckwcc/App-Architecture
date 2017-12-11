package com.ckw.zfsoft.ckwapparchitecture.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.MutableBoolean;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.gyf.barlibrary.ImmersionBar;

import javax.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ckw
 * on 2017/12/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private Toolbar mToolbar;

    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled()){
            initImmersionBar();
        }
        initToolbar();
        //初始化数据
        initData();
        //view与数据绑定
        initView(savedInstanceState);

        initAnimation();


    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.imm = null;
        if (mImmersionBar != null){
            mImmersionBar.destroy();
        }
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //所有的抽象方法

    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        //为了解决状态栏与布局顶部重叠
        mImmersionBar
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.colorPrimary)//这里的颜色需要与base_toolbar的xml中设置的toolbar的颜色一致
                .init();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //ToolBar相关

    private void initToolbar(){
        mToolbar =  findViewById(R.id.too_bar_id);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    /**
     * 设置头部标题
     * @param title
     */
    public void setToolBarTitle(String title) {
        if(mToolbar != null){
            TextView titleView = findViewById(R.id.common_title);
            titleView.setText(title);
        }
    }

    public void setToolBarTitle(int resId) {
        if(mToolbar != null){
            TextView titleView = findViewById(R.id.common_title);
            titleView.setText(resId);
        }
    }

    /**
     * 设置toolbar右侧的textview
     * @param subTitle
     */
    public void setToolBarSubTitle(String subTitle){
        if(mToolbar != null){
            TextView titleSubView = findViewById(R.id.common_subtitle);
            titleSubView.setText(subTitle);
        }
    }

    public void setToolBarSubTitle(int resId){
        if(mToolbar != null){
            TextView titleSubView = findViewById(R.id.common_subtitle);
            titleSubView.setText(resId);
        }
    }

    /**
     * 自定义导航图标
     *
     * @param resId 图片的资源id
     */
    protected void setNavigationIcon(int resId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(resId);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationIconClick();
                }
            });
        }
    }

    /**
     * 自定义导航栏图标
     *
     * @param drawable drawable对象
     */
    protected void setNavigationIcon(Drawable drawable) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(drawable);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationIconClick();
                }
            });
        }
    }



    /**
     * 设置toolbar的返回箭头是否显示
     *
     * @param enabled true:显示  false:不显示
     */
    protected void setDisplayHomeAsUpEnabled(boolean enabled) {
        if (mToolbar != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(enabled);
                if (enabled) {
                    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onNavigationIconClick();
                        }
                    });
                }
            }
        }
    }

    /**
     * toolbar左侧返回键点击
     */
    protected void onNavigationIconClick() {
        boolean animationEnabled = checkAnimationEnabled();
        if (animationEnabled) {
            onBackPressed();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //检查是否可以有动画
    private boolean checkAnimationEnabled() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animationEnabled();
    }

    //初始化动画
    private void initAnimation() {
        if (checkAnimationEnabled()) {
            setUpAnimation();
        }
    }

    /**
     * 设置需要转场动画---默认是true
     *
     * @return true: 需要 false: 不需要
     */
    protected boolean animationEnabled() {
        return true;
    }

    /**
     * 设置android 5.0的转场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setUpAnimation() {

        Transition transition_enter = new Slide(Gravity.RIGHT);
        transition_enter.setDuration(600);
        getWindow().setEnterTransition(transition_enter);


        Transition transition_return = new Fade();
        transition_return.setDuration(400);
        getWindow().setReturnTransition(transition_return);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //其他


    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
}
