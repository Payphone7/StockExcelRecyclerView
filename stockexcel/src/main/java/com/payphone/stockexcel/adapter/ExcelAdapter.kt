package com.stockexcel.library.adapter

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * create time : 2023/9/6 13:39
 * create by : xupengpeng
 */
abstract class ExcelAdapter {
    open fun showTipContentLayout(viewGroup: FrameLayout, row: Int): Boolean {
        return false
    }

    abstract fun onContentItemBindViewHolder(
        holder: RecyclerView.ViewHolder,
        row: Int,
        column: Int
    )

    abstract fun onLeftBindViewHolder(holder: RecyclerView.ViewHolder, row: Int)
    abstract fun onTopTitleBindViewHolder(holder: RecyclerView.ViewHolder, column: Int)
    
    abstract val topItemCount: Int
    abstract val columnItemCount: Int
    abstract fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    companion object {
        const val LEFT_TOP_ITEM = 1
        const val TOP_ITEM = 2
        const val LEFT_ITEM = 3
        const val CENTER_ITEM = 4
    }
}