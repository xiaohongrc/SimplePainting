package com.hongenit.simplepainting.util

import android.content.SharedPreferences
import android.graphics.Color
import com.hongenit.simplepainting.MyApplication

/**
 * Created by hongenit on 2018/5/20.
 */

class Pref {
    private val KEY_PREF_CANVAS_COLOR: String = "key_pref_canvas_color"
    private val KEY_PREF_PAINT_COLOR: String = "key_pref_paint_color"
    private val KEY_PREF_PAINT_SIZE: String = "key_pref_paint_size"

    private val DEFAULT_CANVAS_COLOR: Int = Color.WHITE
    private val DEFAULT_PAINT_COLOR: Int = Color.RED
    private val DEFAULT_PAINT_SIZE: Float = 20f
    private var userSettings: SharedPreferences = MyApplication.getAppContext()!!.getSharedPreferences("setting", 0)
    private val LAST_CACHE_TIME = "last_cache_time"

    companion object {
        var mPreInstance: Pref? = null

        fun getInstance(): Pref {
            if (mPreInstance == null) {
                mPreInstance = Pref()
            }
            return mPreInstance!!
        }
    }

    // 设置缓存时间
    fun setLastCacheTime() {
        val editor = userSettings.edit()
        editor.putLong(LAST_CACHE_TIME, System.currentTimeMillis())
        editor.apply()
        println("setLastCacheTime time  = " + System.currentTimeMillis())

    }

    // 获取上次缓存时间
    fun getLastCacheTime(): Long {
        val time = userSettings.getLong(LAST_CACHE_TIME, System.currentTimeMillis())
        println("last cache time  = " + time)
        return time
    }


    // 画布背景颜色
    fun setCanvasColor(color: Int) {
        userSettings.edit().putInt(KEY_PREF_CANVAS_COLOR, color).apply()
    }

    fun getCanvasColor(): Int {
        return userSettings.getInt(KEY_PREF_CANVAS_COLOR, DEFAULT_CANVAS_COLOR)
    }

    // 画笔颜色
    fun setPaintColor(color: Int) {

        LogUtil.hong("setPaintColor   = $color")
        userSettings.edit().putInt(KEY_PREF_PAINT_COLOR, color).apply()
    }

    fun getPaintColor(): Int {
        val getColor = userSettings.getInt(KEY_PREF_PAINT_COLOR, DEFAULT_PAINT_COLOR)
        LogUtil.hong("getPaintColor = $getColor")
        return getColor
    }


    // 画笔粗细
    fun setPaintSize(size: Float) {
        userSettings.edit().putFloat(KEY_PREF_PAINT_SIZE, size).apply()
    }

    fun getPaintSize(): Float {
        return userSettings.getFloat(KEY_PREF_PAINT_SIZE, DEFAULT_PAINT_SIZE)
    }

    //


}