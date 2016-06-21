package com.mqt.dripirrigationsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.interfac.DialogCallbackListener;

/**
 * Created by Administrator on 2016/6/21.
 */
public class CustemAlertDialog {
    private String dialogTitle;
    private String msg;
    private DialogCallbackListener listener;

    private Context mContext;

    public CustemAlertDialog(Context context){
        this.mContext =context;

    }

    public Dialog createPressureDialog(final DialogCallbackListener listener) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(R.layout.pressure_dialog)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        //发送请求
                        Log.i("tag","------>发送请求");
                        listener.onPositiveButton();//回调处理
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listener.onNegativeButton();//回调处理
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
