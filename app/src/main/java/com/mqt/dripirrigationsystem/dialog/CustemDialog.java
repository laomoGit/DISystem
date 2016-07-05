package com.mqt.dripirrigationsystem.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.interfac.DialogCallbackListener;
import com.mqt.dripirrigationsystem.linechart.ChartValue;
import com.mqt.dripirrigationsystem.linechart.ChartValueSerie;
import com.mqt.dripirrigationsystem.linechart.LineChartView;
import com.mqt.dripirrigationsystem.utils.LogInfo;

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


        builder.setView(view)
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

    public DatePickerDialog createDateDialog(String title,final DialogCallbackListener listener){
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

   /* public Dialog createChartDialog(final DialogCallbackListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_line_chart,null);
        if(view == null){
            return null;
        }

        LineChartView lineChartView = (LineChartView)view.findViewById(R.id.line_chart);
        ChartValueSerie rr = new ChartValueSerie(Color.RED,1);
        rr.addPoint(new ChartValue("jan",99));
        rr.addPoint(new ChartValue("feb",80));
        rr.addPoint(new ChartValue("mar",30));
        rr.addPoint(new ChartValue("apr",99));
        rr.addPoint(new ChartValue("may",80));
        rr.addPoint(new ChartValue("jun",50));
        rr.addPoint(new ChartValue("jul",20));
        rr.addPoint(new ChartValue("aug",50));
        rr.addPoint(new ChartValue("sep",80));

        lineChartView.addSerie(rr);


        builder.setView(lineChartView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //listener.onPositiveButton(null);
            }
        });

        return builder.create();
    }*/

    public Dialog creatPatternDialog(final View view, final Node node, final DialogCallbackListener listener){
        boolean isAuto = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);


        builder.setView(view);
        builder.setPositiveButton("上传", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               listener.onPositiveButton(view);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegativeButton(view);
            }
        });

        return builder.create();

    }

    public TimePickerDialog createTimeDialog(int hour, int minute, TimePickerDialog.OnTimeSetListener callback){
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,callback,hour,minute,true);

        return timePickerDialog;
    }

}
