package com.example.news.ui.activity

import android.graphics.Color
import android.graphics.PixelFormat.TRANSLUCENT
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.news.R
import com.example.news.ui.fragment.HomeFragment
import com.example.news.ui.fragment.TestFragment
import com.example.news.util.changeStatusBarTransparent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX.statusBar
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar


class MainActivity : AppCompatActivity() {

    val fragmentList =
        listOf(
            TestFragment("视频功能开发中~"),
            HomeFragment(),
            TestFragment("个人主页开发中~")
//            TestFragment("TestFragment3333"),
//            TestFragment("TestFragment4444")
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // 加载主活动页面布局文件

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

        val contentViewPager = findViewById<ViewPager>(R.id.content_view_pager)
        // 设置fragment页面缓存数量！！否则切换不回来！！！
        contentViewPager.offscreenPageLimit = fragmentList.size
        contentViewPager.adapter = MyAdapter(supportFragmentManager)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        // 将nav_home作为默认fragment
        bottomNav.selectedItemId = R.id.nav_home
        contentViewPager.currentItem = 1
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> contentViewPager.currentItem = 1
                R.id.nav_test_1 -> contentViewPager.currentItem = 0
                R.id.nav_test_2 -> contentViewPager.currentItem = 2
//                R.id.nav_test_3 -> contentViewPager.currentItem = 3
//                R.id.nav_test_4 -> contentViewPager.currentItem = 4
            }
            false
        }

        contentViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNav.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    inner class MyAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount() = fragmentList.size

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

    }


}


