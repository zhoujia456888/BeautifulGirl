package com.jloveh.beautifulgirl.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.jloveh.beautifulgirl.R
import com.jloveh.beautifulgirl.adapter.FrontCoverAdapter
import com.jloveh.beautifulgirl.base.BaseActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class MainActivity : BaseActivity() {

    companion object {


        var nvshensUrl = "https://m.nvshens.net/gallery/"
        val saiBanUserAgent =
            "Nokia5320/04.13 (SymbianOS/9.3; U; Series60/3.2 Mozilla/5.0; Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML, like Gecko) Safari/413\n"
    }

    var page = 1

    var frontCoverAdapter = FrontCoverAdapter(Elements())

    lateinit var ckInitems: Elements
    var isFirstEnter = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        rc_gril_front_cover.layoutManager = staggeredGridLayoutManager

        rc_gril_front_cover.adapter = frontCoverAdapter

        frontCoverAdapter.setOnItemClickListener { adapter, view, position ->
            val url = frontCoverAdapter.data[position].select("a.ck-link").attr("abs:href")
            var intent = Intent(this@MainActivity, PhotoViewAcivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }

        if (isFirstEnter) {
            isFirstEnter = false
            refreshlayout.autoRefresh()//第一次进入触发自动刷新，演示效果
        }

        //下拉刷新
        refreshlayout.setOnRefreshListener { refreshLayout ->
            refreshLayout.finishRefresh(2000)
            page = 1
            analyzeData()
        }

        //上拉加载
        refreshlayout.setOnLoadMoreListener { refreshLayout ->
            refreshLayout.finishLoadMore(2000)
            page += 1
            analyzeData()
        }
    }

    private fun analyzeData() {

        var url = "$nvshensUrl$page.html"
        LogUtils.e(url)

        GlobalScope.launch(Dispatchers.IO) {
            val doc: Document = Jsoup.connect(url).userAgent(saiBanUserAgent).get()

            ckInitems = doc.select("div.ck-initem")

            /* for (ck_initem in ckInitems) {
                 LogUtils.e(ck_initem.select("a.ck-link").attr("abs:href"))
                 LogUtils.e(ck_initem.select("mip-img").attr("abs:src"))
             }*/

            withContext(Dispatchers.Main) {
                if (page == 1) {
                    frontCoverAdapter.setNewData(ckInitems)
                } else {
                    frontCoverAdapter.addData(ckInitems)
                }
            }
        }


    }


}
