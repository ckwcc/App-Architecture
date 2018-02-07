package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;

/**
 * Created by ckw
 * on 2018/1/26.
 */

public class FloatWindowView extends RelativeLayout {
    /**
     * 记录大悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;


    public FloatWindowView(final Context context,String phoneName,String userDep) {
        super(context);
        //需要将根布局替换成RelativeLayout
        LayoutInflater.from(context).inflate(R.layout.window_over_flow, this);
        View view = findViewById(R.id.rl_root_view);
        TextView userName = (TextView) view.findViewById(R.id.tv_username);
        userName.setText(phoneName);
        TextView department = (TextView) view.findViewById(R.id.tv_department);
        department.setText(userDep);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        ImageView close = (ImageView) findViewById(R.id.iv_close);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击关闭悬浮窗的时候，移除所有悬浮窗，并停止Service
                PhoneWindowManager.removeBigWindow(context);
                Intent intent = new Intent(getContext(), PhoneService.class);
                context.stopService(intent);
            }
        });

    }


}
