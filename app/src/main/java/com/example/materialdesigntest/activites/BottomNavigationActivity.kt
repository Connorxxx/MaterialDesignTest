package com.example.materialdesigntest.activites

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.materialdesigntest.R
import com.example.materialdesigntest.apadter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : BaseActivity() {
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        initView()
    }

    private fun initView() {
        setStatusBarColor(getColor(R.color.transparent))
        setToolbar(toolbar2)

        viewPager2 = findViewById(R.id.viewPagerBottomNavigation)
        val view1: View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view2: View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view3: View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val view4: View = layoutInflater.inflate(R.layout.item_view_pager, null)
        val viewList: MutableList<View> = ArrayList()
        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        viewList.add(view4)
        viewPager2.adapter = ViewPagerAdapter(viewList, this)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val evaluator = ArgbEvaluator()
                var evaluate = resources.getColor(R.color.app_blue)
                when (position) {
                    0 -> evaluate = evaluator.evaluate(
                        positionOffset,
                        resources.getColor(R.color.app_blue),
                        resources.getColor(R.color.app_green)
                    ) as Int
                    1 -> evaluate = evaluator.evaluate(
                        positionOffset,
                        resources.getColor(R.color.app_green),
                        resources.getColor(R.color.app_yellow)
                    ) as Int
                    2 -> evaluate = evaluator.evaluate(
                        positionOffset,
                        resources.getColor(R.color.app_yellow),
                        resources.getColor(R.color.app_red)
                    ) as Int
                    else -> evaluate = resources.getColor(R.color.app_red)
                }
                (viewPager2.parent as View).setBackgroundColor(evaluate)
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNavigationView.selectedItemId = R.id.menuBlue
                    1 -> bottomNavigationView.selectedItemId = R.id.menuGreen
                    2 -> bottomNavigationView.selectedItemId = R.id.menuYellow
                    3 -> bottomNavigationView.selectedItemId = R.id.menuRed
                }
            }
        })


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuBlue -> viewPager2.currentItem = 0
                R.id.menuGreen -> viewPager2.currentItem = 1
                R.id.menuYellow -> viewPager2.currentItem = 2
                R.id.menuRed -> viewPager2.currentItem = 3
            }
            true
        }
    }
}