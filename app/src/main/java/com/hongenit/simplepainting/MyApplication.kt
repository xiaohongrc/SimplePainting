package com.hongenit.simplepainting

import android.app.Application
import android.content.Context
import android.os.Handler
import com.hongenit.simplepainting.common.Constants
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

/**
 * Created by Xiaohong on 2018/5/28.
 * desc:
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        sContext = applicationContext
        initUmeng()
    }


    private fun initUmeng() {
        UMConfigure.init(this, Constants.UMENG_APP_KEY, Constants.UMENG_CHANNEL_NAME, UMConfigure.DEVICE_TYPE_PHONE, "7949ff424302b34a1fb5ffc0d2cec3b9")
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)
        UMConfigure.setLogEnabled(true)
    }


    companion object {
        lateinit var sContext: Context
        val mHandler: Handler = Handler()

        fun getAppContext(): Context? {
            return sContext
        }

        fun runOnUiThread(runnable: Runnable) {
            mHandler.post(runnable)
        }

    }

}