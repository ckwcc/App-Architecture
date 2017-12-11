package com.ckw.zfsoft.ckwapparchitecture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_hello)
    TextView mHello;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setDisplayHomeAsUpEnabled(true);
        setToolBarTitle("测试");
        setToolBarSubTitle("嘻嘻");
    }

    @Override
    protected void initData() {
        mHello.setText("你好");
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

}
