package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.picture_processing.deal_picture;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseActivity;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.picture_processing.deal_picture.custom.AppConstant;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.picture_processing.deal_picture.custom.CameraUtil;
import com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.picture_processing.deal_picture.custom.ShowPicActivity;
import com.ckw.zfsoft.ckwapparchitecture.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by ckw
 * on 2018/2/10.
 */

public class DealPictureActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    private static final int REQUEST_CODE_CAMERA_PERMISSIONS = 2; //拍照时的请求码

    @BindView(R.id.btn_picture)
    Button mTakePicture;

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deal_picture;
    }

    @Override
    protected void initListener() {
        mTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPermissions.requestPermissions(DealPictureActivity.this,
                        getResources().getString(R.string.request_permission_by_camera),
                        REQUEST_CODE_CAMERA_PERMISSIONS, Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
          CameraUtil.getInstance().camera(DealPictureActivity.this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            ToastUtils.showShort("权限被拒绝");
        }
    }

    @Override
    public void setToolbar() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != AppConstant.RESULT_CODE.RESULT_OK){
            return;
        }

        if(requestCode == AppConstant.REQUEST_CODE.CAMERA){
            String img_path = data.getStringExtra(AppConstant.KEY.IMG_PATH);

            int picWidth = data.getIntExtra(AppConstant.KEY.PIC_WIDTH, 0);
            int picHeight = data.getIntExtra(AppConstant.KEY.PIC_HEIGHT, 0);
            /*
                img.setLayoutParams(new RelativeLayout.LayoutParams(picWidth, picHeight));
                img.setImageURI(Uri.parse(img_path));
            */
            Intent intent = new Intent(this, ShowPicActivity.class);
            intent.putExtra(AppConstant.KEY.PIC_WIDTH, picWidth);
            intent.putExtra(AppConstant.KEY.PIC_HEIGHT, picHeight);
            intent.putExtra(AppConstant.KEY.IMG_PATH, img_path);
            startActivity(intent);
        }
    }


}
