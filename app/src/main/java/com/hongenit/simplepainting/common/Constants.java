package com.hongenit.simplepainting.common;

import android.os.Build;
import android.os.Environment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Locale;

/**
 * Created by hongenit on 2018/2/4.
 * constant values
 */

public interface Constants {

    File APP_DIR = new File(Environment.getExternalStorageDirectory(), ".PicSeeSee");

    // 缓存文档的目录
    File CACHE_DOCUMENTS = new File(APP_DIR, "cache_documents");
    File CACHE_IMAGES = new File(APP_DIR, "images");

    String UMENG_CHANNEL_NAME = "google";
    String UMENG_APP_KEY = "5bc9b8e9f1f556d4cf000063";


    String ADMOB_APP_ID = "ca-app-pub-1616641096587052~4948648232";
    // admob ID of banner canvas
    String ADMOB_BANNER_CANVAS = "ca-app-pub-1616641096587052/7820146169";

    @NotNull
    String SDK_VERSION = String.valueOf(Build.VERSION.SDK_INT);
    @Nullable
    String LOCALE_COUNTRY = Locale.getDefault().getCountry();
    @NotNull
    String LOCALE_LANGUAGE = Locale.getDefault().getLanguage();

    long ONE_SECOND = 1000;
    long ONE_MINUTE = 60 * ONE_SECOND;
    long ONE_HOUR = 60 * ONE_MINUTE;
    long ONE_DAY = 24 * ONE_HOUR;


    // 画笔粗细最大值，最小值
    int PAINT_SIZE_MAX = 50;
    int PAINT_SIZE_MIN = 1;

}
