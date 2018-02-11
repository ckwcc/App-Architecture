package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.picture_processing.deal_picture.custom;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class ShowPicActivity extends AppCompatActivity {

    private int picWidth;
    private int picHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);
        setContentView(R.layout.activity_show_pic);

        picWidth = getIntent().getIntExtra(AppConstant.KEY.PIC_WIDTH, 0);
        picHeight = getIntent().getIntExtra(AppConstant.KEY.PIC_HEIGHT, 0);
        String imgPath = getIntent().getStringExtra(AppConstant.KEY.IMG_PATH);
        Uri uri = Uri.fromFile(new File(imgPath));

        SimpleDraweeView simpleDraweeView = findViewById(R.id.img);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(picWidth, picHeight))
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .setCallerContext(uri)
                .build();

        simpleDraweeView.setController(controller);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        List<CustomProcessAdapter.Type> dataSet = new ArrayList<>();
        dataSet.add(CustomProcessAdapter.Type.BlurAndGrayscale);
        dataSet.add(CustomProcessAdapter.Type.Blur);
        dataSet.add(CustomProcessAdapter.Type.Grayscale);
        dataSet.add(CustomProcessAdapter.Type.ColorFilter);
        dataSet.add(CustomProcessAdapter.Type.Mask);
        dataSet.add(CustomProcessAdapter.Type.NinePatchMask);
        dataSet.add(CustomProcessAdapter.Type.Pixel);
        dataSet.add(CustomProcessAdapter.Type.Vignette);
        //不知道为什么，Kuawahara这种会导致程序奔溃
//        dataSet.add(CustomProcessAdapter.Type.Kuawahara);
        dataSet.add(CustomProcessAdapter.Type.Brightness);
        dataSet.add(CustomProcessAdapter.Type.Swirl);
        dataSet.add(CustomProcessAdapter.Type.Sketch);
        dataSet.add(CustomProcessAdapter.Type.Invert);
        dataSet.add(CustomProcessAdapter.Type.Contrast);
        dataSet.add(CustomProcessAdapter.Type.Sepia);
        dataSet.add(CustomProcessAdapter.Type.Toon);

        recyclerView.setAdapter(new CustomProcessAdapter(this, dataSet,simpleDraweeView,uri));
    }


}
