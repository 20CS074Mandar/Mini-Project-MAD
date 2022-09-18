package com.example.miniproject

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView


class LoadingDialog {
    private  lateinit var activity:Activity
    private  lateinit var dialog: AlertDialog
    lateinit var loadString:String
    constructor(myActivity: Activity,loadText:String="Loading")
    {
        activity=myActivity
        loadString=loadText

    }
    fun startAlertDialog()
    {
        var builder=AlertDialog.Builder(activity)
        var inflater=activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_loading_dialogbox,null))
        builder.setCancelable(false)
        dialog=builder.create()
        dialog.show()
        dialog.window?.setLayout(500,500)
    }
    fun dismisDialog()
    {
        dialog.dismiss()
    }


}