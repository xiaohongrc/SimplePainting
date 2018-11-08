package com.hongenit.simplepainting.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.security.Permission;

/**
 * Created by Xiaohong on 2018/11/8.
 * desc:
 */
public class PermissionUtils {

    public final static int REQUEST_CODE_STORAGE = 0x1001;

    // 请求权限
    public static void askPermission(Activity activity, String[] permissions, int reqCode, Runnable grantedTask) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isAllPermissionsGranted(activity, permissions)) {
                grantedTask.run();
            } else {
                ActivityCompat.requestPermissions(activity, permissions, reqCode);
            }
        } else {
            grantedTask.run();
        }

    }


    // 是否所有权限都授予
    private static boolean isAllPermissionsGranted(Activity activity, String[] permissions) {
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, p) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static void onRequestPermissionsResult(int requestCode, int[] grantResults, Runnable grantedTask, Runnable deniedTask) {
        if (requestCode == REQUEST_CODE_STORAGE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                grantedTask.run();
            } else {
                deniedTask.run();
            }
        }


    }
}
