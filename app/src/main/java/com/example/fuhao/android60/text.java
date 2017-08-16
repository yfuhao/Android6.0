package com.example.fuhao.android60;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fuhao on 2017/8/16.
 */

public class text {
    List<String> list = new ArrayList<String>();

    public static void main(String[] arg) {
        String[] arr = {"android.permission.CAMERA", "android.permission.CALL_PHONE"};
        List<String> strings = Arrays.asList(arr);
        String[] array = (String[]) strings.toArray();
    }
}
