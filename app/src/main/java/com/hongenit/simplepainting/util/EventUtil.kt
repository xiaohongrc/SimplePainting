package com.hongenit.simplepainting.util

import com.hongenit.simplepainting.MyApplication
import com.umeng.analytics.MobclickAgent

/**
 * Created by hongenit on 18/2/8.
 * umeng事件
 */
object EventUtil {
    fun onEvent(action: String, hashMap: HashMap<String, String?>? = null) {
//        PicSeeApplication.getFirebaseEventLogger().logEvent(action, bundle)
        if (hashMap == null) {
            MobclickAgent.onEvent(MyApplication.getAppContext(), action)
        } else {
            MobclickAgent.onEvent(MyApplication.getAppContext(), action, hashMap)
        }

    }

    // drawer_action 抽屉打开或关闭事件
    fun drawer_action(hashMap: HashMap<String, String?>?) {
        onEvent("drawer_action", hashMap)
    }


    // drawer_open_btn_click 点击打开抽屉按钮
    fun drawer_open_btn_click() {
        onEvent("drawer_open_btn_click")
    }


    // drawer_close_btn_click 点击关闭抽屉按钮
    fun drawer_close_btn_click() {
        onEvent("drawer_close_btn_click")
    }


    //  menu_click_favorites 抽屉中的收藏
    fun menu_click_favorites() {
        onEvent("menu_click_favorites")
    }


    // menu_click_feedback 抽屉中的反馈
    fun menu_click_feedback() {
        onEvent("menu_click_feedback")
    }

    // menu_click_setting 抽屉中的设置
    fun menu_click_setting() {
        onEvent("menu_click_setting")
    }

    // request_classify 请求一个分类标签
    fun request_classify(hashMap: HashMap<String, String?>?) {
        onEvent("request_classify", hashMap)
    }

    // album_to_detail_click 点击图集
    fun album_to_detail_click(hashMap: HashMap<String, String?>?) {
        onEvent("album_to_detail_click", hashMap)
    }

    // detail_photo_favour 收藏图片
    fun detail_photo_favour(hashMap: HashMap<String, String?>?) {
        onEvent("detail_photo_favour", hashMap)
    }


/*
    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }
*/

    object FirebaseEventParams {

        const val drawer_close = "drawer_close"
        const val drawer_open = "drawer_open"
        const val urlWithParam = "urlWithParam"
        const val albumUrl = "albumUrl"
        const val tabUrl = "tabUrl"
        const val thumbnailUrl = "thumbnailUrl"
        const val picUrl = "picUrl"

    }


}