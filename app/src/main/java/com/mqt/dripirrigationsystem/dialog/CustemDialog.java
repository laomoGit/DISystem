package com.mqt.dripirrigationsystem.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.interfac.DialogCallbackListener;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/6/21.
 */
public class CustemDialog {
    private Context mContext;

    public CustemDialog(Context context){
        this.mContext =context;

    }

    /**
     *
     * @param listener 回调处理点击监听器
     * @return 返回dialog
     */
    public Dialog createPressureDialog(final DialogCallbackListener listener) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.pressure_dialog,null);

        if(view == null){
            return null;
        }


        builder.setView(R.layout.pressure_dialog)
                .setPositiveButton("上传", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        listener.onPositiveButton(view);//回调处理
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listener.onNegativeButton(view);//回调处理
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public DatePickerDialog createStartDate(String title,final DialogCallbackListener listener){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               listener.onPositiveButton(view);
            }
        }, year, month, day);
        datePickerDialog.setTitle(title);
        return datePickerDialog;
    }

}
