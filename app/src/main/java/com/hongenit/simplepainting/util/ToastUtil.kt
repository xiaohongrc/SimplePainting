package com.hongenit.simplepainting.util

import android.widget.Toast
import com.hongenit.simplepainting.MyApplication

/**
 * Created by hongenit on 18/2/1.
 *  ToastUtil
 */
object ToastUtil {
    fun showToast(msg: String) {
        Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show()
    }


}