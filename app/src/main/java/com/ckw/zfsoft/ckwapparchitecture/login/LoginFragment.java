package com.ckw.zfsoft.ckwapparchitecture.login;


import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.home.HomeActivity;
import com.ckw.zfsoft.ckwapparchitecture.home.PictureBottomSheetDialogFragment;
import com.ckw.zfsoft.ckwapparchitecture.utils.AppUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.FileUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.ImageLoaderHelper;
import com.ckw.zfsoft.ckwapparchitecture.utils.ImageUtil;
import com.ckw.zfsoft.ckwapparchitecture.utils.ImageUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.LogUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.SDCardUtils;
import com.ckw.zfsoft.ckwapparchitecture.utils.StringUtils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View,PictureBottomSheetDialogFragment.OnViewClickListener,
                                                        EasyPermissions.PermissionCallbacks{

    private static final String BOTTOM_SHEET_DIALOG_FRAGMENT = "BOTTOM_SHEET_DIALOG_FRAGMENT";
    private static final int REQUEST_CODE_CAMERA_PERMISSIONS = 2; //拍照时的请求码
    private static final int REQUEST_CODE_IMAGE_FROM_ALBUM = 3; //从相册中选取的请求码
    private static final int REQUEST_CODE_SELECT_FROM_ALBUM_PERMISSIONS = 3; //从相册中选取的权限
    private static final int REQUEST_CODE_TAKE_CAMERA = 4; //拍照
    private static final int REQUEST_CODE_CROP_IMAGE = 5; //裁剪图片的请求码
    @Inject
    public LoginFragment() {
    }

    @Inject
    LoginPresenter mPresenter;

    @BindView(R.id.iv_user)
    CircleImageView mUserImg;
    @BindView(R.id.til_user_name)
    TextInputLayout mUserName;
    @BindView(R.id.til_user_pwd)
    TextInputLayout mUserPwd;
    @BindView(R.id.tv_login)
    Button mLogin;

    private File mFile; //裁剪图片保存的位置
    private String mPackageName; //包名
    private String mAuthority = "com.ckw.zfsoft.ckwapparchitecture"+"ckwFile";
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_login;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    protected void initVariables() {
        mPackageName = AppUtils.getAppName();
    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {

    }

    @Override
    protected void initListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUserName.getEditText().getText().toString().trim();
                String userPwd = mUserPwd.getEditText().getText().toString().trim();

                mUserName.setErrorEnabled(false);
                mUserPwd.setErrorEnabled(false);
                //验证用户名和密码
                if(validateAccount(userName)&&validatePassword(userPwd)){
                    mPresenter.doLogin(userName,userPwd);
                }


            }
        });

        mUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureBottomSheetDialogFragment bottomSheetDialogFragment = PictureBottomSheetDialogFragment.newInstance();
                bottomSheetDialogFragment.setOnViewClickListener(LoginFragment.this);
                bottomSheetDialogFragment.show(getChildFragmentManager(),BOTTOM_SHEET_DIALOG_FRAGMENT);
            }
        });
    }

    @Override
    public void showLoginSuccess(String msg) {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginFailure(String msg) {

    }

    @Override
    public void updateUserImgSuccess(String msg) {
        LogUtils.d("上传头像成功");
    }

    @Override
    public void updateUserImgFailure(String msg) {
        LogUtils.d("上传头像失败");
    }


    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {


        /*
         * 从相册中选取
         */
            case REQUEST_CODE_IMAGE_FROM_ALBUM:
                if (data != null) {
                    if (!checkFileIsCreateSuccess()) {
                        return;
                    }
                    Uri uri = data.getData();
                    String path = ImageUtil.getImageAbsolutePath(getContext(), uri);
                    if (path == null) {
                        return;
                    }
                    File file = new File(path);
                    cropImage(file);
                }
                break;

        /*
         * 拍照
         */
            case REQUEST_CODE_TAKE_CAMERA:
                cropImage(mFile);
                break;

        /*
         * 裁剪图片
         */
            case REQUEST_CODE_CROP_IMAGE:
                if (mFile == null) {
                    return;
                }
                showIcon(mFile.getAbsolutePath());
                break;

            default:
                break;
        }
    }

    @Override
    public void onSelectFromAlbumClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            EasyPermissions.requestPermissions(this,
                    getResources().getString(R.string.request_permission_by_select_from_album),
                    REQUEST_CODE_SELECT_FROM_ALBUM_PERMISSIONS, Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            openActivityForPicture();
        }
    }

    @Override
    public void onTakePicturesClick() {
        EasyPermissions.requestPermissions(this,
                getResources().getString(R.string.request_permission_by_camera),
                REQUEST_CODE_CAMERA_PERMISSIONS, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
        /*
         * 拍照
         */
            case REQUEST_CODE_CAMERA_PERMISSIONS:
                if (!checkFileIsCreateSuccess()) {
                    return;
                }
                Intent intentCamera = new Intent();
                intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(getContext(), mAuthority, mFile);
                    List<ResolveInfo> resInfoList = getContext().getPackageManager().queryIntentActivities(intentCamera, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        getContext().grantUriPermission(packageName, uri,
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                } else {
                    uri = Uri.fromFile(mFile);
                }
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intentCamera, REQUEST_CODE_TAKE_CAMERA);
                break;

        /*
         * 从相册中选取
         */
            case REQUEST_CODE_SELECT_FROM_ALBUM_PERMISSIONS:
                openActivityForPicture();
                break;

            default:
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            showAppSettingDialog();
        }
    }

    private void showIcon(String path){
        ImageLoaderHelper.loadImage(getContext(),mUserImg,path);
        mPresenter.updateUserImg(buildParams(),getFilePart("ckw",mFile,mFile.getName()));
    }

    private void openActivityForPicture(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_IMAGE_FROM_ALBUM);
    }

    private boolean checkFileIsCreateSuccess(){
        mFile = createNewFile();
        return mFile != null;
    }

    private File createNewFile(){
        if (!SDCardUtils.isSDCardEnable()) {
            LogUtils.d("sd卡为空");
            return null;
        }
        String take_picture_dir = SDCardUtils.getSDCardPath() + mPackageName + "/images/";
        if (!FileUtils.createOrExistsDir(take_picture_dir)) {
            LogUtils.d("目录创建失败");
            return null;
        }
        String take_picture_image_path = take_picture_dir + System.currentTimeMillis() + ".png";
        File file = new File(take_picture_image_path);
        if (!FileUtils.createFileByDeleteOldFile(file)) {
            LogUtils.d("文件创建失败");
            return null;
        }
        return file;
    }

    /**
     * 压缩图片
     * @param cropFile
     */
    private void cropImage(File cropFile){
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            uri = FileProvider.getUriForFile(getContext(),mAuthority , cropFile);
            uri = getImageContentUri(cropFile.getAbsolutePath());//适配7.0
            Log.d("----", "cropImage: 图片网址："+uri);
        } else {
            uri = Uri.fromFile(cropFile);
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", getResources().getDimensionPixelSize(R.dimen.circle_image_view_size));
        intent.putExtra("outputY", getResources().getDimensionPixelSize(R.dimen.circle_image_view_size));
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    /**
     * 适配7.0权限方法
     * @param path
     * @return
     */
    private Uri getImageContentUri(String path){
        Cursor cursor = getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, ""+id);
        }else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, path);
            return getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
    }

    /**
     * 上传图片的参数
     * @return
     */
    private Map<String, RequestBody> buildParams(){
        Map<String,RequestBody> map = new LinkedHashMap<>();
        map.put("username",getRequestBody("ckw"));
        return map;
    }

    private RequestBody getRequestBody(String value){
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }

    private MultipartBody.Part getFilePart(String partName, File file, String fileName){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(partName, fileName, requestBody);
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (mImmersionBar == null) {
            return;
        }
        mImmersionBar.statusBarDarkFont(true);
        mImmersionBar.statusBarColor(R.color.colorWhite)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 解决切换主题时，顶部状态栏颜色变为默认的天蓝色问题
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mImmersionBar == null) {
            return;
        }
        mImmersionBar.statusBarDarkFont(true);
        mImmersionBar.statusBarColor(R.color.colorWhite)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     * @param account
     * @return
     */
    private boolean validateAccount(String account){
        if(StringUtils.isEmpty(account)){
            showError(mUserName,"用户名不能为空");
            return false;
        }
        if("ckw".equals(account)){
            return true;
        }
        return false;
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            showError(mUserPwd,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 16) {
            showError(mUserPwd,"密码长度为6-16位");
            return false;
        }

        return true;
    }


}
