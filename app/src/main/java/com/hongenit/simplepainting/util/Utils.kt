package com.hongenit.simplepainting.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.hongenit.simplepainting.BuildConfig
import com.hongenit.simplepainting.R
import com.hongenit.simplepainting.common.Constants
import java.math.BigInteger
import java.security.MessageDigest


/**
 * Created by hongenit on 18/1/29.
 * desc:
 */
class Utils {
    companion object {
        /**
         * 对字符串md5加密(小写+字母)
         *
         * @param str 传入要加密的字符串
         * @return  MD5加密后的字符串
         */
        fun getMD5(str: String): String? {
            try {
                val md = MessageDigest.getInstance("MD5")
                md.update(str.toByteArray())
                // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
                // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
                return BigInteger(1, md.digest()).toString(16)
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

        }


        fun feedback(context: Context, feedbackTitle: String, feedbackEmail: String) {
            try {
                val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", feedbackEmail, null))
                sendIntent.putExtra(Intent.EXTRA_TEXT, buildFeedbackText(context))
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, feedbackTitle)
                sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(sendIntent)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        private fun buildFeedbackText(context: Context): String {
            return context.getString(R.string.feedback_message, BuildConfig.VERSION_NAME, Constants.SDK_VERSION)
        }


    }


}