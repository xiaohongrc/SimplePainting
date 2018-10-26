package com.hongenit.simplepainting.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umeng.analytics.MobclickAgent


/**
 * Created by hongenit on 18/1/30.
 * 分类fragment的基类
 */
abstract class BaseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentContentId(), container, false)
    }

    // 获取fragment的布局id
    abstract fun getFragmentContentId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initParams()
        initView()
        initData()
    }

    protected open fun initParams() {}

    abstract fun initView()

    abstract fun initData()

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(javaClass.name) //统计页面，"MainScreen"为页面名称，可自定义
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(javaClass.name)
    }

}