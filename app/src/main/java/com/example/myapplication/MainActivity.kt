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
        for (i in 0..200) {
            val bean = StockBean()
            bean.current = "4000"
            bean.prisePercent = "5%"
            bean.speed = "0.5%"
            bean.total = "100000万亿"
            bean.time = "2020-10-10"
            if (i % 11 == 0) {
                bean.name =
                    "上证指数上证指数上证指数上证指数上证指数上证指数上证指数上证指数上证指数上证指数"
                bean.isShowTip = true
            } else {
                bean.name = "上证指数"
            }
            if (i % 19 == 0) {
                bean.isShowTip = true
            }
            list.add(bean)
        }

    }
}