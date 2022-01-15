package com.example.team_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class loadingdialog {
    private Activity activity;
    private AlertDialog alertDialog;

    loadingdialog(Activity myActivity){
        activity = myActivity;
    }

    void startloading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loadingdialog, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }

    void dismissDialog(){
        alertDialog.dismiss();
    }

}
