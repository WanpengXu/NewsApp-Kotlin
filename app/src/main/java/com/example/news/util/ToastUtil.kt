package com.example.news.util

import android.widget.Toast
import com.example.news.NewsApplication

fun String.showToast() {
    Toast.makeText(
        NewsApplication.context,
        this,
        Toast.LENGTH_SHORT
    ).show()
}