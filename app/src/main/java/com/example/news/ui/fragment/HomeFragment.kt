package com.example.news.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.Toolbar  // x!!
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.news.NewsApplication
import com.example.news.R
import com.example.news.ui.activity.SearchActivity
import com.example.news.util.showToast
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    // juhe:top(推荐,默认) 、guonei(国内) 、guoji(国际) 、yule(娱乐) 、tiyu(体育) 、junshi(军事) 、keji(科技) 、caijing(财经) 、youxi(游戏) 、qiche(汽车) 、jiankang(健康)
//    private val newsTypeList = listOf("top", "guonei", "guoji", "yule", "tiyu", "junshi", "keji")
//    val titleList = listOf("推荐", "国内", "国际", "娱乐", "体育", "军事", "科技")

    // jd:头条、新闻、财经、体育、娱乐、军事、教育、科技、NBA、股票、星座、女性、健康、育儿
    val newsTypeList = listOf("头条", "新闻", "财经", "体育", "娱乐", "军事", "教育")
    val titleList = listOf("头条", "新闻", "财经", "体育", "娱乐", "军事", "教育")

    // 测试时省着点用
//    val newsTypeList = listOf("头条")
//    val titleList = listOf("头条")

    val fragmentList = ArrayList<NewsFragment>()

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var editText: EditText

    inner class MyAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount() = fragmentList.size

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titleList[position]
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 在fragment_home中搜索id！！
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        tabLayout = view.findViewById<TabLayout>(R.id.news_tab_layout)  // 定义时已经声明了类型，可以去掉这里的类型
        viewPager = view.findViewById<ViewPager>(R.id.news_view_pager)
        toolbar = view.findViewById(R.id.home_toolbar)
        editText = view.findViewById(R.id.home_edit_text)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 这种方式可以实现展开收起
        toolbar.inflateMenu(R.menu.home_tool_bar_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.email -> "你点击了邮箱按钮".showToast()
                R.id.scan -> "你点击了扫码按钮".showToast()
            }
            false
        }

        // 设置home页面的搜索框 不可编辑
        editText.keyListener = null

        // 设置home页面的搜索框的点击事件: 打开SearchActivity这个页面，即搜索页面
        editText.setOnClickListener {
            val intent = Intent(NewsApplication.context, SearchActivity::class.java)
            startActivity(intent)
        }


        for (newsType in newsTypeList) {
            fragmentList.add(NewsFragment(newsType))
        }

        // 设置缓存数量！！
        viewPager.offscreenPageLimit = titleList.size
        viewPager.adapter = MyAdapter(childFragmentManager)

        tabLayout.setupWithViewPager(viewPager)
    }
}