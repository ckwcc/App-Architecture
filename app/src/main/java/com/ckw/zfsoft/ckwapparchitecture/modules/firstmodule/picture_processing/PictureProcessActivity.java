package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.picture_processing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckw
 * on 2018/2/10.
 */

public class PictureProcessActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        Fresco.initialize(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        List<PictureProcessAdapter.Type> dataSet = new ArrayList<>();
        dataSet.add(PictureProcessAdapter.Type.BlurAndGrayscale);
        dataSet.add(PictureProcessAdapter.Type.Blur);
        dataSet.add(PictureProcessAdapter.Type.Grayscale);
        dataSet.add(PictureProcessAdapter.Type.ColorFilter);
        dataSet.add(PictureProcessAdapter.Type.Mask);
        dataSet.add(PictureProcessAdapter.Type.NinePatchMask);
        dataSet.add(PictureProcessAdapter.Type.Pixel);
        dataSet.add(PictureProcessAdapter.Type.Vignette);
        dataSet.add(PictureProcessAdapter.Type.Kuawahara);
        dataSet.add(PictureProcessAdapter.Type.Brightness);
        dataSet.add(PictureProcessAdapter.Type.Swirl);
        dataSet.add(PictureProcessAdapter.Type.Sketch);
        dataSet.add(PictureProcessAdapter.Type.Invert);
        dataSet.add(PictureProcessAdapter.Type.Contrast);
        dataSet.add(PictureProcessAdapter.Type.Sepia);
        dataSet.add(PictureProcessAdapter.Type.Toon);

        recyclerView.setAdapter(new PictureProcessAdapter(this, dataSet));
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_process;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean needToolbar() {
        return false;
    }

    @Override
    public void setToolbar() {

    }
}
