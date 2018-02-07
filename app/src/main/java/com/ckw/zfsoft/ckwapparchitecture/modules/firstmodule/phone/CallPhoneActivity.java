package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.ActivityUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.SizeUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by ckw
 * on 2018/1/25.
 */

public class CallPhoneActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    @BindView(R.id.btn_call)
    Button mCall;

    @BindView(R.id.btn_show)
    Button mShow;

    @BindView(R.id.btn_jump)
    Button mJump;

    private static final int REQUEST_PERMISSIONS_CODE = 1;
    private AlertDialog tipDialog;

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_call_phone;
    }

    @Override
    protected void initListener() {
        mJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(CallPhoneActivity.this, CallPhoneServiceActivity.class);
            }
        });
        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(CallPhoneActivity.this,
                            Manifest.permission.CALL_PHONE)) {
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                        // 弹窗需要解释为何需要该权限，再次请求授权
                        Toast.makeText(CallPhoneActivity.this, "请授权！", Toast.LENGTH_LONG).show();
                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        requestCallPermission();
                    }

                } else {
                    callPhone();
                }
            }
        });

        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (Settings.canDrawOverlays(getApplicationContext())) {
                    } else {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        startActivity(intent);
                    }
                } else {
                }
            }
        });
    }

    private void requestCallPermission() {
        EasyPermissions.requestPermissions(this,
                getResources().getString(R.string.call_need_permissions),
                REQUEST_PERMISSIONS_CODE, Manifest.permission.CALL_PHONE);
    }

    @Override
    protected boolean needToolbar() {
        return false;
    }

    @Override
    public void setToolbar() {

    }

    @SuppressLint("NewApi")
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(getApplicationContext())) {
                callPhone();
            } else {
                showSaveDialog();
            }
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtils.showShort("授权被拒绝");

    }




    private void showSaveDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("温馨提示：");
        builder.setMessage("是否要个性化拨号界面(直接跳转到设置界面开启)");
        builder.setNegativeButton("不需要", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callPhone();
                tipDialog.dismiss();
            }
        });
        builder.setPositiveButton("需要", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (Settings.canDrawOverlays(getApplicationContext())) {
                        callPhone();
                    } else {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        startActivity(intent);
                    }
                }

                tipDialog.dismiss();

            }
        });
        tipDialog = builder.show();

    }


    @SuppressLint("MissingPermission")
    private void callPhone() {
        //发送广播，告知有消息去了
        Intent intent = new Intent();
        intent.setAction("com.ckw.CUSTOM_PHONE");
        intent.putExtra("userName", "ckwcc");
        intent.putExtra("userDep","Android Developer");
        sendBroadcast(intent);

        String phoneNumber = "15129933947";
        if (TextUtils.isEmpty(phoneNumber)) {
            // 提醒用户
            // 注意：在这个匿名内部类中如果用this则表示是View.OnClickListener类的对象，
            // 所以必须用MainActivity.this来指定上下文环境。
        } else {
            // 拨号：激活系统的拨号组件
            Uri uri = Uri.parse("tel:" + phoneNumber);
            Intent callIntent = new Intent(Intent.ACTION_CALL, uri);//ACTION_DIAL
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
            boolean isIntentSafe = activities != null && activities.size() > 0;
            if (isIntentSafe) {
                startActivity(callIntent);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}
