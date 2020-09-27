package com.example.materialdesigntest.activites

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesigntest.R
import com.example.materialdesigntest.apadter.RecyclerViewAdapter
import com.example.materialdesigntest.view.ItemTouchHelperCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlin.concurrent.thread

class RecyclerViewActivity : BaseActivity() {

    lateinit var adapter: RecyclerViewAdapter

    private val data = ArrayList<String>()
    private var loadTimes = 0
    private var loading = false
    var insertData = "0"
    var color = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setToolbar(toolbar)
        val layoutManager = LinearLayoutManager(this)
        vRecyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter(this)
        vRecyclerView.adapter = adapter
        initData()
        adapter.addHeader()
        adapter.setItems(data)
        adapter.addFooter()
        eFab.setOnClickListener {
            adapter.addItem(layoutManager.findFirstVisibleItemPosition() + 1, insertData)
        }
        val callback = ItemTouchHelperCallback(adapter)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(vRecyclerView)
        swipeRefreshLayout.setColorSchemeResources(
            R.color.google_blue,
            R.color.google_green,
            R.color.google_red,
            R.color.google_yellow
        )
        swipeRefreshLayout.setOnRefreshListener {
            thread {
                Thread.sleep(2000)
                if (color > 4) color = 0
                runOnUiThread {
                    adapter.setColors(++color)
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }
        vRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) eFab.shrink()
                else eFab.extend()

                if (!loading && layoutManager.itemCount == layoutManager.findLastVisibleItemPosition() + 1) {
                    loadMoreData()
                    loading = true
                }
            }
        })
    }

    private fun initData() {
        data.clear()
        (1..10).forEach {
            data.add("$it")
        }
    }

    private fun loadMoreData() {
        thread {
            Thread.sleep(1500)
            runOnUiThread {
                if (loadTimes <= 5) {
                    adapter.removeFooter()
                    loading = false
                    adapter.addItems(data)
                    adapter.addFooter()
                    loadTimes++
                } else {
                    adapter.removeFooter()
                    Snackbar.make(vRecyclerView, "No more data", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}

