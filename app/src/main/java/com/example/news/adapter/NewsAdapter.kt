package com.example.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.News
import com.example.news.NewsApplication
import com.example.news.R
import com.example.news.ui.activity.ContentActivity
import com.example.news.ui.fragment.NewsFragment

// 将自定义Adapter作为MainActivity的内部类是为了获取它作为上下文Context
// 当自定义Adapter增多时可集成到一个adapter包中，使用参数向其传递fragment
class NewsAdapter(private val newsList: List<News>,  private val newsFragment: NewsFragment) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    // 将自定义ViewHolder作为自定义Adapter的内部类是一种习惯（使用它的地方记得更改为获取内部类的方式）
    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.news_title)
        val description: TextView = itemView.findViewById(R.id.news_description)
        val image: com.makeramen.roundedimageview.RoundedImageView =
            itemView.findViewById(R.id.news_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item_one_image, parent, false)
        return NewsViewHolder(itemView)
    }

    // 更换API记得更改来源字段
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.title.text = news.title
//        holder.description.text = news.author_name
        holder.description.text = news.src
        // 加载图片。为获取Activity，将其作为MainActivity的内部类
//        Glide.with(NewsApplication.context).load(news.thumbnail_pic_s).into(holder.image)
        Glide.with(NewsApplication.context).load(news.pic).into(holder.image)

        holder.itemView.setOnClickListener {
//                "你好！我的下标是${holder.adapterPosition}！".showToast()
            val intent = Intent(NewsApplication.context, ContentActivity::class.java)
            intent.putExtra("url=", newsList[holder.adapterPosition].url)
            // 在Activity外使用startActivity()需要该flag
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(NewsApplication.context,  intent, null)
        }

    }

    override fun getItemCount() = newsList.size

}