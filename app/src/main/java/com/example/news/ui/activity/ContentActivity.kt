package com.example.news.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.news.R
import com.example.news.util.changeStatusBarTransparent
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar


class ContentActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)  // 加载新闻内容活动页面布局文件

        // 沉浸式状态栏（官方）
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        changeStatusBarTransparent(this)

        // 沉浸式状态栏（UltimateBarX插件）
        statusBar {
            // 布局是否侵入状态栏（true 不侵入，false 侵入）
            fitWindow = true
            // 状态栏背景颜色（色值）
            color = Color.rgb(233, 30, 99)
            // light模式：状态栏字体 true: 灰色，false: 白色 Android 6.0+
            light = false
            // 低版本 light 模式不生效，重新设置状态栏背景
            // 防止状态栏背景色跟字体颜色一致导致字体看不见
            // lvl 系列方法仅在低版本（不支持 light 模式的版本）下开启 light 模式生效
            lvlColor = Color.WHITE
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

        // 设置系统自带的home按钮（箭头）
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 从intent中接受url
        val url = intent.getStringExtra("url=")
        // 获取内嵌浏览器并修改其设置
        val webView = findViewById<WebView>(R.id.news_web_view)
        val settings = webView.settings
        settings.javaScriptEnabled = true  // 允许加载JS，避免显示不全问题，有些新闻页会加载广告。
        webView.webViewClient = WebViewClient()  // 不使用浏览器
        // 使用内嵌浏览器打开网页
        url?.let { webView.loadUrl(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}