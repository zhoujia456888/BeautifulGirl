package com.jloveh.beautifulgirl.activity

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.jloveh.beautifulgirl.R
import com.jloveh.beautifulgirl.activity.MainActivity.Companion.saiBanUserAgent
import com.jloveh.beautifulgirl.adapter.PhotoViewAdapter
import com.jloveh.beautifulgirl.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photoview.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.text.DecimalFormat


class PhotoViewAcivity : BaseActivity() {

    companion object {
        val photoBaseUrl = "https://t1.onvshen.com:85/gallery/"
    }

    var photoUrls = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photoview)

        var url = intent.getStringExtra("url")

        //获图片组编号
        var phptosNo = url.split("/")[4]

        GlobalScope.launch(Dispatchers.IO) {
            val doc: Document = Jsoup.connect(url).userAgent(saiBanUserAgent).get()
            val ck_icon = doc.select("a[href]")
            val ck_link = ck_icon.select("a.ck-link")

            //获取女神编号
            val grilNo = ck_link[0].attr("abs:href").split("/")[4]

            //获取整个图片组的图片张数
            val pageSize = doc.select("span").text().split("张")[0].toInt()
            LogUtils.e(pageSize)

            for (index in 0..pageSize) {
                //大于0的话就是3位数，不足3位补0
                var indexStr = index.toString()
                if (index > 0) {
                    indexStr = DecimalFormat("000").format(index)
                }
                var photoUrl = "$photoBaseUrl$grilNo/$phptosNo/$indexStr.jpg"
                photoUrls.add(photoUrl)
            }

            withContext(Dispatchers.Main) {
                var photoViewAdapter = PhotoViewAdapter(photoUrls)
                view_pager.adapter = photoViewAdapter
            }
        }
    }
}


