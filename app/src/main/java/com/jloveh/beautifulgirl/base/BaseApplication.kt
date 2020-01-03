package com.jloveh.beautifulgirl.base

import android.app.Application
import com.blankj.utilcode.util.Utils

class BaseApplication :Application(){

    companion object{
        lateinit var application:Application
    }

    override fun onCreate() {
        super.onCreate()

        application=this
        Utils.init(application)//utils初始化
    }
}