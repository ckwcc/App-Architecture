package com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.ckw.zfsoft.ckwapparchitecture.modules.secondmodule.photo_view.BigPictureActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.ToastUtils;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2017/12/27.
 * 图片显示
 */
@ActivityScoped
public class CupFragment extends BaseFragment {

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

    @Inject
    public CupFragment(){
    }

    
    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_cup;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }



    @Override
    protected void operateViews(View view) {

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

    private void translateView(View view){

        if (Build.VERSION.SDK_INT  < 21) {
            ToastUtils.showShort("点击了图片");
        } else {
            Intent intent = new Intent(getActivity(), BigPictureActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(getActivity(), view, getString(R.string.transition_test));
            startActivity(intent, options.toBundle());
        }
    }
}
