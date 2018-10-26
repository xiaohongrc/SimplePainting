package com.hongenit.simplepainting.db;

import android.provider.BaseColumns;

/**
 * Created by Xiaohong on 2018/5/10.
 * desc:
 */

public class DbSettings {
    public static class FavouriteTable implements BaseColumns {
        public static String TABLE_NAME = "favourite";
        public static String PHOTO_URL = "photo_url";
        public static String FAVOURITE_TIME = "favourite_time";
    }


}
