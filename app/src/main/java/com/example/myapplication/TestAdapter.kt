package com.example.myapplication

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stockexcel.library.adapter.ExcelAdapter

/**
 * create time : 2023/9/6 14:38
 * create by : xupengpeng
 */
class TestAdapter : ExcelAdapter() {
    private val titles = arrayOf(
        "净值",
        "估值",
        "添加后收益",
        "近一周",
        "近一月",
        "近三月",
        "近六月",
        "今年来",
        "近一年",
        "近两年",
        "近三年",
        "近五年",
        "成立来"
    )
    private var list: List<StockBean> = ArrayList()
    fun setList(list: List<StockBean>) {
        this.list = list
    }

    override fun showTipContentLayout(frameLayout: FrameLayout, row: Int): Boolean {
        val bean = list[row]
        if (bean.isShowTip) {
            val textView = TextView(frameLayout.context)
            textView.gravity = Gravity.CENTER_VERTICAL
            textView.text = "本基金加入自选以来下跌5.46%"
            val layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            frameLayout.addView(textView, layoutParams)
            return true
        }
        return super.showTipContentLayout(frameLayout, row)
    }

    fun onLeftTopTitleBindViewHolder(holder: RecyclerView.ViewHolder?) {
        if (holder is LTViewHolder) {
            holder.tvTitle.text = "沪深市场"
        }
    }

    override fun onContentItemBindViewHolder(
        holder: RecyclerView.ViewHolder,
        row: Int,
        column: Int
    ) {
        val bean = list[row]
        if (holder is ViewHolder) {
            when (column) {
                0 -> {
                    holder.tvTitle.text = bean.jingzhi
                }

                1 -> {
                    holder.tvTitle.text = bean.guzhi
                }

                2 -> {
                    holder.tvTitle.text = bean.tianjaihoushouyi.convert()
                    holder.tvTitle.setTextColor(bean.tianjaihoushouyi.colorConvert())
                }

                3 -> {
                    holder.tvTitle.text = bean.jinyizhou.convert()
                    holder.tvTitle.setTextColor(bean.jinyizhou.colorConvert())
                }

                4 -> {
                    holder.tvTitle.text = bean.jinyiyue.convert()
                    holder.tvTitle.setTextColor(bean.jinyiyue.colorConvert())
                }

                5 -> {
                    holder.tvTitle.text = bean.jinsanyue.convert()
                    holder.tvTitle.setTextColor(bean.jinsanyue.colorConvert())
                }

                6 -> {
                    holder.tvTitle.text = bean.jinliuyue.convert()
                    holder.tvTitle.setTextColor(bean.jinliuyue.colorConvert())
                }

                7 -> {
                    holder.tvTitle.text = bean.jinnianlai.convert()
                    holder.tvTitle.setTextColor(bean.jinnianlai.colorConvert())
                }

                8 -> {
                    holder.tvTitle.text = bean.jinyinian.convert()
                    holder.tvTitle.setTextColor(bean.jinyinian.colorConvert())
                }

                9 -> {
                    holder.tvTitle.text = bean.jinliangnian.convert()
                    holder.tvTitle.setTextColor(bean.jinliangnian.colorConvert())
                }

                10 -> {
                    holder.tvTitle.text = bean.jinsannian.convert()
                    holder.tvTitle.setTextColor(bean.jinsannian.colorConvert())
                }

                11 -> {
                    holder.tvTitle.text = bean.jinwunian.convert()
                    holder.tvTitle.setTextColor(bean.jinwunian.colorConvert())
                }

                12 -> {
                    holder.tvTitle.text = bean.chenglilai.convert()
                    holder.tvTitle.setTextColor(bean.chenglilai.colorConvert())
                }
            }
        }
    }

    override fun onLeftBindViewHolder(holder: RecyclerView.ViewHolder, row: Int) {
        val bean = list[row]
        if (holder is LViewHolder) {
            holder.tvTitle.text = bean.name
            holder.tvCode.text = bean.code


        }
    }

    override fun onTopTitleBindViewHolder(holder: RecyclerView.ViewHolder, column: Int) {
        val name = titles[column]
        if (holder is TViewHolder) {
            holder.tvTitle.text = name
        }
    }


    override val topItemCount: Int
        get() = titles.size


    override val columnItemCount: Int
        get() = list.size

    override fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_left_top, parent, false)
        val viewLeft =
            LayoutInflater.from(parent.context).inflate(R.layout.item_left, parent, false)

        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        when (viewType) {
            LEFT_TOP_ITEM -> return LTViewHolder(view)
            LEFT_ITEM -> return LViewHolder(viewLeft)
            TOP_ITEM -> return TViewHolder(viewItem)
            CENTER_ITEM -> return ViewHolder(viewItem)
        }
        return ViewHolder(viewItem)
    }

    internal inner class LTViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }

    internal inner class LViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvCode: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvCode = itemView.findViewById(R.id.tvCode)
        }
    }

    internal inner class TViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }
}