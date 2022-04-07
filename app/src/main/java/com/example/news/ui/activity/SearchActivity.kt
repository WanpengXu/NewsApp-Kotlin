package com.example.news.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.news.R
import com.example.news.util.changeStatusBarTransparent
import com.example.news.util.showToast
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // 沉浸式状态栏（官方）
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        changeStatusBarTransparent(this)

        // 沉浸式状态栏（UltimateBarX插件）
        statusBar {
            // 布局是否侵入状态栏（true 不侵入，false 侵入）
            fitWindow = true
            // 状态栏背景颜色（色值）
            color = Color.TRANSPARENT
            // light模式：状态栏字体 true: 灰色，false: 白色 Android 6.0+
            light = true
            // 低版本 light 模式不生效，重新设置状态栏背景
            // 防止状态栏背景色跟字体颜色一致导致字体看不见
            // lvl 系列方法仅在低版本（不支持 light 模式的版本）下开启 light 模式生效
            lvlColor = Color.BLACK
        }

        // 透明式导航栏（UltimateBarX插件）
        navigationBar {
            // 布局是否侵入状态栏（true 不侵入，false 侵入）
            fitWindow = true
            // 状态栏背景颜色（色值）
            color = Color.TRANSPARENT
            // light模式：状态栏字体 true: 灰色，false: 白色 Android 6.0+
            light = true
            // 低版本 light 模式不生效，重新设置状态栏背景
            // 防止状态栏背景色跟字体颜色一致导致字体看不见
            // lvl 系列方法仅在低版本（不支持 light 模式的版本）下开启 light 模式生效
            lvlColor = Color.BLACK
        }

        // "取消"点击事件：销毁本活动，返回上一级
        val searchCancelButton = findViewById<TextView>(R.id.search_cancel_button)
        searchCancelButton.setOnClickListener {
            finish()
        }
        // 搜索框
        val searchEditText = findViewById<EditText>(R.id.home_edit_text)
        searchEditText.setOnEditorActionListener { _, keyCode, _ ->
            // 如果点击了回车键，即搜索键，目前弹出一个toast
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                "你搜索了'${searchEditText.text}'".showToast()
                true
            } else {
                false
            }
        }


    }
}