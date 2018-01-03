package com.ckw.zfsoft.ckwapparchitecture.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;

import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by ckw
 * on 2018/1/3.
 */

public abstract class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = "BaseBottomSheetDialogFragment";
    protected Context context;
    private ImmersionBar immersionBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
        destroyStatusBar();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = createBottomSheetDialog(savedInstanceState);
        initStatusBar(dialog);
        return dialog;
    }


    private void initStatusBar(Dialog dialog) {
        immersionBar = ImmersionBar.with(getActivity(), dialog, TAG)
                .navigationBarWithKitkatEnable(false);
        immersionBar.init();
    }


    private void destroyStatusBar() {
        if (immersionBar != null) {
            immersionBar.destroy();
            immersionBar = null;
        }
    }


    /**
     * 创建对话框
     *
     * @param savedInstanceState Bundle实例
     * @return Dialog 对象
     */
    protected abstract Dialog createBottomSheetDialog(Bundle savedInstanceState);
}
