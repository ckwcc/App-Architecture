package com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.Window;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.media.AndroidMediaController;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.media.IjkVideoView;
import com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule.ijk.media.Settings;

import java.io.FileDescriptor;
import java.io.IOException;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by ckw
 * on 2018/1/22.
 */

public class IjkActivity extends BaseActivity {


    @BindView(R.id.ijk_video_view)
    IjkVideoView mVideoView;


    private AndroidMediaController mMediaController;
    @Override
    protected void initView(Bundle savedInstanceState) {
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mMediaController = new AndroidMediaController(this, true);
        ActionBar supportActionBar = getSupportActionBar();
        mMediaController.setSupportActionBar(supportActionBar);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid_bigbuckbunny));
        mVideoView.start();

    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ijk;
    }

    @Override
    protected void initListener() {

    }


    @Override
    public void setToolbar() {
        setToolBarTitle("Ijk播放");
        setToolBarSubTitle("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
