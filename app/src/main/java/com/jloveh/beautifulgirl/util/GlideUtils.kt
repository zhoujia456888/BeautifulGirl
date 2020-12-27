package com.jloveh.beautifulgirl.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.jloveh.beautifulgirl.R
import com.jloveh.beautifulgirl.activity.MainActivity.Companion.nvshensUrl


object GlideUtils {


    fun displayImageView(context: Context, imageView: ImageView, url: String) {

        Glide.with(context).load(buildGlideUrl(url)).diskCacheStrategy(DiskCacheStrategy.ALL).centerInside()
            .placeholder(R.mipmap.transparent).error(R.mipmap.ic_error).into(imageView);

    }

    fun buildGlideUrl(url: String): GlideUrl {
        return GlideUrl(
            url, LazyHeaders.Builder().addHeader("Referer", nvshensUrl)
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
                )
                .build()
        )
    }


}