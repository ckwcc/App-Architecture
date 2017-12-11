package com.ckw.zfsoft.ckwapparchitecture;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private FragmentManager manager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setToolbar() {
        setDisplayHomeAsUpEnabled(true);
        setToolBarTitle("测试");
        setToolBarSubTitle("嘻嘻");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
