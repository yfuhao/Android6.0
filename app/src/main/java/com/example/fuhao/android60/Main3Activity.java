package com.example.fuhao.android60;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Process;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        PackageManager pm = getPackageManager();
        boolean permission = PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.CAMERA", "com.example.fuhao.android60");
        if (!permission) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("打开相机时需要系统授权读取相机权限权限");
            dialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //动态请求权限
                            ActivityCompat.requestPermissions(Main3Activity.this, new String[]{"android.permission.CAMERA"}, 101);
                        }
                    });
            dialog.show();
            System.out.println("第一次请求相机权限");
        }

        //适配小米机型
        AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int checkOp = appOpsManager.checkOp(AppOpsManager.OPSTR_CAMERA, Process.myUid(), getPackageName());
        if (checkOp == AppOpsManager.MODE_IGNORED) {
            //直接跳转到设置页面设置权限
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("提示");
            dialog.setMessage("当前应用缺少相机权限。");
            dialog.setMessage("请点击“设置”-“权限”-打开所需权限");
            dialog.setPositiveButton("设置",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //跳转到设置权限的界面
                            Toast.makeText(Main3Activity.this, "点击了设置", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //动态请求权限
                    finish();
                }
            }).show();

        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                for (int i = 0; i < permissions.length; i++) {
                    System.out.println("101权限= " + permissions[i] + grantResults[i]);
                    if (permissions[i].equals("android.permission.CAMERA")) {
                        if (grantResults[i] == -1) {
                            System.out.println("没有相机权限101");
                        } else {
                            System.out.println("有相机权限101");
                            Intent intent = new Intent();
                            intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                            startActivity(intent);
                        }
                    }
                    if (permissions[i].equals("android.permission.CALL_PHONE")) {
                        if (grantResults[i] == -1) {
                            System.out.println("没有联系人权限101");
                        } else {
                            System.out.println("有联系人权限101");
                        }
                    }
                }
                break;
            case 102:
                for (int i = 0; i < permissions.length; i++) {
                    System.out.println("102权限= " + permissions[i] + grantResults[i]);
                    if (permissions[i].equals("android.permission.CAMERA")) {
                        if (grantResults[i] == -1) {
                            System.out.println("没有相机权限102");
                        } else {
                            System.out.println("有相机权限102");
                            Intent intent = new Intent();
                            intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                            startActivity(intent);
                        }
                    }
                    if (permissions[i].equals("android.permission.CALL_PHONE")) {
                        if (grantResults[i] == -1) {
                            System.out.println("没有联系人权限102");
                        } else {
                            System.out.println("有联系人权限102");
                        }
                    }
                }
                break;
        }
    }
}
