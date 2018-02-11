package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.picture_processing.deal_picture.camera1;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import java.io.File;
import java.util.List;

import jp.wasabeef.fresco.processors.BlurPostprocessor;
import jp.wasabeef.fresco.processors.ColorFilterPostprocessor;
import jp.wasabeef.fresco.processors.CombinePostProcessors;
import jp.wasabeef.fresco.processors.GrayscalePostprocessor;
import jp.wasabeef.fresco.processors.MaskPostprocessor;
import jp.wasabeef.fresco.processors.gpu.BrightnessFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.ContrastFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.InvertFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.PixelationFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.SepiaFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.SketchFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.SwirlFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.ToonFilterPostprocessor;
import jp.wasabeef.fresco.processors.gpu.VignetteFilterPostprocessor;

public class CustomProcessAdapter extends RecyclerView.Adapter<CustomProcessAdapter.ViewHolder> {

  private Context context;
  private List<Type> dataSet;
  private SimpleDraweeView mTargetView;
  private Uri mUri;

   enum Type {
    Mask,
    NinePatchMask,
    ColorFilter,
    Grayscale,
    Blur,
    Toon,
    Sepia,
    Contrast,
    Invert,
    Pixel,
    Sketch,
    Swirl,
    Brightness,
//    Kuawahara,
    Vignette,
    BlurAndGrayscale
  }

  public CustomProcessAdapter(Context context, List<Type> dataSet) {
    this.context = context;
    this.dataSet = dataSet;
  }

  public CustomProcessAdapter(Context context, List<Type> dataSet, SimpleDraweeView targetView, Uri uri) {
    this.context = context;
    this.dataSet = dataSet;
    this.mTargetView = targetView;
    mUri = uri;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(context).inflate(R.layout.layout_custom_list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, final int position) {
    final Context context = holder.itemView.getContext();
    Postprocessor processor = null;

    holder.drawee.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

    switch (dataSet.get(position)) {
      case Mask: {
        processor = new MaskPostprocessor(context, R.drawable.mask_starfish);
        holder.drawee.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        break;
      }
      case NinePatchMask: {
        processor = new MaskPostprocessor(context, R.drawable.mask_chat_right);
        holder.drawee.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE);
        break;
      }
      case ColorFilter:
        processor = new ColorFilterPostprocessor(Color.argb(80, 255, 0, 0));
        break;
      case Grayscale:
        processor = new GrayscalePostprocessor();
        break;
      case Blur:
        processor = new BlurPostprocessor(context, 25);
        break;
      case BlurAndGrayscale:
        processor = new CombinePostProcessors.Builder()
                .add(new BlurPostprocessor(context, 25))
                .add(new GrayscalePostprocessor())
                .build();
        break;
      case Toon:
        processor = new ToonFilterPostprocessor(context);
        break;
      case Sepia:
        processor = new SepiaFilterPostprocessor(context);
        break;
      case Contrast:
        processor = new ContrastFilterPostprocessor(context, 2.0f);
        break;
      case Invert:
        processor = new InvertFilterPostprocessor(context);
        break;
      case Pixel:
        processor = new PixelationFilterPostprocessor(context, 30f);
        break;
      case Sketch:
        processor = new SketchFilterPostprocessor(context);
        break;
      case Swirl:
        processor = new SwirlFilterPostprocessor(context, 0.5f, 1.0f, new PointF(0.5f, 0.5f));
        break;
      case Brightness:
        processor = new BrightnessFilterPostprocessor(context, 0.5f);
        break;
//      case Kuawahara://目前会导致代码奔溃，先不用了
//        processor = new KuawaharaFilterPostprocessor(context, 25);
//        break;
      case Vignette:
        processor = new VignetteFilterPostprocessor(context, new PointF(0.5f, 0.5f),
            new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f);
        break;
    }
    ImageRequest request = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.demo)
        .setPostprocessor(processor)
        .build();

    final PipelineDraweeController controller =
        (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setOldController(holder.drawee.getController())
            .build();
    holder.drawee.setController(controller);
    holder.title.setText(dataSet.get(position).name());


    final Postprocessor finalProcessor = processor;
    holder.drawee.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(mTargetView != null){

          ImageRequest requestTarget = ImageRequestBuilder.newBuilderWithSource(mUri)
                  .setPostprocessor(finalProcessor)
                  .build();

          final PipelineDraweeController controllerTarget =
                  (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                          .setImageRequest(requestTarget)
                          .setOldController(mTargetView.getController())
                          .setCallerContext(mUri)
                          .build();
            mTargetView.setController(controllerTarget);

        }
      }
    });
  }



  @Override public int getItemCount() {
    return dataSet.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView drawee;
    public TextView title;

    ViewHolder(View itemView) {
      super(itemView);
      drawee = (SimpleDraweeView) itemView.findViewById(R.id.image);
      title = (TextView) itemView.findViewById(R.id.title);
    }
  }
}
