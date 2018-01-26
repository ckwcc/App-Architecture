package com.ckw.zfsoft.ckwapparchitecture.modules.fourthmodule;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.MainActivity;
import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.utils.LogUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.NetworkUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.PhoneUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by ckw
 * on 2017/12/27.
 * 获取手机信息
 */
@ActivityScoped
public class FlagFragment extends BaseFragment{

    @BindView(R.id.phone_info)
    TextView mPhoneInfo;

    private String msg;
    private String text;

    @Inject
    public FlagFragment() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_flag;
    }

    @Override
    protected void initVariables() {
//        EasyPermissions.requestPermissions(this,
//                "我要权限",
//                1, Manifest.permission.READ_PHONE_STATE
//              );
    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        initData();
    }

    @Override
    protected void initListener() {

    }

    private void initData() {
//        getAvailMemory();// 获取手机可用内存大小
//        getTotalMemory();//获取总内存大小
//        getHeightAndWidth();//获取屏幕宽高
////        getInfo();//获取IMEI号，IESI号，手机型号
//        getMacAddress();//获取IMEI号，IESI号，手机型号
//        getCpuInfo();//手机CPU信息
//        getPackage();//获取软件包名,版本名，版本号
//        isRoot();//手机是否root
//        //sim 卡运营商名称
//        String simOperatorName = PhoneUtils.getSimOperatorName();
//        String systemVersion = getSystemVersion();
//        msg = getHeightAndWidth() +
//                "\n" + getTotalMemory() +
//                "\n" + getAvailMemory() +
////                "\n" + getInfo() +
//                "\n" + getMacAddress() +
//                "\n" + getCpuInfo() +
//                "\n" + getPackage() +
//                "\n" + isRoot()
//            +"\n 运营商："+simOperatorName
//        +"\n 安卓版本:"+systemVersion ;
//
//
//        mPhoneInfo.setText(PhoneUtils.getPhoneStatus()+text);

    }

//    private  String getSystemVersion() {
//        return android.os.Build.VERSION.RELEASE;
//    }


    /**
     * 获取软件包名,版本名，版本号
     */
//    private String getPackage() {
//        try {
//            String pkName = getActivity().getPackageName();
//            String versionName = getActivity().getPackageManager().getPackageInfo(
//                    pkName, 0).versionName;
//            int versionCode = getActivity().getPackageManager()
//                    .getPackageInfo(pkName, 0).versionCode;
//            return "Package:" + pkName + "\nversionName:" + versionName + "\nversionCode:" + versionCode;
//        } catch (Exception e) {
//        }
//        return null;
//    }

    /**
     * 获取手机是否root信息
     * @return
     */
//    private String isRoot() {
//        String bool = "Root:false";
//        try {
//            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
//                bool = "Root:false";
//            } else {
//                bool = "Root:true";
//            }
//        } catch (Exception e) {
//        }
//        return bool;
//    }

    /**
     * 获取android当前可用内存大小
     */
//    private String getAvailMemory() {// 获取android当前可用内存大小
//        File path = Environment.getDataDirectory();
//        StatFs stat = new StatFs(path.getPath());
//        long blockSize = stat.getBlockSize();
//        long availableBlocks = stat.getAvailableBlocks();
//        return "当前可用内存：" + Formatter.formatFileSize(getActivity(), blockSize * availableBlocks);
//    }

    /**
     * 获得系统总内存
     */
//    private String getTotalMemory() {
//        String str1 = "/proc/meminfo";// 系统内存信息文件
//        String str2;
//        String[] arrayOfString;
//        long initial_memory = 0;
//        try {
//            FileReader localFileReader = new FileReader(str1);
//            BufferedReader localBufferedReader = new BufferedReader(
//                    localFileReader, 8192);
//            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
//
//            arrayOfString = str2.split("\\s+");
//            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
//            localBufferedReader.close();
//        } catch (IOException e) {
//        }
//        return "总内存大小：" + Formatter.formatFileSize(getContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
//    }

//    /**
//     * 获得手机屏幕宽高
//     * @return
//     */
//    public String getHeightAndWidth() {
//        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
//        int heigth = getActivity().getWindowManager().getDefaultDisplay().getHeight();
//        String str = "Width:" + width + "\nHeight:" + heigth + "";
//        return str;
//    }

    /**
     * 获取IMEI号，IESI号，手机型号
     */
//    private String getInfo() {
//        TelephonyManager mTm = (TelephonyManager) getActivity().getSystemService(TELEPHONY_SERVICE);
//
//         String imei = mTm.getDeviceId();
//        String imsi = mTm.getSubscriberId();
//        String mtype = android.os.Build.MODEL; // 手机型号
//        String mtyb= android.os.Build.BRAND;//手机品牌
//         String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
//        return "手机IMEI号："+imei+"\n手机IESI号："+imsi+"\n手机型号："+mtype+"\n手机品牌："+mtyb+"\n手机号码"+numer;
//    }
    /**
//     * 获取手机MAC地址
//     * 只有手机开启wifi才能获取到mac地址
//     */
//    private String getMacAddress(){
//        String result = "";
//        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        result = wifiInfo.getMacAddress();
//        return "手机macAdd:" + result;
//    }
//    /**
//     * 手机CPU信息
//     */
//    private String getCpuInfo() {
//        String str1 = "/proc/cpuinfo";
//        String str2 = "";
//        String[] cpuInfo = {"", ""}; //1-cpu型号 //2-cpu频率
//        String[] arrayOfString;
//        try {
//            FileReader fr = new FileReader(str1);
//            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
//            str2 = localBufferedReader.readLine();
//            arrayOfString = str2.split("\\s+");
//            for (int i = 2; i < arrayOfString.length; i++) {
//                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
//            }
//            str2 = localBufferedReader.readLine();
//            arrayOfString = str2.split("\\s+");
//            cpuInfo[1] += arrayOfString[2];
//            localBufferedReader.close();
//        } catch (IOException e) {
//        }
//        return "CPU型号:" + cpuInfo[0] + "\nCPU频率：" + cpuInfo[1];
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        TelephonyManager mTm = (TelephonyManager) getActivity().getSystemService(TELEPHONY_SERVICE);
//
//        String imei = mTm.getDeviceId();
//        String imsi = mTm.getSubscriberId();
//        String mtype = android.os.Build.MODEL; // 手机型号
//        String mtyb= android.os.Build.BRAND;//手机品牌
//        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
//        text = "\n手机IMEI号："+imei+"\n手机IESI号："+imsi+"\n手机型号："+mtype+"\n手机品牌："+mtyb+"\n手机号码"+numer;
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            showAppSettingDialog();
//        }
//    }
}
