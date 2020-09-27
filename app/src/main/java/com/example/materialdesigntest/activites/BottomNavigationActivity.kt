package com.example.materialdesigntest.activites

import android.os.Bundle
import android.view.View
import com.example.materialdesigntest.R
import com.example.materialdesigntest.apadter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import kotlinx.android.synthetic.main.item_view_pager.*


class BottomNavigationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        initView()
    }

    private fun initView() {
        setStatusBarColor(getColor(R.color.transparent))
        setToolbar(toolbar2)

        val view1 :View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view2 :View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view3 :View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view4 :View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val viewList: MutableList<View> = ArrayList()
        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        viewList.add(view4)
        viewPager2.adapter = ViewPagerAdapter(viewList)
    }
}