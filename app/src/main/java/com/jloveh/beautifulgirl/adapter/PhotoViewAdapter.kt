package com.jloveh.beautifulgirl.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.jloveh.beautifulgirl.R
import com.jloveh.beautifulgirl.util.GlideUtils
import java.net.URL

class PhotoViewAdapter(photoList: MutableList<String>) : PagerAdapter() {

    var photoList = photoList


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var photoView = PhotoView(container.context)
        photoView.scaleType=ImageView.ScaleType.CENTER_INSIDE

        /*  var uri = Uri.parse(photoList[position])
          photoView.setImageURI(uri)*/

        GlideUtils.displayImageView(container.context ,photoView, photoList[position])


        /*Glide.with(photoView).load(Uri.parse(photoList[position])).placeholder(R.mipmap.transparent).error(R.mipmap.ic_error)
            .into(photoView)*/

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