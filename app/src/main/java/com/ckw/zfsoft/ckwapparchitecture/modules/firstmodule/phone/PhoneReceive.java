package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;



/**
 * Created by ckw
 * on 2018/1/29.
 */

public class PhoneReceive extends BroadcastReceiver {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

        if (intent.getAction().equals("com.ckw.CUSTOM_PHONE")) {//从自己的程序里发出的，拨打电话
            String userName = intent.getStringExtra("userName");
            String userDep = intent.getStringExtra("userDep");
            Intent phoneIntent = new Intent(context, PhoneService.class);
            phoneIntent.putExtra("userName", userName);
            phoneIntent.putExtra("userDep", userDep);
            phoneIntent.putExtra("phoneState", 3);
            context.startService(phoneIntent);
        }
        //如果是去电
        else if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            //主动拨打电话的话，会经过这里，但是我们用上面的代码，因为需要传自己的数据过来
        } else {
            //设置一个监听器
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }


    PhoneStateListener listener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //注意，方法必须写在super方法后面，否则incomingNumber无法获取到值。
            super.onCallStateChanged(state, incomingNumber);
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.d("----", "onCallStateChanged: 挂断");
                    Intent phoneIntent = new Intent(mContext,PhoneService.class);
                    //这边传过去可以接收到
                    phoneIntent.putExtra("phoneState",TelephonyManager.CALL_STATE_IDLE);
                    mContext.startService(phoneIntent);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://应该是打电话的状态，通话中
                    Log.d("----", "onCallStateChanged: 接听");
                    break;
                case TelephonyManager.CALL_STATE_RINGING://响铃，比如来电
                    //输出来电号码
                    Log.d("----", "onCallStateChanged: 来电号码："+incomingNumber);
                    Intent ringingIntent = new Intent(mContext,PhoneService.class);
                    ringingIntent.putExtra("userName","CKW来电");
                    ringingIntent.putExtra("userDep","移动校园");
                    ringingIntent.putExtra("phoneState",3);
                    mContext.startService(ringingIntent);
                    break;
            }
        }
    };
}
