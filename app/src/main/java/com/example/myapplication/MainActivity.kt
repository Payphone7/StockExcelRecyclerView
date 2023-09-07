package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.payphone.stockexcel.ExcelRecyclerView

class MainActivity : AppCompatActivity() {

    private val testAdapter = TestAdapter()
    private val recyclerView: ExcelRecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    private val list = mutableListOf<StockBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testAdapter.setList(list)
        recyclerView.notifyDataSetChanged()
        recyclerView.setExcelAdapter(testAdapter)
        setList()
    }

    private fun setList() {
        for (i in 0..100) {
            val beanFG = StockBean()

            if(i % 4 == 0){
                beanFG.name = "中银主题策略混合A"
                beanFG.isShowTip = false
                beanFG.code = "163822"
            }else if (i % 4 == 1){
                beanFG.name = "富国中证500指数增强(LOF)A"
                beanFG.code = "161017"

            }else{
                beanFG.name = "招商中证白酒指数(LOF)A"
                beanFG.code = "161725"
            }
            beanFG.jingzhi = "2.2130"
            beanFG.guzhi = "2.1898"
            beanFG.tianjaihoushouyi = 0.14
            beanFG.jinyizhou = 0.41
            beanFG.jinyiyue = -4.69
            beanFG.jinsanyue = -1.12
            beanFG.jinliuyue = -5.47
            beanFG.tianjaihoushouyi = 2.26
            beanFG.jinnianlai = -5.18
            beanFG.jinyinian = -16.84
            beanFG.jinliangnian = -0.36
            beanFG.jinsannian = -0.36
            beanFG.jinwunian = 49.41
            beanFG.chenglilai
            list.add(beanFG)
        }
    }


}