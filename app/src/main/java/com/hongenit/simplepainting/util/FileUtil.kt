package com.hongenit.simplepainting.util

import android.os.SystemClock
import com.hongenit.simplepainting.MyApplication
import java.io.File
import java.nio.charset.Charset


/**
 * Created by hongenit on 2018/2/4.
 *
 */
object FileUtil {


    // 根据url获取缓存json数据
    fun getDataByUrl(buildUrl: String): String {
        println("timeee 2000 = " + SystemClock.currentThreadTimeMillis())

        // url的md5值作为缓存文件名
        val cacheFileMd5 = EnDecryptUtil.md5(buildUrl)
        println("timeee 2001 = " + SystemClock.currentThreadTimeMillis())

        return getJsonString(cacheFileMd5)

    }

    // 获取缓存的json String
    private fun getJsonString(fileName: String): String {
        if (!isCacheFileExist(fileName)) {
            return ""
        }
        val dirCacheDocument = getCacheDir()
        val file = File(dirCacheDocument, fileName)
        val text = file.readText(Charset.forName("utf-8"))
        return text
    }

    // 缓存Json数据
    fun cacheJsonData(url: String, jsonData: String) {
        val cacheFileMd5 = EnDecryptUtil.md5(url)
        val dirCacheJson = getCacheDir()

        if (!dirCacheJson.exists()) {
            dirCacheJson.mkdirs()
        }
        if (dirCacheJson.exists() && dirCacheJson.isDirectory) {
            println("timeee 55555555555555555 " + SystemClock.currentThreadTimeMillis())
            val file = File(dirCacheJson, cacheFileMd5)
            file.writeText(jsonData, Charset.forName("utf-8"))
        }
    }

    // 缓存文件是否存在
    private fun isCacheFileExist(fileName: String): Boolean {
        val dirCacheDocument = getCacheDir()
//            val file = File(dirCacheDocument, fileName)
        if (dirCacheDocument.exists() && dirCacheDocument.isDirectory() && dirCacheDocument.list() != null) {
            val fileList = dirCacheDocument.list()
            if (fileList.contains(fileName)) {
                return true
            }
        }
        return false
    }

    private fun getCacheDir(): File {
        var cachePath: String? = null
        cachePath = MyApplication.getAppContext()!!.getCacheDir().getPath()
        LogUtil.d(this, cachePath)
        return File(cachePath, "cache_documents")
    }

    // 删除缓存网络请求的目录
    fun deleteCacheNetworkDir() {
        deleteDir(getCacheDir())
    }

    // 删除目录
    fun deleteDir(dir: File?) {
        if (dir == null) {
            return
        } else if (dir.exists()) {//如果此抽象指定的对象存在并且不为空。
            if (dir.isFile) {
                dir.delete()//如果此抽象路径代表的是文件，直接删除。
            } else if (dir.isDirectory) {//如果此抽象路径指代的是目录
                val str = dir.list()//得到目录下的所有路径
                if (str == null) {
                    dir.delete()//如果这个目录下面是空，则直接删除
                } else {//如果目录不为空，则遍历名字，得到此抽象路径的字符串形式。
                    for (st in str) {
                        deleteDir(File(dir, st))
                    }//遍历清楚所有当前文件夹里面的所有文件。
                    dir.delete()//清楚文件夹里面的东西后再来清楚这个空文件夹

                }
            }
        }
    }


}