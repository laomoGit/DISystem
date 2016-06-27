package com.mqt.dripirrigationsystem.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.dialog.CustemDialog;
import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.interfac.DialogCallbackListener;
import com.mqt.dripirrigationsystem.linechart.ChartValue;
import com.mqt.dripirrigationsystem.linechart.ChartValueSerie;
import com.mqt.dripirrigationsystem.linechart.LineChartFragment;
import com.mqt.dripirrigationsystem.linechart.LineChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class NodeDetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //声明赋值的控件
    Button bt_Pressure;
    TextView tv_SensorT1Value;
    TextView tv_SensorT2Value;
    TextView tv_SensorH1Value;
    TextView tv_SensorH2Value;
    TextView tv_UsePattern;
    Button bt_EndData;
    Button bt_StarData;
    Spinner sp_Type;
    Button bt_query;
    LineChartView lcv_lineChart;
    ChartValueSerie redLineValues;
    ChartValueSerie greenLineValues;
    Node node;

    private List<String> type_list;
    private ArrayAdapter<String>type_adapter;

    private CustemDialog dialog;
    private LineChartFragment lineChartFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.node_details);
        //获得Node
        getNdoe();
        dialog = new CustemDialog(NodeDetailActivity.this);
        //初始化控件
        initWidget();
    }

    private void initWidget() {
        //setTitle(node.getValveName());
        //返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bt_Pressure = (Button) findViewById(R.id.pressure_variate);
        //点击事件
        bt_Pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPressureDialog();
            }
        });


        tv_SensorT1Value = (TextView) findViewById(R.id.soil_temperature_variate);
        tv_SensorT2Value = (TextView) findViewById(R.id.soil_temperature_variate2);
        tv_SensorH1Value = (TextView) findViewById(R.id.soil_humidity_variate);
        tv_SensorH2Value = (TextView) findViewById(R.id.soil_humidity_variate2);
        tv_UsePattern = (TextView) findViewById(R.id.use_pattern_variate);
        lcv_lineChart = (LineChartView)findViewById(R.id.node_line_chart);
        initLineChart();
        sp_Type = (Spinner) findViewById(R.id.sp_type);
        bt_query = (Button) findViewById(R.id.query_button);
        bt_StarData = (Button) findViewById(R.id.start_time_bt);
        bt_StarData.setOnClickListener(this);
        bt_EndData = (Button) findViewById(R.id.end_time_bt);
        bt_EndData.setOnClickListener(this);
        //控件赋值
        setView();

        //温湿度传感器分组
        type_list = new ArrayList<String>();
        type_list.add("1组");
        type_list.add("2组");
        //适配器
        type_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type_list);
        //设置样式
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp_Type.setAdapter(type_adapter);
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valveId = node.getSysId();
                int sensorTypeId = 3;
                Toast.makeText(NodeDetailActivity.this,"haha",Toast.LENGTH_SHORT).show();
                //manager = HistroyManager.getInstanc();
               // manager.clean();
               // manager.request(NodeDetailsActivity.this, callback, UrlConfig.BASEURL + "GetAllHisDataJson",
                        //"valveId=" + valveId + "&" + "sensorTypeId=" + sensorTypeId + "&" + "startTime=" + tx_StarData.getText() + "&" + "endTime=" + tx_EndData.getText());

               /* LineChartFragment lineChartFragment = new LineChartFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                //transaction.replace(R.id.spread_line_chart,lineChartFragment);
                transaction.commit();*/
                lcv_lineChart.deleteSerie(redLineValues);
            }
        });

    }

    private void initLineChart() {
        redLineValues = new ChartValueSerie(Color.RED,1);
        redLineValues.addPoint(new ChartValue("jan",99));
        redLineValues.addPoint(new ChartValue("feb",80));
        redLineValues.addPoint(new ChartValue("mar",30));
        redLineValues.addPoint(new ChartValue("apr",99));
        redLineValues.addPoint(new ChartValue("may",80));
        redLineValues.addPoint(new ChartValue("jun",50));
        redLineValues.addPoint(new ChartValue("jul",20));
        redLineValues.addPoint(new ChartValue("aug",50));
        redLineValues.addPoint(new ChartValue("sep",80));

        greenLineValues = new ChartValueSerie(Color.GREEN,2);
        greenLineValues.addPoint(new ChartValue("jan",99));
        greenLineValues.addPoint(new ChartValue("feb",80));
        greenLineValues.addPoint(new ChartValue("mar",46));
        greenLineValues.addPoint(new ChartValue("apr",56));
        greenLineValues.addPoint(new ChartValue("may",80));
        greenLineValues.addPoint(new ChartValue("jun",50));
        greenLineValues.addPoint(new ChartValue("jul",78));
        greenLineValues.addPoint(new ChartValue("aug",50));
        greenLineValues.addPoint(new ChartValue("sep",80));

        lcv_lineChart.addSerie(redLineValues);
        lcv_lineChart.addSerie(greenLineValues);
    }

    private void showPressureDialog() {
        //如果改变水压值，弹出对话框，是否上传服务端
        dialog.createPressureDialog(new DialogCallbackListener() {
            @Override
            public void onPositiveButton(View view) {
                Toast.makeText(NodeDetailActivity.this,"haahh",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegativeButton(View view) {

            }

        }).show();
    }

    private void setView() {

        // tv_Status.setText(node.Status+"");
        bt_Pressure.setText(Integer.toString(node.getPressure()) + "KPa");
        tv_SensorT1Value.setText(node.getSensorT1Value() + "℃");
        tv_SensorT2Value.setText(node.getSensorT2Value() + "℃");
        tv_SensorH1Value.setText(node.getSensorH1Value() + "%");
        tv_SensorH2Value.setText(node.getSensorH2Value() + "%");
        if (node.getUsePattern()) {
            tv_UsePattern.setText("自动");
        } else {
            tv_UsePattern.setText("手动");
        }
    }

    private void getNdoe() {
        Bundle bundle = getIntent().getExtras();
        node = (Node) bundle.getSerializable("node");
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_time_bt:
                dialog.createDateDialog("设置开始时间", new DialogCallbackListener() {
                    @Override
                    public void onPositiveButton(View view) {
                        DatePicker datePicker = (DatePicker) view;
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        Toast.makeText(NodeDetailActivity.this,"---"+year,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegativeButton(View view) {

                    }
                }).show();
                break;
            case R.id.end_time_bt:
                dialog.createDateDialog("设置结束时间", new DialogCallbackListener() {
                    @Override
                    public void onPositiveButton(View view) {

                    }

                    @Override
                    public void onNegativeButton(View view) {

                    }
                }).show();
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"hhaha",Toast.LENGTH_SHORT).show();
    }

}
