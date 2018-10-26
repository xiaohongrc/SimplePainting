//package com.hongenit.picseei18n.util
//
//import android.content.Context
//import android.content.SharedPreferences
//import com.hongenit.picseei18n.DataModel.Companion.mInstance
//import com.hongenit.picseei18n.PicSeeApplication
//
///**
// * Created by hongenit on 2018/5/20.
// */
//class Pref {
//    private var userSettings: SharedPreferences = PicSeeApplication.getAppContext()!!.getSharedPreferences("setting", 0)
//    private val LAST_CACHE_TIME = "last_cache_time"
//
//    companion object {
//        var mPreInstance: Pref? = null
//
//        fun getInstance(): Pref {
//            if (mPreInstance == null) {
//                mPreInstance = Pref()
//            }
//            return mPreInstance!!
//        }
//    }
//
//    // 设置缓存时间
//    fun setLastCacheTime() {
//        val editor = userSettings.edit()
//        editor.putLong(LAST_CACHE_TIME, System.currentTimeMillis())
//        editor.apply()
//        println("setLastCacheTime time  = " + System.currentTimeMillis())
//
//    }
//
//    // 获取上次缓存时间
//    fun getLastCacheTime(): Long {
//        val time = userSettings.getLong(LAST_CACHE_TIME, System.currentTimeMillis())
//        println("last cache time  = " + time)
//        return time
//    }
//
//}