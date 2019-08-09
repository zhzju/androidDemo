package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;



import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;



public class CameraActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private String FILE_PATH ;
    private Button OpenCamera;
    private ImageView CameraImage;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        getPermission();
        OpenCamera = findViewById(R.id.open_camera);
        OpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/" + SystemClock.uptimeMillis() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                File file = new File(FILE_PATH);
                // 把文件地址转换成Uri格式
                Uri uri = Uri.fromFile(file);
//                Uri uri = FileProvider.getUriForFile(CameraActivity.this, String.format("%s.provider", getPackageName()), file);
                // 设置系统相机拍摄照片完成后图片文件的存放地址
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                file = null;
                CameraActivity.this.startActivityForResult(intent, 0);
//                mLocationClient.startLocation();
            }
        });
        CameraImage = findViewById(R.id.phone_image);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setInterval(1000);
        option.setNeedAddress(true);
        //异步获取定位结果
        AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //解析定位结果
                        Toast.makeText(CameraActivity.this, amapLocation.getAddress(), Toast.LENGTH_SHORT).show();
                        OpenCamera.setText(amapLocation.getAddress());
                        Log.d("testaddress",amapLocation.getAddress());
                        mLocationClient.stopLocation();
                    }
                }
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        mLocationClient.setLocationOption(option);
        //启动定位

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0)
        {
            File file = new File(FILE_PATH);
            Uri uri = Uri.fromFile(file);
//            Glide.with(CameraActivity.this).load(uri.getEncodedPath()).into(CameraImage);
            Glide.with(CameraActivity.this).load(uri.getEncodedPath()).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    File file = new File(FILE_PATH);
                    if(file.exists())
                        file.delete();
                    file = null;
                    CameraImage.setImageDrawable(resource);
                }
            });
            Toast.makeText(this, "显示照片成功" + uri.getEncodedPath(), Toast.LENGTH_SHORT).show();
            file = null;
        }

    }
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
//            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相、定位使用权限", 1, permissions);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功打开权限
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
    }
    //用户未同意权限
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();
    }
}
