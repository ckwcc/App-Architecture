package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.phone_inject.PhoneInfo;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.phone_inject.PhoneInfoContract;
import com.ckw.zfsoft.ckwapparchitecture.utils.SPUtils;


/**
 * Created by ckw
 * on 2018/1/29.
 */

public class PhoneReceive extends BroadcastReceiver implements PhoneInfoContract.View{

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
                case TelephonyManager.CALL_STATE_IDLE://没有任何状态（包括挂断）
                        Intent phoneIntent = new Intent(mContext,PhoneService.class);
                        phoneIntent.putExtra("phoneState",TelephonyManager.CALL_STATE_IDLE);
                        mContext.startService(phoneIntent);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://应该是打电话的状态，通话中（接起电话了）
                    break;
                case TelephonyManager.CALL_STATE_RINGING://响铃，比如来电
                   //发起网络请求

                    break;
            }
        }
    };


    @Override
    public void showPhoneInfoResult(PhoneInfo info) {
        //接听电话，在这里开启service，目前没有网络接口，还不不需要
        Intent ringingIntent = new Intent(mContext,PhoneService.class);
                    ringingIntent.putExtra("userName",info.getXm());
                    ringingIntent.putExtra("userDep",info.getDepname());
        ringingIntent.putExtra("phoneState",TelephonyManager.CALL_STATE_RINGING);
        mContext.startService(ringingIntent);

    }


    @Override
    public void showError(String msg) {
        Log.d("----", "showError: "+msg);
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
