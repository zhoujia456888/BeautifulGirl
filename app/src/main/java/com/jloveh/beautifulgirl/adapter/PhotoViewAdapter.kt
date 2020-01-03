package com.jloveh.beautifulgirl.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.jloveh.beautifulgirl.R
import java.net.URL

class PhotoViewAdapter(photoList: MutableList<String>) : PagerAdapter() {

    var photoList = photoList


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var photoView = PhotoView(container.context)

      /*  var uri = Uri.parse(photoList[position])
        photoView.setImageURI(uri)*/


        Glide.with(photoView).load(Uri.parse(photoList[position])).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
            .into(photoView)


        container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        return photoView

    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return photoList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}