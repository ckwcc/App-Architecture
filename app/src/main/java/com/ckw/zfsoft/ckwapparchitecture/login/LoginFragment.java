package com.ckw.zfsoft.ckwapparchitecture.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.home.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View{

    @Inject
    public LoginFragment() {
    }

    @Inject
    LoginPresenter mPresenter;

    @BindView(R.id.et_user_name)
    EditText mUserName;
    @BindView(R.id.et_user_pwd)
    EditText mUserPwd;
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
                String userName = mUserName.getText().toString().trim();
                String userPwd = mUserPwd.getText().toString().trim();
                mPresenter.doLogin(userName,userPwd);
            }
        });
    }

    @Override
    public void showLoginSuccess(String msg) {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginFailure(String msg) {

    }


    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }
}
