package com.example.myapplication

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
    private val titles = arrayOf("现价", "涨幅", "涨速", "自选时间", "市盈率")
    private var list: List<StockBean> = ArrayList()
    fun setList(list: List<StockBean>) {
        this.list = list
    }

    override fun showTipContentLayout(frameLayout: FrameLayout, row: Int): Boolean {
        val bean = list[row]
        if (bean.isShowTip) {
            val textView = TextView(frameLayout.context)
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
            val viewHolder = holder
            when (column) {
                0 -> viewHolder.tvTitle.text = bean.current
                1 -> viewHolder.tvTitle.text = bean.prisePercent
                2 -> viewHolder.tvTitle.text = bean.speed
                3 -> viewHolder.tvTitle.text = bean.time
                4 -> viewHolder.tvTitle.text = bean.total
            }
        }
    }

    override fun onLeftBindViewHolder(holder: RecyclerView.ViewHolder, row: Int) {
        val bean = list[row]
        if (holder is LViewHolder) {
            holder.tvTitle.text = bean.name
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
        when (viewType) {
            LEFT_TOP_ITEM -> return LTViewHolder(view)
            LEFT_ITEM -> return LViewHolder(viewLeft)
            TOP_ITEM -> return TViewHolder(view)
            CENTER_ITEM -> return ViewHolder(viewLeft)
        }
        return ViewHolder(view)
    }

    internal inner class LTViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }

    internal inner class LViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
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