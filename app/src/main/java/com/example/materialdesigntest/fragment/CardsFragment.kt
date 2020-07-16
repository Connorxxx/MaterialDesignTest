package com.example.materialdesigntest.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.materialdesigntest.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.card_1.*
import kotlinx.android.synthetic.main.card_3.*


/**
 * A simple [Fragment] subclass.
 * Use the [CardsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cards, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cardOnAction()
    }

    private fun cardOnAction() {
        btn_card_1_action1.setOnClickListener {
            context?.let { context ->
                AlertDialog.Builder(context).apply {
                    setTitle("This is Dialog")
                    setMessage("Something important.")
                    setCancelable(false)
                    setPositiveButton("Ok") { dialog, which ->

                    }
                    setNegativeButton("Cancel") { dialog, which ->
                        dialog.cancel()
                    }
                    setNeutralButton("Neutral") { dialog, which ->

                    }
                    show()
                }

            }
//            Snackbar.make(it,"Card_1 Action1", Snackbar.LENGTH_SHORT).show()
        }
        btn_card_1_action2.setOnClickListener {
            Snackbar.make(it, "Card_1 Action2", Snackbar.LENGTH_SHORT).show()
        }
        btn_card_3_action_1.setOnClickListener {
            Snackbar.make(it, "Card_3 Action1", Snackbar.LENGTH_SHORT).show()
        }
        btn_card_3_action_2.setOnClickListener {
            Snackbar.make(it, "Card_3 Action2", Snackbar.LENGTH_SHORT).show()
        }
    }
}