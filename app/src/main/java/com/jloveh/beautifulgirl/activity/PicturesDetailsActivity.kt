package com.jloveh.beautifulgirl.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.jloveh.beautifulgirl.R
import com.jloveh.beautifulgirl.adapter.PicturesDetailsAdapter
import com.jloveh.beautifulgirl.base.BaseActivity
import kotlinx.android.synthetic.main.activity_pictures_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.text.DecimalFormat

class PicturesDetailsActivity : BaseActivity() {

    companion object {
        val photoBaseUrl = "https://t1.onvshen.com:85/gallery/"
    }

    var picturesDetailsAdapter = PicturesDetailsAdapter(ArrayList())

    lateinit var url: String
    lateinit var phptosNo: String
    var isFirstEnter = true

    var photoUrls = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures_details)


        url = intent.getStringExtra("url")
        //获图片组编号
        phptosNo = url.split("/")[4]

        var staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        rc_pictures_details.layoutManager = staggeredGridLayoutManager

        rc_pictures_details.adapter = picturesDetailsAdapter

        picturesDetailsAdapter.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(this, PhotoViewAcivity::class.java)
            intent.putExtra("position", position)
            intent.putStringArrayListExtra("photoUrls", photoUrls as ArrayList<String>)
            startActivity(intent)
        }

        if (isFirstEnter) {
            isFirstEnter = false
            refreshlayout_pictures_details.autoRefresh()//第一次进入触发自动刷新，演示效果
        }


        refreshlayout_pictures_details.setOnRefreshListener {
            analyzeData()
        }


    }

    private fun analyzeData() {
        GlobalScope.launch(Dispatchers.IO) {
            val doc: Document = Jsoup.connect(url).userAgent(MainActivity.saiBanUserAgent).get()
            val ck_parent_divs = doc.select("div.ck-parent-div")

            //获取第一张图片的地址
            val photoOneUrl = ck_parent_divs[0].select("img[src]").attr("src")

            //截取图片的头部
            val photoUrlHead = photoOneUrl.substring(0, photoOneUrl.lastIndexOf("/") + 1)

            //获取整个图片组的图片张数
            val pageSize = doc.select("span").text().split("张")[0].toInt()
            LogUtils.e(pageSize)

            for (index in 0 until pageSize) {
                //大于0的话就是3位数，不足3位补0
                var indexStr = index.toString()
                if (index > 0) {
                    indexStr = DecimalFormat("000").format(index)
                }
                var photoUrl = "$photoUrlHead$indexStr.jpg"
                LogUtils.e(photoUrl)
                photoUrls.add(photoUrl)
            }

            withContext(Dispatchers.Main) {
                picturesDetailsAdapter.setNewData(photoUrls)
                refreshlayout_pictures_details.finishRefresh()
            }

        }
    }
}