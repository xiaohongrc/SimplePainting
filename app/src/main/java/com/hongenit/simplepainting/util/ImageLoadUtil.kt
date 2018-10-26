package com.hongenit.simplepainting.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 * Created by hongenit on 18/1/31.
 * 图片加载的封装类
 */
class ImageLoadUtil {
    companion object {
        private var instance: ImageLoadUtil? = null
        fun newInstance(): ImageLoadUtil? {
            if (instance == null) {
                instance = ImageLoadUtil()
            }
            return instance
        }
    }

//    val requestOptions = RequestOptions().placeholder(R.drawable.default_img).error(R.drawable.default_img)

    fun loadImage(context: Context, imageView: ImageView, imgUrl: String?) {
        val crossFade = DrawableTransitionOptions().crossFade(100)
        Glide.with(context).load(imgUrl).transition(crossFade).into(imageView)
    }

    fun loadRoundImage(context: Context, imageView: ImageView, imgUrl: String, cornerRadius: Float= ScreenUtil.dip2px(context, 20F).toFloat(), listener: ImageLoadListener? = null) {
        val crossFade = DrawableTransitionOptions().crossFade(100)
        val requestOptions = RequestOptions().transform(CornersTransform(context, cornerRadius))
        Glide.with(context).load(imgUrl).apply(requestOptions).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                listener?.onLoadFailed()
                return false
            }

            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                listener?.onLoadComplete()
                return false
            }
        }).into(imageView)
    }

    interface ImageLoadListener {
        fun onLoadFailed()
        fun onLoadComplete()

    }


}

