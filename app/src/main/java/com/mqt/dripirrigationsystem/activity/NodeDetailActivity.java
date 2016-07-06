package com.mqt.dripirrigationsystem.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.dialog.CustemDialog;
import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.interfac.DialogCallbackListener;
import com.mqt.dripirrigationsystem.interfac.OnUIRequestCallback;
import com.mqt.dripirrigationsystem.linechart.ChartValue;
import com.mqt.dripirrigationsystem.linechart.ChartValueSerie;
import com.mqt.dripirrigationsystem.linechart.LineChartFragment;
import com.mqt.dripirrigationsystem.linechart.LineChartView;
import com.mqt.dripirrigationsystem.manager.HistoryManager;
import com.mqt.dripirrigationsystem.manager.NodeManager;
import com.mqt.dripirrigationsystem.service.NodeDetailService;
import com.mqt.dripirrigationsystem.utils.LogInfo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class NodeDetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //private static final String USE_PATTERN_URL = "http://192.168.155.1:8080/DripIrrigationSystem/pattern";
    //private static final String NODE_PRESSURE = "http://192.168.155.1:8080/DripIrrigationSystem/pressure";
    //private static final String NODE_HISTORY_URL = "http://192.168.155.1:8080/DripIrrigationSystem/hisquery";
    private static final String USE_PATTERN_URL = "http://192.168.43.82:8080/DripIrrigationSystem/pattern";
    private static final String NODE_PRESSURE = "http://192.168.43.82:8080/DripIrrigationSystem/pressure";
    private static final String NODE_HISTORY_URL = "http://192.168.43.82:8080/DripIrrigationSystem/hisquery";

    private HistoryManager historyManager;

    //声明赋值的控件
    Button bt_Pressure;
    TextView tv_SensorT1Value;
    TextView tv_SensorT2Value;
    TextView tv_SensorH1Value;
    TextView tv_SensorH2Value;
    Button bt_usePattern;
    Button bt_EndData;
    Button bt_StarData;
    Spinner sp_Type;
    Button bt_query;
    LineChartView lcv_lineChart;
    ChartValueSerie redLineValues;
    ChartValueSerie greenLineValues;
    Node node;

    Dialog patternDialog;
    String startDate;
    String endDate;

    boolean isAuto;
    int newPressure;
    View parent;

    private List<String> type_list;
    private ArrayAdapter<String>type_adapter;

    private CustemDialog dialog;
    private NodeDetailService service;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = LayoutInflater.from(NodeDetailActivity.this).inflate(R.layout.node_details,null);
        setContentView(parent);
        //阀门服务
        service = new NodeDetailService();
        //获得Node
        getNdoe();
        dialog = new CustemDialog(NodeDetailActivity.this);

        //初始化控件
        initWidget();
    }

    private void initWidget() {
        //setTitle(node.getValveName());
        linearLayout = (LinearLayout)findViewById(R.id.node_linearLayout);
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
        bt_usePattern = (Button) findViewById(R.id.use_pattern_variate);
        bt_usePattern.setOnClickListener(this);

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
        bt_query.setOnClickListener(this);

    }

    /**
     * 显示水压值修改窗口
     */
    private void showPressureDialog() {
        //如果改变水压值，弹出对话框，是否上传服务端
        dialog.createPressureDialog(new DialogCallbackListener() {
            @Override
            public void onPositiveButton(View view) {
                EditText et_pressure = (EditText) view.findViewById(R.id.et_input_pressure);
                //判断输入值规范性
                try{
                    newPressure = Integer.parseInt(et_pressure.getText().toString().trim());
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(NodeDetailActivity.this,"输入错误",Toast.LENGTH_SHORT).show();
                    return;
                }

                service.sendRequest(NodeDetailActivity.this,
                        callbackPressure,NODE_PRESSURE,"GET",
                        "userid="+node.getUserId()+"&"+"sysid="
                                +node.getSysId()+"&"+"pressure="+newPressure);
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
            bt_usePattern.setText("自动");
        } else {
            bt_usePattern.setText("手动");
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
               showStartDateDialog();
                break;
            case R.id.end_time_bt:
                showEndDateDialog();
                break;
            case R.id.use_pattern_variate:
                showUsePattrenDialog();
                break;
            case R.id.query_button:
                if(!TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)){
                    service.sendRequest(NodeDetailActivity.this,
                            callbackHistoryQuery,NODE_HISTORY_URL,"GET",
                            "userid="+node.getUserId()+"&"+"sysid="+node.getSysId()
                            +"&"+"time1="+startDate+"&"+"time2="+endDate);
                }
                break;

        }
    }

    /**
     * 设置历史查询开始时间
     */
    private void showStartDateDialog() {
        dialog.createDateDialog("设置开始时间", new DialogCallbackListener() {
            @Override
            public void onPositiveButton(View view) {
                DatePicker datePicker = (DatePicker) view;
                int year = datePicker.getYear();
                int month = datePicker.getMonth()+1;
                int day = datePicker.getDayOfMonth();
                if(month<10){
                    startDate = year+"-0"+month;
                }else{
                    startDate = year+"-"+month;
                }if(day<10){
                    startDate +="-0"+day;
                }else{
                    startDate +="-"+day;
                }

                bt_StarData.setText(startDate);
            }

            @Override
            public void onNegativeButton(View view) {

            }
        }).show();
    }

    /**
     * 设置历史查询结束时间窗口
     */
    private void showEndDateDialog() {
        dialog.createDateDialog("设置结束时间", new DialogCallbackListener() {
            @Override
            public void onPositiveButton(View view) {
                DatePicker datePicker = (DatePicker) view;
                int year = datePicker.getYear();
                int month = datePicker.getMonth()+1;
                int day = datePicker.getDayOfMonth();
                if(month<10){
                    endDate = year+"-0"+month;
                }else{
                    endDate = year+"-"+month;
                }if(day<10){
                    endDate +="-0"+day;
                }else{
                    endDate +="-"+day;
                }
                bt_EndData.setText(endDate);
            }

            @Override
            public void onNegativeButton(View view) {

            }
        }).show();
    }

    /**
     * 显示阀门工作模式修改窗口
     */
    private void showUsePattrenDialog() {
        View view = LayoutInflater.from(NodeDetailActivity.this).inflate(R.layout.use_pattern_item,null);

        final RadioButton rb_hand = (RadioButton)view.findViewById(R.id.rb_hand_movement_pattern);
        final RadioButton rb_auto = (RadioButton)view.findViewById(R.id.rb_auto_movement_pattern);
        RadioGroup rg = (RadioGroup)view.findViewById(R.id.rg_use_pattern);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == rb_auto.getId()){
                    isAuto = true;
                }
                if(checkedId == rb_hand.getId()){
                    isAuto = false;
                }
            }
        });

        patternDialog= dialog.creatPatternDialog(view,node,new DialogCallbackListener() {
            @Override
            public void onPositiveButton(View view) {
                    if(isAuto != node.getUsePattern()){
                        service.sendRequest(NodeDetailActivity.this,
                                callbackPattern,USE_PATTERN_URL,"GET",
                                "userid="+node.getUserId()+"&"+
                                        "sysid="+node.getSysId()+"&"+"usepattern="+isAuto);
                    }
            }

            @Override
            public void onNegativeButton(View view) {

            }
        });
        //显示窗口
        patternDialog.show();
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


    /**
     * 阀门工作模式修改回调接口
     */
    private OnUIRequestCallback callbackPattern = new OnUIRequestCallback() {
        @Override
        public void onUIRequestStart() {

        }

        @Override
        public void onUIRequestSuccess(String res) {
            Toast.makeText(NodeDetailActivity.this,res,Toast.LENGTH_SHORT).show();
            NodeManager manager = NodeManager.getInstance();
            manager.modifyNode(node,isAuto);
            node.setUsePattern(isAuto);
            String usePat = "";
            if(isAuto){
                usePat = "自动";
            }else{
                usePat = "手动";
            }
            bt_usePattern.setText(usePat);

        }

        @Override
        public void onUIRequestError(Exception e) {
            Toast.makeText(NodeDetailActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 阀门水压值修改回调
     */
    private OnUIRequestCallback callbackPressure = new OnUIRequestCallback() {
        @Override
        public void onUIRequestStart() {

        }

        @Override
        public void onUIRequestSuccess(String res) {
            Toast.makeText(NodeDetailActivity.this,res,Toast.LENGTH_SHORT).show();
            NodeManager.getInstance().modifyNode(node,newPressure);
            bt_Pressure.setText(String.valueOf(newPressure)+"KPA");
        }

        @Override
        public void onUIRequestError(Exception e) {
            Toast.makeText(NodeDetailActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 历史查询回调接口
     */
    private OnUIRequestCallback callbackHistoryQuery = new OnUIRequestCallback() {
        @Override
        public void onUIRequestStart() {

        }

        @Override
        public void onUIRequestSuccess(String res) {
            historyManager = new HistoryManager();
            historyManager.saveHistories(res);
            int hisCount = historyManager.histories.size();
            if(hisCount<1){
                Toast.makeText(NodeDetailActivity.this,"没有查询结果",Toast.LENGTH_SHORT).show();
                return;
            }
            hisCount = hisCount<11?hisCount:10;

            LogInfo.info("hiscount=="+hisCount);
            LineChartView view = new LineChartView(NodeDetailActivity.this);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            ChartValueSerie serieGreen = new ChartValueSerie(Color.GREEN,1);
            ChartValueSerie serieBlue = new ChartValueSerie(Color.BLUE);
            view.setLayoutParams(layoutParams);

            for(int i=0;i<hisCount;i++){
                //一号温度和湿度
                LogInfo.info(historyManager.histories.get(i).getRecvTime());
                serieGreen.addPoint(new ChartValue(String.valueOf(i+1)
                        ,historyManager.histories.get(i).getSensorT1Value()));
                serieBlue.addPoint(new ChartValue(String.valueOf(i+1)
                        ,historyManager.histories.get(i).getSensorH1Value()));
            }
            view.addSerie(serieGreen);
            view.addSerie(serieBlue);
            linearLayout.addView(view);
        }
        @Override
        public void onUIRequestError(Exception e) {
            Toast.makeText(NodeDetailActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };
}
