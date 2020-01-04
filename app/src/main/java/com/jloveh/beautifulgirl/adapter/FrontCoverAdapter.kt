package com.jloveh.beautifulgirl.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jloveh.beautifulgirl.R
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class FrontCoverAdapter(ckInitems: Elements) :
    BaseQuickAdapter<Element, BaseViewHolder>(R.layout.item_front_cover, ckInitems) {
    override fun convert(helper: BaseViewHolder, ckInitem: Element?) {

        val mip_img = ckInitem!!.select("mip-img").attr("abs:src")

        val joke_img = "http://dmimg.5054399.com/allimg/pkm/pk/22.jpg"
        Glide.with(context).load(mip_img).placeholder(R.mipmap.transparent).error(R.mipmap.ic_error).into(helper.getView(R.id.img_front_cover))


      /*  val title = ckInitem.select("div.ck-gallery-title").text()
        helper.setText(R.id.txt_title, title)*/
    }
}