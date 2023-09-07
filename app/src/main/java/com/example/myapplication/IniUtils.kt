package com.example.myapplication

import android.graphics.Color
import android.provider.CalendarContract.Colors

/**
 * create time : 2023/9/7 14:43
 * create by : xupengpeng
 */
fun Double.convert(): String {
    var stringBuffer = StringBuffer(this.toString())
    stringBuffer.append("%")
    return stringBuffer.toString()
}

fun Double.colorConvert(): Int {
    return if (this > 0){
        Color.RED
    }else if(this < 0){
        Color.GREEN
    }else{
        Color.BLACK
    }
}