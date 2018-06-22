package com.sun.liteshop.fragments


import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sun.liteshop.R
import okhttp3.OkHttpClient
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.jude.rollviewpager.RollPagerView
import com.jude.rollviewpager.adapter.DynamicPagerAdapter
import com.jude.rollviewpager.adapter.LoopPagerAdapter
import com.jude.rollviewpager.adapter.StaticPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import okhttp3.Request
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.toast


class HomeFragment : Fragment() {
    internal lateinit var view:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view= inflater.inflate(R.layout.fragment_home, container, false)
        init()
        return view
    }

    private fun init(){
        setImage()
    }

    private fun setImage(){
        view.rp_view.setAdapter(RollPagerAddapter(view.rp_view))
        view.rp_view.setOnItemClickListener {
            toast("you click the picture of $it")
        }
    }

    private inner class RollPagerAddapter(rollPagerView: RollPagerView): LoopPagerAdapter(rollPagerView){
        override fun getRealCount(): Int {
            return images.size
        }

        var images=getImage()

        override fun getView(container: ViewGroup?, position: Int): View {
            var imageview = ImageView(container!!.context)
            imageview.setImageBitmap(images[position])
            imageview.scaleType=ImageView.ScaleType.CENTER_CROP
            imageview.layoutParams=ViewGroup
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            return imageview
        }
    }

    private fun getImage(): Array<Bitmap?> {
        var images= arrayOfNulls<Bitmap>(3)
        /*var urls= arrayListOf<String>(
                "http://www.bkjia.com/uploads/allimg/180209/20205049D-0.jpg",
                "http://www.bkjia.com/uploads/allimg/180209/20205049D-0.jpg",
                "http://www.bkjia.com/uploads/allimg/180209/20205049D-0.jpg"
        )
        var num=0
        for(url in urls){
            Thread {
                val client = OkHttpClient()
                //获取请求对象
                val request = Request.Builder().url(url).build()
                //获取响应体
                val body = client.newCall(request).execute().body()
                //获取流
                val `in` = body!!.byteStream()
                //转化为bitmap
                val bitmap = BitmapFactory.decodeStream(`in`)
                onUiThread { images[num++]=bitmap }
            }.start()
        }*/

        images[0]=BitmapFactory.decodeResource(resources,R.drawable.mix2s)
        images[1]=BitmapFactory.decodeResource(resources,R.drawable.mi8)
        images[2]=BitmapFactory.decodeResource(resources,R.drawable.iphonex)

        return images
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                HomeFragment()
    }

}
