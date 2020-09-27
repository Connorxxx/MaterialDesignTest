package com.example.materialdesigntest.apadter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesigntest.R


class ViewPagerAdapter(private val viewList: List<View>, val context: Context) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private val colorArray = intArrayOf(
        R.color.google_blue,
        R.color.google_green,
        R.color.google_yellow,
        R.color.google_red
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coordinatorLayout: CoordinatorLayout = view.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_view_pager, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.coordinatorLayout.setBackgroundResource(colorArray[position])
    }

    override fun getItemCount() = viewList.size
}