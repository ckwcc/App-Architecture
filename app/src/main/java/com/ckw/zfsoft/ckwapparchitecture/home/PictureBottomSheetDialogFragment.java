package com.ckw.zfsoft.ckwapparchitecture.home;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseBottomSheetDialogFragment;

/**
 * Created by ckw
 * on 2018/1/3.
 * 从底部弹出的dialog
 */

public class PictureBottomSheetDialogFragment extends BaseBottomSheetDialogFragment implements View.OnClickListener{

    private OnViewClickListener listener;

    public static PictureBottomSheetDialogFragment newInstance() {
        PictureBottomSheetDialogFragment fragment = new PictureBottomSheetDialogFragment();
        return fragment;
    }

    @Override
    protected Dialog createBottomSheetDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet_picture_fragment, null);
        TextView tv_select_from_album = (TextView) view.findViewById(R.id.select_from_album);
        TextView tv_take_pictures = (TextView) view.findViewById(R.id.take_pictures);
        TextView tv_cancel = (TextView) view.findViewById(R.id.cancel);
        tv_select_from_album.setOnClickListener(this);
        tv_take_pictures.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        dialog.setContentView(view);
        return dialog;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (view == null || listener == null) {
            return;
        }

        int key = view.getId();
        if (key == R.id.select_from_album) {
            listener.onSelectFromAlbumClick();


        /*
         * 拍照
         */
        } else if (key == R.id.take_pictures) {
            listener.onTakePicturesClick();


        /*
         * 取消
         */
        } else if (key == R.id.cancel) {

        }

        dismiss();
    }

    /**
     * 自定义回调接口
     */
    public interface OnViewClickListener {

        /**
         * 从相册中选取
         */
        void onSelectFromAlbumClick();

        /**
         * 拍照
         */
        void onTakePicturesClick();
    }
}
