package com.example.pushpopdemo.home.ui.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pushpopdemo.R
import com.example.pushpopdemo.databinding.HomeActivityBinding

class HomeActivity :AppCompatActivity(), View.OnClickListener{
    private lateinit var binding:HomeActivityBinding
    companion object {
        val TAG: String = HomeActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            Intent(activity, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }.run {
                activity.startActivity(this)

            }}
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.home_activity)
        binding.onClickLister=this
    }

    override fun onClick(v: View?) {
        showDialog()
    }
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.popup_dia_layout)
        val ph_no = dialog.findViewById(R.id.lbl_ph__number) as TextView
        val noBtn = dialog.findViewById(R.id.btn_save) as Button
        noBtn.setOnClickListener {
            binding.lblPhNumber.text=ph_no.toString()
            dialog.dismiss()
        }

        dialog.show()
    }
}