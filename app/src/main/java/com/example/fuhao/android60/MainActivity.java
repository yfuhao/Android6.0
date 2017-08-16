package com.example.fuhao.android60;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Main2Activity.class));


//        Button btn = (Button) findViewById(R.id.btn);
//        if (Build.VERSION.SDK_INT >= 23) {
//            System.out.println("大于等于23");
//        } else {
//            System.out.println("小于23");
//        }
//
//        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
//            //startGetImageThread();
//        }
//
//        PackageManager pm = getPackageManager();
//        boolean permission = PackageManager.PERMISSION_GRANTED ==
//                pm.checkPermission("android.permission.CAMERA", "com.example.fuhao.android60");
//        if (permission) {
//            System.out.println("有这个权限");
//        } else {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//            dialog.setTitle("打开相机时需要系统授权读取相机权限权限");
//            dialog.setPositiveButton("确定",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //动态请求权限
//                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 10086);
//                        }
//                    });
//            System.out.println("没有这个权限");
//        }
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
//
//    //动态请求权限的监听，根据请求码判断
//    //-1为拒绝，0为允许
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == 10086) {
//            System.out.println("permissions = " + permissions[0]);
//            System.out.println("grantResults = " + grantResults[0]);
//        }
//    }
    }
}
