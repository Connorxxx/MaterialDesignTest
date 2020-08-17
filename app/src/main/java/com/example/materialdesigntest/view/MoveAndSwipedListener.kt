package com.example.materialdesigntest.view

interface MoveAndSwipedListener {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}