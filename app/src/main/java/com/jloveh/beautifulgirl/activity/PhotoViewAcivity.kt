package com.jloveh.beautifulgirl.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.LogUtils
import com.jloveh.beautifulgirl.R
import com.jloveh.beautifulgirl.activity.MainActivity.Companion.saiBanUserAgent
import com.jloveh.beautifulgirl.activity.PicturesDetailsActivity.Companion.photoBaseUrl
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

    var photoUrls = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photoview)

        var position = intent.getIntExtra("position", 0)
        photoUrls = intent.getStringArrayListExtra("photoUrls")

        txt_position.text = "$position/${photoUrls.size}"

        var photoViewAdapter = PhotoViewAdapter(photoUrls)
        view_pager.adapter = photoViewAdapter

        view_pager.currentItem = position


        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                txt_position.text = "$position/${photoUrls.size}"
            }
        })

    }
}


