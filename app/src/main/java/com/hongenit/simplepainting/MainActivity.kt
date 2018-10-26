package com.hongenit.simplepainting

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.widget.Toast
import com.example.seekbarlibrary.discreteseekbar.DiscreteSeekBar
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.hongenit.simplepainting.colorpicker.ColorDialog
import com.hongenit.simplepainting.common.BaseActivity
import com.hongenit.simplepainting.common.Constants
import com.hongenit.simplepainting.setting.SettingActivity
import com.hongenit.simplepainting.util.EventUtil
import com.hongenit.simplepainting.util.Utils
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.layout_main_content.*
import java.util.*
import com.hongenit.simplepainting.drawableview.DrawableViewConfig
import com.hongenit.simplepainting.drawableview.DrawableView
import com.hongenit.simplepainting.util.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var drawableView: DrawableView
    private var config = DrawableViewConfig()

    private var colorDialog: ColorDialog? = null


    fun getImgName(name: String): File {
        if (!Constants.CACHE_IMAGES.exists()) {
            Constants.CACHE_IMAGES.mkdirs()
        }

        return File(Constants.CACHE_IMAGES, name)
    }


    fun saveBitmap(bitmap: Bitmap, filePic: File): String? {
        try {
            if (!filePic.exists()) {
                filePic.parentFile.mkdirs()
                filePic.createNewFile()
            }
            val fos = FileOutputStream(filePic)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return filePic.absolutePath

    }

    private var bgColorDialog: ColorDialog? = null

    override fun onClick(v: View?) {
        when (v) {

            bt_save -> {
                val obtainBitmap = drawableView.obtainBitmap()
                val name = System.currentTimeMillis().toString() + ".jpg"
                val saveBitmap = saveBitmap(obtainBitmap, getImgName(name))
                if (saveBitmap != null) {
                    Toast.makeText(this, "图片已保存到：\n $saveBitmap", Toast.LENGTH_LONG).show()
                }
            }
            bt_clear -> {
                drawableView.clear()
            }
            bt_undo -> {

                drawableView.undo()
            }
            bt_color -> {
                if (colorDialog == null) {
                    colorDialog = ColorDialog(this, ColorDialog.ColorChooseListener {
                        bt_color.setBackgroundColor(it)
                        iv_paint_style.setBackgroundColor(it)
                        config.strokeColor = it
                    })
                }
                colorDialog?.show()
            }

            tv_menu_item1 -> {

                if (bgColorDialog == null) {
                    bgColorDialog = ColorDialog(this, ColorDialog.ColorChooseListener {
                        drawableView.setCanvaBgColor(it)
                    })
                }
                bgColorDialog?.show()
            }

            tv_menu_item2 -> {
                Utils.feedback(this@MainActivity, getString(R.string.feedback_title), getString(R.string.feedback_email))
                EventUtil.menu_click_feedback()
            }

            tv_menu_item3 -> {
                startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                EventUtil.menu_click_setting()
            }

            close_menu -> {
                if (drawer_layout.isDrawerOpen(rl_menu)) {
                    drawer_layout.closeDrawer(rl_menu)
                    EventUtil.drawer_close_btn_click()
                }
            }

            ib_open_drawer -> {
                if (!drawer_layout.isDrawerOpen(rl_menu)) {
                    drawer_layout.openDrawer(rl_menu)
                    EventUtil.drawer_open_btn_click()
                }
            }

        }

    }


    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initAdMob()
        initUi()
//        loadAdView()
    }

    private fun loadAdView() {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                println("onAdLoaded")

                super.onAdLoaded()
            }

            override fun onAdClosed() {
                println("onAdClosed")

                super.onAdClosed()
            }

            override fun onAdClicked() {
                println("onAdClicked")

                super.onAdClicked()
            }

            override fun onAdFailedToLoad(p0: Int) {
                println("onAdFailedToLoad code = $p0")
                super.onAdFailedToLoad(p0)
            }

        }
    }


    private fun initAdMob() {
        MobileAds.initialize(this, Constants.ADMOB_APP_ID)
    }


    private lateinit var mSeekBar: DiscreteSeekBar

    private fun initView() {
        ib_open_drawer.setOnClickListener(this)
        bt_color.setOnClickListener(this)
        bt_clear.setOnClickListener(this)
        bt_undo.setOnClickListener(this)
        bt_save.setOnClickListener(this)
        mSeekBar = discrete2 as DiscreteSeekBar
        mSeekBar.max = 100
        mSeekBar.min = 1
        mSeekBar.numericTransformer = object : DiscreteSeekBar.NumericTransformer() {
            override fun transform(value: Int): Int {
                LogUtil.hong("value  = $value")
                config.strokeWidth = value.toFloat()
                iv_paint_style.layoutParams.width = value * 2
                iv_paint_style.layoutParams.height = value * 2
                iv_paint_style.requestLayout()
                return value
            }
        }

        initDrawer()
    }


    private fun initUi() {
        drawableView = paintView as DrawableView

        config.strokeColor = Color.RED
        config.isShowCanvasBounds = true
        config.strokeWidth = 20.0f
        config.minZoom = 1.0f
        config.maxZoom = 3.0f
        config.canvasHeight = 1920
        config.canvasWidth = 1080
        drawableView.setConfig(config)

    }

    private fun initDrawer() {
        var versionName = "1.0"
        try {
            versionName = packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        app_version.text = versionName
        drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
                println("new state  =$newState")
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                //设置主布局随菜单滑动而滑动
                val drawerViewWidth = drawerView.width
                fl_main_content.translationX = drawerViewWidth * slideOffset

                //设置控件最先出现的位置
                val padingLeft = drawerViewWidth.toDouble() * (1 - 0.618) * (1 - slideOffset).toDouble()
                rl_menu.setPadding(padingLeft.toInt(), 0, 0, 0)
            }

            override fun onDrawerClosed(drawerView: View) {
                LogUtil.i(TAG, "onDrawerClosed")
                val hashMap: HashMap<String, String?>? = hashMapOf()
                hashMap?.put(EventUtil.FirebaseEventParams.drawer_close, "onDrawerClosed")
                EventUtil.drawer_action(hashMap)
            }

            override fun onDrawerOpened(drawerView: View) {
                LogUtil.i(TAG, "onDrawerOpened")
                val hashMap: HashMap<String, String?>? = hashMapOf()
                hashMap?.put(EventUtil.FirebaseEventParams.drawer_open, "onDrawerOpened")
                EventUtil.drawer_action(hashMap)
            }
        })


        tv_menu_item1.setOnClickListener(this)
        tv_menu_item2.setOnClickListener(this)
        tv_menu_item3.setOnClickListener(this)
        close_menu.setOnClickListener(this)


    }


}
