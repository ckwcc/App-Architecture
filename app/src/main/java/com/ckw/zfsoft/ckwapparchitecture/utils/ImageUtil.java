package com.ckw.zfsoft.ckwapparchitecture.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;


import com.ckw.zfsoft.ckwapparchitecture.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author wesley
 * @date: 2017/3/21
 * @Description: 图片的uri转换为真实路径的工具类
 */

public class ImageUtil {

    private static final String TAG = "ImageUtil";

    private ImageUtil() {

    }

    /**
     * bitmap 转化为文件路径
     *
     * @param src     bitmap
     * @param file    要保存的位置
     * @param format  转换的格式 如: CompressFormat.JPEG
     * @param recycle 是否要回收
     * @return true:保存成功  false:保存失败
     */
    public static boolean save(Bitmap src, File file, Bitmap.CompressFormat format, boolean recycle) {
        if (isEmptyBitmap(src) || !FileUtils.createOrExistsFile(file)) {
            return false;
        }
        OutputStream os = null;
        boolean ret = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = src.compress(format, 100, os);
            if (recycle && !src.isRecycled()) {
                src.recycle();
            }
        } catch (IOException e) {
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
        return ret;
    }


    /**
     * 判断bitmap对象是否为空
     *
     * @param src 源图片
     * @return true 是 false 否
     */
    private static boolean isEmptyBitmap(Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }


    /**
     * 根据Uri获取图片绝对路径
     *
     * @param context  上下文对象
     * @param imageUri uri
     * @return 图片的绝对路径
     */
    public static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null) {
            return null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri)) {
                return imageUri.getLastPathSegment();
            }
            return getDataColumn(context, imageUri, null, null);
        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * @param screen_width 屏幕宽度
     * @param bitmap       图片
     * @return
     */
    public static int getImageHeight(Bitmap bitmap, int screen_width) {
        // 图片的宽度和高度
        int image_width = bitmap.getWidth();
        int image_height = bitmap.getHeight();
        int widget_height = screen_width * image_height / image_width;
        return widget_height;
    }

    public static String getImagePath(Context context, Uri uri) {
        String smallImagePath = null;
        if (uri != null) {
            if (Build.VERSION.SDK_INT < 19) {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = context.getContentResolver().query(uri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                smallImagePath = cursor.getString(columnIndex);
                cursor.close();
            } else {
                InputStream imInputStream = null;
                try {
                    imInputStream = context.getContentResolver().openInputStream(
                            uri);
                    Bitmap bitmap = BitmapFactory
                            .decodeStream(imInputStream);
                    smallImagePath = saveGalaryImageOnLitkat(context, bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                   if (imInputStream != null) {
                       try {
                           imInputStream.close();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }

        }
        return smallImagePath;
    }

    private static String saveGalaryImageOnLitkat(Context context, Bitmap bitmap) {
        FileOutputStream out = null;
        try {
            File cacheDir;
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED))
                cacheDir = new File(Environment.getExternalStorageDirectory(),
                        context.getResources().getString(R.string.app_name));
            else
                cacheDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (!cacheDir.exists())
                cacheDir.mkdirs();
            String filename = System.currentTimeMillis() + ".jpg";
            File file = new File(cacheDir, filename);
//            temp_path = file.getAbsoluteFile();
            // if(!file.exists())
            file.createNewFile();
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }
}
