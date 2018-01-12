package com.ckw.zfsoft.ckwapparchitecture.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import pub.devrel.easypermissions.AppSettingsDialog;

/**
 * Created by ckw
 * on 2017/12/7.
 */

public abstract class BaseFragment extends DaggerFragment implements BaseView{
    protected Context mContext;

    protected ImmersionBar mImmersionBar;
    protected AppSettingsDialog mAppSettingsDialog;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        initVariables();
        handleArguments();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            return null;
        }
        return inflater.inflate(getLayoutResID(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view == null) {
            return;
        }
        mUnbinder = ButterKnife.bind(this,view);

        if(immersionEnabled()){
            initImmersionBar();
        }
        operateViews(view);
        initListener();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();

        if(mImmersionBar != null){
            mImmersionBar.destroy();
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    /**
     * 获取布局文件
     *
     * @return 布局文件的id
     */
    protected abstract int getLayoutResID();

    /**
     * 初始化变量---如list adapter FragmentManager
     */
    protected abstract void initVariables();

    /**
     * 处理Bundle
     */
    protected abstract void handleBundle(Bundle bundle);

    /**
     * 初始化控件
     *
     * @param view 布局文件的跟布局
     */
    protected abstract void operateViews(View view);

    /**
     * 初始化点击事件
     */
    protected abstract void initListener();

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    /**
     * 当前页面Fragment支持沉浸式初始化。子类可以重写返回false，设置不支持沉浸式初始化
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean immersionEnabled() {
        return false;
    }

    /**
     * 显示appSettingsDialog对话框
     */
    protected void showAppSettingDialog() {
        if (!isActive()) {
            return;
        }
        if (mAppSettingsDialog == null) {
            mAppSettingsDialog = new AppSettingsDialog
                    .Builder(this)
                    .setTitle(R.string.request_permissions)
                    .setRationale(R.string.permissions_rationale)
                    .setPositiveButton(R.string.Ok)
                    .setNegativeButton(R.string.cancel)
                    .build();
        }
        mAppSettingsDialog.show();
    }


    //处理Activity界面传递过来的参数
    private void handleArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            handleBundle(bundle);
        }
    }
}
