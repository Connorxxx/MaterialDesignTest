package com.example.materialdesigntest.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.materialdesigntest.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_widgets.*


/**
 * A simple [Fragment] subclass.
 * Use the [WidgetsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WidgetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_widgets, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initChips()
    }

    private fun initChips() {
        val context = context ?: return
        chip4.setOnClickListener {
            if (chip4.isChecked) {
                chip4.chipBackgroundColor = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.colorAccent))
            } else {
                chip4.chipBackgroundColor = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.color_ripple)
                )
            }
        }
        chip5.setOnCloseIconClickListener {
            chip5.visibility = View.GONE
            Snackbar.make(it, "Chip is Gone", Snackbar.LENGTH_LONG)
                .setAction("Undo") {
                    chip5.visibility = View.VISIBLE
                }.show()
        }
    }
}