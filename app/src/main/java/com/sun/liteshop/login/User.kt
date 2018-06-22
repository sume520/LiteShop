package com.sun.liteshop.login

import android.graphics.Bitmap
import org.litepal.crud.LitePalSupport

class User(
        var account:String?=null,
        var nickname:String?=null,
        var balance:Float=0f,
        var person_image:Bitmap):LitePalSupport()