package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ckw.zfsoft.ckwapparchitecture.MainActivity;
import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.home.HomeActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.PhoneUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2017/12/27.
 * Android的换肤功能
 */
@ActivityScoped
public class HeartFragment extends BaseFragment {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.btn_night)
    Button mNightMode;

    @BindView(R.id.btn_normal)
    Button mNormalMode;

    @BindView(R.id.btn_phone)
    Button mPhone;

    private View.OnClickListener mModeChoose;

    @Inject
    public HeartFragment(){

    }

    @Override
    public void initPresenter() {
        
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_heart;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        //资源文件
        List<Integer> images = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            images.add(R.mipmap.xxx1);
        }
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置指示器在右边，默认是中间
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    protected void initListener() {
        mNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModeChoose.onClick(v);
            }
        });

        mNormalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModeChoose.onClick(v);
            }
        });

        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 检查是否获得了权限（Android6.0运行时权限）
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.CALL_PHONE)) {
                        // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                        // 弹窗需要解释为何需要该权限，再次请求授权
                        Toast.makeText(getContext(), "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }else{
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                }else {
                    // 已经获得授权，可以打电话
                    CallPhone();
                }
            }

        });
    }

    public void setModeListener(View.OnClickListener modeListener){
        this.mModeChoose = modeListener;
    }

    private void CallPhone() {
        String number = "15129933947";
        if (TextUtils.isEmpty(number)) {
            // 提醒用户
            // 注意：在这个匿名内部类中如果用this则表示是View.OnClickListener类的对象，
            // 所以必须用MainActivity.this来指定上下文环境。

        } else {
            // 拨号：激活系统的拨号组件
            Intent intent = new Intent(); // 意图对象：动作 + 数据
            intent.setAction(Intent.ACTION_CALL); // 设置动作
            Uri data = Uri.parse("tel:" + number); // 设置数据
            intent.setData(data);
            startActivity(intent); // 激活Activity组件
        }
    }

    // 处理权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    CallPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(getContext(), "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }


}
