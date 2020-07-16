package com.example.materialdesigntest.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.materialdesigntest.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_dialogs.*
import java.text.DateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [DialogsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogsFragment : Fragment() {
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialogs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBtnDialog()
    }

    private fun initBtnDialog() {
        val context = this.context ?: return
        btn_dialog_1.setOnClickListener {
            val singleChoiceItems = resources
                .getStringArray(R.array.dialog_choice_array)
            val itemSelected = 0
            AlertDialog.Builder(context)
                .setTitle(R.string.single_choice_dialog)
                .setSingleChoiceItems(singleChoiceItems, itemSelected,
                    DialogInterface.OnClickListener { _, _ ->

                    })
                .setPositiveButton("Ok") { _, _ ->

                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
        btn_dialog_2.setOnClickListener {
            val multiChoiceItems = resources
                .getStringArray(R.array.dialog_choice_array)
            val checkedItems = booleanArrayOf(true, false, false, false, false)
            AlertDialog.Builder(context)
                .setTitle(R.string.multi_choice_dialog)
                .setMultiChoiceItems(multiChoiceItems, checkedItems, null)
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .show()
        }
        btn_dialog_3.setOnClickListener {
            if (progressBar.visibility == View.VISIBLE) {
                progressBar.visibility = View.GONE
            } else {
                progressBar.visibility = View.VISIBLE
            }
        }
        btn_dialog_4.setOnClickListener {
                progressBar2.visibility = View.VISIBLE
            progressBar2.progress += 10
            if (progressBar2.progress == 100) {
                progressBar2.visibility = View.GONE
                progressBar2.progress = 0
            }
        }
        btn_dialog_5.setOnClickListener {
            calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                context,
                OnDateSetListener { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date =
                        DateFormat.getDateInstance(DateFormat.MEDIUM)
                            .format(calendar.time)
                    btn_dialog_5.text = date
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
        btn_dialog_6.setOnClickListener {
            calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    val time: String =
                        DateFormat.getTimeInstance(DateFormat.SHORT)
                            .format(calendar.time)
                    btn_dialog_6.text = time
                },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
            )
            timePickerDialog.show()
        }
        btn_dialog_7.setOnClickListener {
            val mBottomSheetDialog = BottomSheetDialog(context)
            val dialogView = requireActivity().layoutInflater.inflate(
                R.layout.dialog_bottom_sheet, null)
            val imgBottomDialog = dialogView
                .findViewById<ImageView>(R.id.img_bottom_dialog)
            val btnBottomDialogOk = dialogView
                     .findViewById<Button>(R.id.btn_dialog_bottom_sheet_ok)
            val btnBottomDialogCancel = dialogView
                .findViewById<Button>(R.id.btn_dialog_bottom_sheet_cancel)
            Glide.with(context).load(R.drawable.bottom_dialog).into(imgBottomDialog)
                mBottomSheetDialog.setContentView(dialogView)
            btnBottomDialogOk.setOnClickListener {
                mBottomSheetDialog.dismiss()
            }
            btnBottomDialogCancel.setOnClickListener {
                mBottomSheetDialog.dismiss()
            }
            mBottomSheetDialog.show()
        }
        btn_dialog_8.setOnClickListener {
            val fullscreenDialog = Dialog(context, R.style.DialogFullscreen)
            fullscreenDialog.setContentView(R.layout.dialog_fullscreen)
            val imgFullScreenDialog = fullscreenDialog
                .findViewById<ImageView>(R.id.img_full_screen_dialog)
            Glide.with(context).load(R.drawable.google_assistant).into(imgFullScreenDialog)
            val toolbarFullScreenDialog = fullscreenDialog
                .findViewById<Toolbar>(R.id.toolbar_full_screen_dialog)
            toolbarFullScreenDialog.setNavigationOnClickListener {
                fullscreenDialog.dismiss()
            }
            fullscreenDialog.show()
        }
        btn_dialog_9.setOnClickListener {
            val popupMenu = PopupMenu(context, btn_dialog_9)
            popupMenu.menuInflater.inflate(R.menu.popup_menu_main, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.popup_menu_action_1 -> {
                        Snackbar.make(it, "Make", Snackbar.LENGTH_SHORT).show()
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
            }
    }


}


