package com.jloveh.beautifulgirl.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jloveh.beautifulgirl.R
import com.jloveh.beautifulgirl.util.GlideUtils

class PicturesDetailsAdapter(photoList: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_front_cover, photoList) {
    override fun convert(helper: BaseViewHolder, img_url: String) {
        val joke_img = "http://dmimg.5054399.com/allimg/pkm/pk/22.jpg"

        GlideUtils.displayImageView(context, helper.getView(R.id.img_front_cover), img_url)

        //Glide.with(context).load(img_url).placeholder(R.mipmap.transparent).error(R.mipmap.ic_error).into(helper.getView(R.id.img_front_cover))
    }
}