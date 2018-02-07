package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2018/1/26.
 */

public class CallPhoneServiceActivity extends BaseActivity {

    @BindView(R.id.btn_start_service)
    Button mStart;

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_call_phone_service;
    }

    @Override
    protected void initListener() {

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
    }

    @Override
    protected boolean needToolbar() {
        return false;
    }

    @Override
    public void setToolbar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("MissingPermission")
    private void callPhone() {
        String number = "15129933947";
        if (TextUtils.isEmpty(number)) {
            // 提醒用户
            // 注意：在这个匿名内部类中如果用this则表示是View.OnClickListener类的对象，
            // 所以必须用MainActivity.this来指定上下文环境。

        } else {
            //发送广播，告知有消息去了
            //创建Intent
            Intent intent = new Intent();
            intent.setAction("com.ckw.CUSTOM_PHONE");
            intent.putExtra("userName", "ckwcc");
            intent.putExtra("userDep","移动校园");
            //发送广播
            sendBroadcast(intent);
            // 拨号：激活系统的拨号组件
            Uri uri = Uri.parse("tel:" + number);
            Intent callIntent = new Intent(Intent.ACTION_CALL, uri);//ACTION_DIAL
            PackageManager packageManager = this.getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
            boolean isIntentSafe = activities != null && activities.size() > 0;
            if (isIntentSafe) {
                startActivity(callIntent);
            }

        }
    }
}
