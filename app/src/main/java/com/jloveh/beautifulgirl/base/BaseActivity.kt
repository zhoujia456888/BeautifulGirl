package com.jloveh.beautifulgirl.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ScreenUtils

open class BaseActivity : AppCompatActivity() {

    lateinit var baseactivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseactivity = this

        ScreenUtils.setPortrait(baseactivity)//竖屏

    }




}