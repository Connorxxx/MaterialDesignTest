package com.example.materialdesigntest.apadter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesigntest.R
import com.example.materialdesigntest.view.MoveAndSwipedListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), MoveAndSwipedListener {

    private val itemList = ArrayList<String>()

    private val typeNormal = 1
    private val typeFooter = 2
    private val typeHeader = 3
    private val footer = "footer"
    private val header = "header"

    private lateinit var parentView: View

    var color: Int = 0

    fun addHeader() {
        itemList.add(header)
        notifyItemInserted(itemList.size - 1)
    }

    fun addFooter() {
        itemList.add(footer)
        notifyItemInserted(itemList.size - 1)
    }

    fun setItems(data: List<String>) {
        itemList.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(position: Int, insertData: String) {
        itemList.add(position, insertData)
        notifyItemInserted(position)
    }

    fun addItems(data: List<String>) {
        itemList.add(header)
        itemList.addAll(data)
        notifyItemInserted(itemList.size - 1)
    }

    fun removeFooter() {
        itemList.removeAt(itemList.size - 1)
        notifyItemRemoved(itemList.size)
    }

    fun setColors(color: Int) {
        this.color = color
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val round: ConstraintLayout = view.round
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val headerText = view.headerText
    }

    inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       // val progress = view.progressBarLoadMore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        parentView = parent
        when (viewType) {
            typeNormal -> {
                val view = LayoutInflater.from(context).inflate(
                    R.layout.item_recycler_view,
                    parent, false
                )
                return RecyclerViewHolder(view)
            }
            typeFooter -> {
                val view = LayoutInflater.from(context).inflate(
                    R.layout.item_recycler_footer,
                    parent, false
                )
                return FooterViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(
                    R.layout.item_recycler_header,
                    parent, false
                )
                return HeaderViewHolder(view)
            }
        }
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerViewHolder) {

            val animation =
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.anim_recycler_item_show
                )
            holder.itemView.startAnimation(animation)

            val aa1 = AlphaAnimation(1.0f, 0.1f)
            aa1.duration = 400
            holder.round.startAnimation(aa1)

            val aa = AlphaAnimation(0.1f, 1.0f)
            aa.duration = 400

            when (color) {
                1 -> holder.round.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.google_blue))
                2 -> holder.round.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.google_green))
                3 -> holder.round.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.google_yellow))
                4 -> holder.round.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.google_red))
                else -> holder.round.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(R.color.gray))
            }

            holder.round.startAnimation(aa)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            header -> typeHeader
            footer -> typeFooter
            else -> typeNormal
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(itemList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
        Snackbar.make(parentView, "Dismissed", Snackbar.LENGTH_LONG).setAction("Undo") {
            addItem(position, itemList[position])
        }.show()
    }

}


