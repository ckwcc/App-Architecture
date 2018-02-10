package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.photoview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.ToastUtils;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2018/1/25.
 */

public class PhotoViewActivity extends BaseActivity {

    @BindView(R.id.photo_view)
    PhotoView mShow;

    @BindView(R.id.btn_one)
    Button mOne;

    @BindView(R.id.btn_two)
    Button mTwo;

    @BindView(R.id.btn_three)
    Button mThree;

    @BindView(R.id.btn_four)
    Button mFour;

    private final Handler handler = new Handler();
    private boolean rotating = false;

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initListener() {
        mOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShow.setRotationBy(10);
            }
        });

        mTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShow.setRotationBy(-10);
            }
        });

        mThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShow.setRotationTo(0);
            }
        });

        mFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRotation();
            }
        });


        mShow.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                translateView(view);
            }
        });
    }


    @Override
    public void setToolbar() {

    }

    private void toggleRotation() {
        if (rotating) {
            handler.removeCallbacksAndMessages(null);
        } else {
            rotateLoop();
        }
        rotating = !rotating;
    }

    private void rotateLoop() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mShow.setRotationBy(1);
                rotateLoop();
            }
        }, 15);
    }

    /**
     * 使用photoView做转场动画的载体还有问题
     * @param view
     */
    private void translateView(View view){

        if (Build.VERSION.SDK_INT  < 21) {
            ToastUtils.showShort("点击了图片");
        } else {
            Intent intent = new Intent(this, BigPictureActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, view, getString(R.string.transition_test));
            startActivity(intent, options.toBundle());
        }
    }
}
