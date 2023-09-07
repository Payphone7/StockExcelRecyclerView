package com.example.myapplication

/**
 * create time : 2023/9/7 16:42
 * create by : xupengpeng
 */
class Test {

    fun test(block : ()-> Unit){
        block
    }


}

fun test2(int: Int){}

fun  main(){
    var test = Test()
    test.test { test2(1) }


    test.test ({ test2(1) })
}