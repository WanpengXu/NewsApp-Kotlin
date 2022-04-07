package com.example.news

// juhe
//data class NewsResponse(val reason: String, val result: NewsResult, val error_code: Int)
//
//data class NewsResult(val stat: String, val data: List<News>)
//
//data class News(
//    val title: String,
//    val date: String,
//    val author_name: String,
//    val url: String,
//    val thumbnail_pic_s: String
//)

// jd
data class NewsResponse(val code: String, val charge: Boolean, val result: NewsResult)

data class NewsResult(val status: Int, val result: NewsResult2)

data class NewsResult2(val channel: String, val num: Int, val list: List<News>)

data class News(
    val title: String,
    val time: String,
    val src: String,
    val pic: String,
    val url: String,
    val weburl: String
)