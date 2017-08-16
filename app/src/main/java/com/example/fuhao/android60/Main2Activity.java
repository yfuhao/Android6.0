package com.example.fuhao.android60;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions//在那个类里请求权限
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn2 = (Button) findViewById(R.id.btn2);






        //不知道是干啥的啊
        //判断有没有相机权限
        Main2ActivityPermissionsDispatcher.getpermissionWithCheck(this);


        //点击按钮打开相机
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到相机
                Intent intent = new Intent();
                intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
            }
        });
    }


    //解释为什么需要这个方法
    @OnShowRationale(Manifest.permission.CAMERA)
    public void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("打开相机时需要系统授权读取相机权限权限")
                .setPositiveButton("可以", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //请求权限
                        request.proceed();
                        System.out.println("可以 ");
                    }
                })
                .setNegativeButton("不可以", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //直接返回
                        request.cancel();
                        System.out.println("不可以");
                    }
                })
                .show();
    }

    //注释请求的权限后面跟着权限获取后执行的方法
    @NeedsPermission(Manifest.permission.CAMERA)
    public void getpermission() {
        //成功的时候
        System.out.println("已经有权限了");
    }

    //请求拒绝的时候调用
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDeniedForCamera() {
        new AlertDialog.Builder(this)
                .setMessage("不再询问")
                .setPositiveButton("可以", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //请求权限
                        showNeverAskForCamera();
                        System.out.println("可以 ");
                    }
                })
                .setNegativeButton("不可以", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //直接返回
                        System.out.println("不可以");
                    }
                })
                .show();
        //当sdk<23的时候，以及直接拒绝的时候
        Toast.makeText(this, "请求遭到拒绝，或者sdk<23", Toast.LENGTH_SHORT).show();
    }

    //点击不再询问的时候
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
    }

    //请求权限时的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Main2ActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
