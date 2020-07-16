package com.example.materialdesigntest.activites

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.materialdesigntest.R
import com.example.materialdesigntest.apadter.FragmentAdapter
import com.example.materialdesigntest.fragment.CardsFragment
import com.example.materialdesigntest.fragment.DialogsFragment
import com.example.materialdesigntest.fragment.WidgetsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    lateinit var drawer: DrawerLayout
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initNavigationView()
        initDrawerLayout()
        initTabLayoutWithViewPager2()
        initFab()
        //Test Commit....
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_main_about -> {
                searchPlaceEdit.apply {
                    val imm = searchPlaceEdit.context
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        requestFocus()
                        postDelayed({
                            kotlin.run {
                                imm.showSoftInput(searchPlaceEdit, 0)
                            }
                        }, 100)
                    } else {
                        visibility = View.GONE
                        imm.hideSoftInputFromWindow(this.windowToken, 0)
                    }
                }



            }
            R.id.action_menu_main_donations -> Toast.makeText(this,
                "Donations", Toast.LENGTH_SHORT).show()
            R.id.action_menu_main_my_app -> Toast.makeText(this,
                "My Apps", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initFab() {
        fab = fab_main
        fab.setOnClickListener {
            Snackbar.make(it, "You clicked snack bar", Snackbar.LENGTH_LONG)
                .setAction("Okay") {
                    // do something here...
                }.show()
        }
    }

    private fun initNavigationView() {
        nav_view.setNavigationItemSelectedListener {
            true
        }
        val headerView: View = nav_view.getHeaderView(0)
        val navHeader: ConstraintLayout = headerView.findViewById(R.id.nav_header)
        val iconView: CircleImageView = headerView.findViewById(R.id.icon_image)
        navHeader.setOnClickListener {
            Toast.makeText(this, "You click nav_view", Toast.LENGTH_SHORT).show()
        }
        iconView.setOnClickListener {
            Toast.makeText(this, "You clicked image.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initDrawerLayout() {
        drawer = drawer_layout
        val toggle =  ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }


    private fun initTabLayoutWithViewPager2() {
        view_pager_main.offscreenPageLimit = 2
        val titles = ArrayList<String>().apply {
            add("Cards")
            add("Dialogs")
            add("widgets")
        }
//        tab_layout_main.apply {
//            titles.forEach {
//                addTab(this.newTab().setText(it))
//            }
//        }
        val fragments = ArrayList<Fragment>().apply {
            add(CardsFragment())
            add(DialogsFragment())
            add(WidgetsFragment())
        }
        val fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle, fragments, titles)
        view_pager_main.adapter = fragmentAdapter
        TabLayoutMediator(tab_layout_main, view_pager_main) { tab, position ->
            tab.text = titles[position]
        }.attach()

        view_pager_main.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback () {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    if (position == 2) {
                        fab.show()
                    } else {
                        fab.hide()
                    }
                }
            })
    }
}
