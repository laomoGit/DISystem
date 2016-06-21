package com.mqt.dripirrigationsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mqt.dripirrigationsystem.R;
import com.mqt.dripirrigationsystem.dialog.CustemAlertDialog;
import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.interfac.DialogCallbackListener;

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

    Node node;

    private List<String> type_list;
    private ArrayAdapter<String>type_adapter;

    private CustemAlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.node_details);
        //获得Node
        getNdoe();
        //初始化控件
        initWidget();
    }

    private void initWidget() {
        setTitle(node.getValveName());
        //返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //控件初始化
        bt_Pressure = (Button) findViewById(R.id.pressure_variate);

        //点击事件
        bt_Pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //如果改变水压值，弹出对话框，是否上传服务端
                alertDialog = new CustemAlertDialog(NodeDetailActivity.this);
                alertDialog.createPressureDialog(new DialogCallbackListener() {
                    @Override
                    public void onPositiveButton() {
                        Toast.makeText(NodeDetailActivity.this,"确定",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegativeButton() {
                    }

                }).show();
                Toast.makeText(NodeDetailActivity.this,"选中---",Toast.LENGTH_SHORT).show();

            }
        });


        tv_SensorT1Value = (TextView) findViewById(R.id.soil_temperature_variate);
        tv_SensorT2Value = (TextView) findViewById(R.id.soil_temperature_variate2);
        tv_SensorH1Value = (TextView) findViewById(R.id.soil_humidity_variate);
        tv_SensorH2Value = (TextView) findViewById(R.id.soil_humidity_variate2);
        tv_UsePattern = (TextView) findViewById(R.id.use_pattern_variate);
        sp_Type = (Spinner) findViewById(R.id.sp_type);
        bt_query = (Button) findViewById(R.id.query_button);
        bt_StarData = (Button) findViewById(R.id.start_time_textClock);
        bt_StarData.setOnClickListener(this);
        bt_EndData = (Button) findViewById(R.id.end_time_textClock);
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
                int valveId = node.SysId;
                int sensorTypeId = 3;
                Toast.makeText(NodeDetailActivity.this,"haha",Toast.LENGTH_SHORT).show();
                //manager = HistroyManager.getInstanc();
               // manager.clean();
               // manager.request(NodeDetailsActivity.this, callback, UrlConfig.BASEURL + "GetAllHisDataJson",
                        //"valveId=" + valveId + "&" + "sensorTypeId=" + sensorTypeId + "&" + "startTime=" + tx_StarData.getText() + "&" + "endTime=" + tx_EndData.getText());

            }
        });

    }

    private void setView() {

        // tv_Status.setText(node.Status+"");
        bt_Pressure.setText(Double.toString(node.getPressure()));
        tv_SensorT1Value.setText(node.SensorT1Value + "℃");
        tv_SensorT2Value.setText(node.SensorT2Value + "℃");
        tv_SensorH1Value.setText(node.SensorH1Value + "%");
        tv_SensorH2Value.setText(node.SensorH2Value + "%");
        if (node.UsePattern) {
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

    @Override
    protected void onDestroy() {
        Toast.makeText(this,"over",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
