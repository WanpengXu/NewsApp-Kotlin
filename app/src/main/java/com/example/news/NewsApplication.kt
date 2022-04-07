package com.example.news

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class NewsApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        val juheKEY2 = "65d4c89f2460e131bd8b288f3f70bff6"  // BILIBILI up主的KEY
        val juheKEY = "d8f17897b5b475c3c4307e4dcbf174c2"
        val jdKEY = "b172f868ee8346493175573080b36927"
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }
}