package com.example.news.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.news.NewsApplication
import com.example.news.News
import com.example.news.NewsResponse
import com.example.news.R
import com.example.news.adapter.NewsAdapter
import com.example.news.ui.activity.ContentActivity
import com.example.news.util.showToast
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class NewsFragment(var newsType: String) : Fragment() {

    private lateinit var newsRecyclerView: RecyclerView

    private lateinit var refresh: SwipeRefreshLayout

    private val newsList = ArrayList<News>()

    private val newsAdapter = NewsAdapter(newsList, this)

    @SuppressLint("NotifyDataSetChanged")
    private fun refresh() {
        thread {
//            val request = Request.Builder()
//                .url("http://v.juhe.cn/toutiao/index?type=${newsType}&page=&page_size=1&is_filter=&key=${NewsApplication.juheKEY2}")
//                .build()
            val request = Request.Builder()
                .url("https://way.jd.com/jisuapi/get?channel=${newsType}&num=10&start=0&appkey=${NewsApplication.jdKEY}")
                .build()
            try {
                val response = OkHttpClient().newCall(request).execute()
                val json = response.body?.string()  // string()!!!
                val newsResponse = Gson().fromJson(json, NewsResponse::class.java)
//            if (newsResponse?.result != null) {
                if (newsResponse != null) {
                    try {
//                        val data = newsResponse.result.data
                    val data = newsResponse.result.result.list
                        newsList.clear()
                        newsList.addAll(data)
                        // 切换回UI线程(即主线程)，执行刷新UI的操作
                        activity?.runOnUiThread {
                            newsRecyclerView.adapter?.notifyDataSetChanged()
                        }
                    } catch (e: Exception) {
                        activity?.runOnUiThread {
                            "请检查API是否已达今日获取上限".showToast()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                activity?.runOnUiThread {
                    "网络请求失败".showToast()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frament_news, container, false)
        newsRecyclerView = view.findViewById<RecyclerView>(R.id.news_recycler_view)  // classic
        refresh = view.findViewById(R.id.news_refresh)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsRecyclerView.layoutManager = LinearLayoutManager(NewsApplication.context)
        newsRecyclerView.adapter = newsAdapter

        // 创建页面后立即刷新一次页面上的新闻数据
        refresh()

        refresh.setColorSchemeColors(Color.parseColor("#F48FB1"))
        refresh.setOnRefreshListener {
            thread {
                Thread.sleep(700)  // 视觉效果
                activity?.runOnUiThread {
                    refresh()
                    refresh.isRefreshing = false
                }
            }
        }
    }

//    // 将自定义Adapter作为MainActivity的内部类是为了获取它作为上下文Context
//    inner class NewsAdapter(private val newsList: List<News>) :
//        RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
//
//        // 将自定义ViewHolder作为自定义Adapter的内部类是一种习惯（使用它的地方记得更改为获取内部类的方式）
//        inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val title: TextView = itemView.findViewById(R.id.news_title)
//            val description: TextView = itemView.findViewById(R.id.news_description)
//            val image: com.makeramen.roundedimageview.RoundedImageView =
//                itemView.findViewById(R.id.news_image)
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//            val itemView =
//                LayoutInflater.from(parent.context)
//                    .inflate(R.layout.news_item_one_image, parent, false)
//            return NewsViewHolder(itemView)
//        }
//
//        // 更换API记得更改来源字段
//        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//            val news = newsList[position]
//            holder.title.text = news.title
//            holder.description.text = news.author_name
//            // 加载图片。为获取Activity，将其作为MainActivity的内部类
//            Glide.with(NewsApplication.context).load(news.thumbnail_pic_s).into(holder.image)
//
//            holder.itemView.setOnClickListener {
////                "你好！我的下标是${holder.adapterPosition}！".showToast()
//                val intent = Intent(NewsApplication.context, ContentActivity::class.java)
//                intent.putExtra("url=", newsList[holder.adapterPosition].url)
//                startActivity(intent)
//
//            }
//
//        }
//
//        override fun getItemCount() = newsList.size
//
//    }
}