package com.example.miniproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class lsl {
    Activity activity;
    AlertDialog dialog;
    lsl(Activity myActivity)
    {
        activity=myActivity;
    }
    void startAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading_dialogbox,null));
        builder.setCancelable(true);
        dialog=builder.create();
        dialog.show();

    }
    void dismisDialog()
    {
        dialog.dismiss();
    }
}
