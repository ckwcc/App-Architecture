package com.ckw.zfsoft.ckwapparchitecture.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View{

    @Inject
    public LoginFragment() {
    }

    @Inject
    LoginPresenter mPresenter;

    @BindView(R.id.tv_showResult)
    TextView mShowResult;
    @BindView(R.id.tv_login)
    TextView mLogin;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_login;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doLogin("ckw","123456");
            }
        });
    }

    @Override
    public void showLoginSuccess(String msg) {
        mShowResult.setText("成功："+msg);
    }

    @Override
    public void showLoginFailure(String msg) {
        mShowResult.setText("失败："+msg);
    }


    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }
}
