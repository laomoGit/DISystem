/*******************************************************************************
 * Descharts library DEMO
 * Copyright (c) 2014 Bradipao <bradipao@gmail.com>
 * https://plus.google.com/+SidBradipao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.mqt.dripirrigationsystem.linechart;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.mqt.dripirrigationsystem.R;


public class LineChartFragment extends Fragment {

   // views
   private View rootView;
   private LineChartView vChart;

   @Override
   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

      // inflate layout
      rootView = inflater.inflate(R.layout.fr_linechart,container,false);
      // get views
      getViews();
      // create RED dummy serie
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
      
      // create GREEN dummy serie
      ChartValueSerie gg = new ChartValueSerie(Color.GREEN,2);
      gg.addPoint(new ChartValue("jan",10));
      gg.addPoint(new ChartValue("feb",20));
      gg.addPoint(new ChartValue("mar",50));
      gg.addPoint(new ChartValue("apr",89));
      gg.addPoint(new ChartValue("may",20));
      gg.addPoint(new ChartValue("jun",Float.NaN));
      gg.addPoint(new ChartValue("jul",99));
      gg.addPoint(new ChartValue("aug",75));
      gg.addPoint(new ChartValue("sep",33));
      
      // create BLUE dummy serie
      ChartValueSerie bb = new ChartValueSerie(Color.BLUE,3);
      bb.addPoint(new ChartValue("jan",20));
      bb.addPoint(new ChartValue("feb",40));
      bb.addPoint(new ChartValue("mar",Float.NaN));
      bb.addPoint(new ChartValue("apr",39));
      bb.addPoint(new ChartValue("may",60));
      bb.addPoint(new ChartValue("jun",90));
      bb.addPoint(new ChartValue("jul",70));
      bb.addPoint(new ChartValue("aug",79));
      bb.addPoint(new ChartValue("sep",75));
      bb.addPoint(new ChartValue("oct",53));
      
      // add lines to chart
      vChart.addSerie(rr);
      vChart.addSerie(gg);
      vChart.addSerie(bb);
      
     return rootView;
   }
   
   private void getViews() {
      // chart view
      vChart = (LineChartView) rootView.findViewById(R.id.chart);
   }
}
