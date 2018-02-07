package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by ckw
 * on 2018/1/26.
 */

public class PhoneService extends Service{
    /**
     * 用于在线程中创建或移除悬浮窗。
     */
    private Handler handler = new Handler();

    /**
     * 定时器，定时进行检测当前应该创建还是移除悬浮窗。
     */
    private Timer timer;
    private int mPhoneState;//0 挂断 1 来电 2 通话中 3 主动拨号

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPhoneState = intent.getIntExtra("phoneState",0);
        String userName = "";
        String userDep = "";
        if(mPhoneState == 3){//拨打电话
            userName = intent.getStringExtra("userName");
            userDep = intent.getStringExtra("userDep");
        }
        // 开启定时器，每隔0.5秒刷新一次
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(userName,userDep), 0, 500);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Service被终止的同时也停止定时器继续运行
        timer.cancel();
        timer = null;
    }

    class RefreshTask extends TimerTask {
        private String userName;
        private String userDep;
        public RefreshTask(String userName,String userDep) {
            this.userName = userName;
            this.userDep = userDep;
        }

        @Override
        public void run() {
            if(!isHome() && !PhoneWindowManager.isWindowShowing()){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        PhoneWindowManager.createBigWindow(getApplicationContext(),userName,userDep);
                    }
                });
            }
            // 当前界面是桌面，且有悬浮窗显示，则移除悬浮窗。
            if (isHome() || mPhoneState == 0) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        PhoneWindowManager.removeBigWindow(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), PhoneService.class);
                        getApplicationContext().stopService(intent);
                    }
                });
            }

        }

    }


    /**
     * 判断当前界面是否是桌面
     */
    private boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        return getHomes().contains(rti.get(0).topActivity.getPackageName());
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private List<String> getHomes() {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }


}
