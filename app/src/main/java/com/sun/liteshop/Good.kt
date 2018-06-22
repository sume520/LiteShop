package com.sun.liteshop

import android.graphics.Bitmap
import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

data class Good(
        @Column(unique = true,defaultValue = "unknow")
        var name:String?=null,
        var price:Float=0f,
        var cost:Float=0f,
        var stock:Int=0,
        var image:Bitmap
):LitePalSupport()