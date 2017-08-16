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
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<String> list = new ArrayList<String>();
    List<String> list1 = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // startActivity(new Intent(this, Main2Activity.class));
        list.add("android.permission.CAMERA");
        list.add("android.permission.CALL_PHONE");

        Button btn = (Button) findViewById(R.id.btn);

        if (Build.VERSION.SDK_INT >= 23) {
            Toast.makeText(this, "大于23", Toast.LENGTH_SHORT).show();
            PackageManager pm = getPackageManager();


            for (int i = 0; i < 2; i++) {
                System.out.println("list.get(i) =权限 " + list.get(i));
                boolean permission = PackageManager.PERMISSION_GRANTED ==
                        pm.checkPermission(list.get(i), "com.example.fuhao.android60");
                if (!permission) {//为true代表已经有权限，在list中移除
                    list1.add(list.get(i));
                }
            }
            int size = list.size();
            if (size == 0) {
                return;
            }

            System.out.println("权限 list size = " + size);
            final String[] str = list1.toArray(new String[0]);
            System.out.println("权限 str length = " + str.length);
            if (str.length != 0) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("打开相机时需要系统授权读取相机权限权限");
                dialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //动态请求权限
                                ActivityCompat.requestPermissions(MainActivity.this, str, 10086);
                            }
                        });
                dialog.show();
            }
        } else {
            Toast.makeText(this, "小于23", Toast.LENGTH_SHORT).show();
        }

//        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
//            //startGetImageThread();
//        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //动态请求权限的监听，根据请求码判断
    //-1为拒绝，0为允许
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10086:
                for (int i = 0; i < permissions.length; i++) {
                    if (permissions[i].equals("android.permission.CAMERA")) {
                        if (grantResults[i] == -1) {
                            System.out.println("没有相机权限");
                            //   Toast.makeText(this, "拒绝了", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("有相机权限");
                            //   Toast.makeText(this, "允许了", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                            startActivity(intent);
                        }
                    }
                    if (permissions[i].equals("android.permission.CALL_PHONE")) {
                        if (grantResults[i] == -1) {
                            System.out.println("没有联系人权限");
                            // Toast.makeText(this, "拒绝了联系人", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("有联系人权限");
                            //Toast.makeText(this, "允许了联系人", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
//        if (requestCode == 10086) {
//            System.out.println("权限 "+permissions[0]);
//            for (int i = 0; i < permissions.length ; i++) {
//                if (permissions[i].equals("android.permission.CAMERA")) {
//                    if (grantResults[i] == -1) {
//                        Toast.makeText(this, "拒绝了", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(this, "允许了", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent();
//                        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
//                        startActivity(intent);
//                    }
//                }
//                if (permissions[i].equals("android.permission.CALL_PHONE")) {
//                    if (grantResults[i] == -1) {
//                        Toast.makeText(this, "拒绝了", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(this, "允许了", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                switch (){
//
//                }
    }
}
