package com.example.news.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.news.R
import com.example.news.util.showToast

class TestFragment(val testName: String) : Fragment() {

    lateinit var testNameView: TextView
    lateinit var testbutton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        testNameView = view.findViewById(R.id.test_name)
        testbutton = view.findViewById(R.id.test_button)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        testNameView.text = testName

        testbutton.setOnClickListener {
            "© 2022 许万鹏。仅供学习。".showToast()
        }
    }


}