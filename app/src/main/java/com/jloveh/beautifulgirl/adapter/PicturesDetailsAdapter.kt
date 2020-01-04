package com.jloveh.beautifulgirl.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jloveh.beautifulgirl.R

class PicturesDetailsAdapter(photoList: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_front_cover, photoList) {
    override fun convert(helper: BaseViewHolder, img_url: String?) {
        val joke_img = "http://dmimg.5054399.com/allimg/pkm/pk/22.jpg"
        Glide.with(context).load(img_url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
            .into(helper.getView(R.id.img_front_cover))
    }
}