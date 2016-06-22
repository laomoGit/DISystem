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
   
   // views : series color, size and dip
   ToggleButton tbS1,tbS2,tbS3;
   Spinner spS1color,spS1size,spS2color,spS2size,spS3color,spS3size;
   CheckBox cbS1dip,cbS2dip,cbS3dip;
   // views : border, axis and grid
   ToggleButton tbBorder,tbAxis,tbGrid;
   Spinner spBorderC,spAxisC,spGridC,spBorderW,spAxisW,spGridW;
   // views : dip and antialis
   CheckBox cbUseDip,cbUseAA;
   // views : X and Y text
   ToggleButton tbXtext,tbYtext,tbXtextBottom,tbYtextLeft;
   Spinner spTextC,spTextS;
   // views : background color
   Spinner spBkgC;
   
   // vars
   private String[] colors = {"BLACK","RED","GREEN","BLUE","VIOLET","YELLOW","WHITE","lt RED","lt GREEN","lt BLUE","lt VIOLET","lt YELLOW"};
   private int[] icolor = {Color.BLACK,0xffcc0000,0xff669900,0xff0099cc,0xff9933cc,0xffff8800,Color.WHITE,0xffff4444,0xff99cc00,0xff33bbee,0xffaa66cc,0xffffbb33};
   private String[] widths = {"0.5","1.0","1.5","2.0","2.5","3.0"};
   private float[] fwidth = {0.5f,1.0f,1.5f,2.0f,2.5f,3.0f};
   private String[] sizes = {"6.0","6.5","7.0","7.5","8.0","9.0","10.0"};
   private float[] fsizes = {6.0f,6.5f,7.0f,7.5f,8.0f,9.0f,10.0f};
   
   @Override
   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

      // inflate layout
      rootView = inflater.inflate(R.layout.fr_linechart,container,false);
      // get views
      getViews();
      setupViews();
      setupListeners();
      
      // create RED dummy serie
      ChartValueSerie rr = new ChartValueSerie(Color.RED,1);
      rr.addPoint(new ChartValue("jan",99));
      rr.addPoint(new ChartValue("feb",80));
      rr.addPoint(new ChartValue("mar",180));
      rr.addPoint(new ChartValue("apr",99));
      rr.addPoint(new ChartValue("may",80));
      rr.addPoint(new ChartValue("jun",120));
      rr.addPoint(new ChartValue("jul",20));
      rr.addPoint(new ChartValue("aug",50));
      rr.addPoint(new ChartValue("sep",109));
      
      // create GREEN dummy serie
      ChartValueSerie gg = new ChartValueSerie(Color.GREEN,2);
      gg.addPoint(new ChartValue("jan",-10));
      gg.addPoint(new ChartValue("feb",20));
      gg.addPoint(new ChartValue("mar",-50));
      gg.addPoint(new ChartValue("apr",89));
      gg.addPoint(new ChartValue("may",20));
      gg.addPoint(new ChartValue("jun",Float.NaN));
      gg.addPoint(new ChartValue("jul",99));
      gg.addPoint(new ChartValue("aug",75));
      gg.addPoint(new ChartValue("sep",33));
      
      // create BLUE dummy serie
      ChartValueSerie bb = new ChartValueSerie(Color.BLUE,3);
      bb.addPoint(new ChartValue("jan",-20));
      bb.addPoint(new ChartValue("feb",-40));
      bb.addPoint(new ChartValue("mar",Float.NaN));
      bb.addPoint(new ChartValue("apr",139));
      bb.addPoint(new ChartValue("may",160));
      bb.addPoint(new ChartValue("jun",90));
      bb.addPoint(new ChartValue("jul",70));
      bb.addPoint(new ChartValue("aug",79));
      bb.addPoint(new ChartValue("sep",175));
      bb.addPoint(new ChartValue("oct",153));
      
      // add lines to chart
      vChart.addSerie(rr);
      vChart.addSerie(gg);
      vChart.addSerie(bb);
      
      return rootView;
   }
   
   private void getViews() {
      // chart view
      vChart = (LineChartView) rootView.findViewById(R.id.chart);
      // series color, size and dip
      tbS1 = (ToggleButton) rootView.findViewById(R.id.tbSerie1);
      spS1color = (Spinner) rootView.findViewById(R.id.spSerie1Color);
      spS1size = (Spinner) rootView.findViewById(R.id.spSerie1Size);
      cbS1dip = (CheckBox) rootView.findViewById(R.id.cbSerie1Dip);
      tbS2 = (ToggleButton) rootView.findViewById(R.id.tbSerie2);
      spS2color = (Spinner) rootView.findViewById(R.id.spSerie2Color);
      spS2size = (Spinner) rootView.findViewById(R.id.spSerie2Size);
      cbS2dip = (CheckBox) rootView.findViewById(R.id.cbSerie2Dip);
      tbS3 = (ToggleButton) rootView.findViewById(R.id.tbSerie3);
      spS3color = (Spinner) rootView.findViewById(R.id.spSerie3Color);
      spS3size = (Spinner) rootView.findViewById(R.id.spSerie3Size);
      cbS3dip = (CheckBox) rootView.findViewById(R.id.cbSerie3Dip);
      // grid visibility
      tbBorder = (ToggleButton) rootView.findViewById(R.id.tbBorder);
      tbAxis = (ToggleButton) rootView.findViewById(R.id.tbAxis);
      tbGrid = (ToggleButton) rootView.findViewById(R.id.tbGrid);
      // grid color spinner
      spBorderC = (Spinner) rootView.findViewById(R.id.spBorderC);
      spAxisC = (Spinner) rootView.findViewById(R.id.spAxisC);
      spGridC = (Spinner) rootView.findViewById(R.id.spGridC);
      // grid width spinner
      spBorderW = (Spinner) rootView.findViewById(R.id.spBorderW);
      spAxisW = (Spinner) rootView.findViewById(R.id.spAxisW);
      spGridW = (Spinner) rootView.findViewById(R.id.spGridW);
      // dip and antialias checkbox
      cbUseDip = (CheckBox) rootView.findViewById(R.id.cbUseDip);
      cbUseAA = (CheckBox) rootView.findViewById(R.id.cbUseAA);
      // text label
      tbXtext = (ToggleButton) rootView.findViewById(R.id.tbXtext);
      tbYtext = (ToggleButton) rootView.findViewById(R.id.tbYtext);
      tbXtextBottom = (ToggleButton) rootView.findViewById(R.id.tbXtextBottom);
      tbYtextLeft = (ToggleButton) rootView.findViewById(R.id.tbYtextLeft);
      // text color and size spinner
      spTextC = (Spinner) rootView.findViewById(R.id.spTextColor);
      spTextS = (Spinner) rootView.findViewById(R.id.spTextSize);
      // background color
      spBkgC = (Spinner) rootView.findViewById(R.id.spBkgColor);
   }
   
   private void setupViews() {
      // series
      tbS1.setChecked(true);
      tbS2.setChecked(true);
      tbS3.setChecked(true);
      ArrayAdapter<String> adS1color = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      ArrayAdapter<String> adS2color = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      ArrayAdapter<String> adS3color = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      adS1color.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adS2color.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adS3color.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);    
      spS1color.setAdapter(adS1color);
      spS2color.setAdapter(adS2color);
      spS3color.setAdapter(adS2color);
      spS1color.setSelection(7);
      spS2color.setSelection(8);
      spS3color.setSelection(9);
      ArrayAdapter<String> adS1size = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,widths);
      ArrayAdapter<String> adS2size = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,widths);
      ArrayAdapter<String> adS3size = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,widths);
      adS1size.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adS2size.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adS3size.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      spS1size.setAdapter(adS1size);
      spS2size.setAdapter(adS2size);
      spS3size.setAdapter(adS2size);
      spS1size.setSelection(1);
      spS2size.setSelection(3);
      spS3size.setSelection(5);
      cbS1dip.setChecked(true);
      cbS2dip.setChecked(true);
      cbS3dip.setChecked(true); 
      // border, axis, grid
      tbBorder.setChecked(true);
      tbAxis.setChecked(true);
      tbGrid.setChecked(true);
      ArrayAdapter<String> adBorderC = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      ArrayAdapter<String> adAxisC = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      ArrayAdapter<String> adGridC = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      adBorderC.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adAxisC.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adGridC.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      spBorderC.setAdapter(adBorderC);
      spAxisC.setAdapter(adAxisC);
      spGridC.setAdapter(adGridC);
      spBorderC.setSelection(6);
      spAxisC.setSelection(6);
      spGridC.setSelection(6);
      // grid size
      ArrayAdapter<String> adBorderW = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,widths);
      ArrayAdapter<String> adAxisW = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,widths);
      ArrayAdapter<String> adGridW = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,widths);
      adBorderW.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adAxisW.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adGridW.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      spBorderW.setAdapter(adBorderW);
      spAxisW.setAdapter(adAxisW);
      spGridW.setAdapter(adGridW);
      spBorderW.setSelection(1);
      spAxisW.setSelection(1);
      spGridW.setSelection(1);
      // dip and antialias checkbox
      cbUseDip.setChecked(true);
      cbUseAA.setChecked(true);
      // text label
      tbXtext.setChecked(true);
      tbYtext.setChecked(true);
      tbXtextBottom.setChecked(true);
      tbYtextLeft.setChecked(true);
      // text color and size spinner
      ArrayAdapter<String> adTextC = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      ArrayAdapter<String> adTextS = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,sizes);
      adTextC.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      adTextS.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      spTextC.setAdapter(adTextC);
      spTextS.setAdapter(adTextS);
      spTextC.setSelection(6);
      spTextS.setSelection(5);
      // background color spinner
      ArrayAdapter<String> adBkgC = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,colors);
      adBkgC.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      spBkgC.setAdapter(adBkgC);
      spBkgC.setSelection(0);
   }
   
   private void setupListeners() {
      // serie togglebutton listener
      OnClickListener mSerieListener = new OnClickListener() {
         @Override
         public void onClick(View v) {
            vChart.setLineVis(0,tbS1.isChecked());
            vChart.setLineVis(1,tbS2.isChecked());
            vChart.setLineVis(2,tbS3.isChecked());
         }
      };
      tbS1.setOnClickListener(mSerieListener);
      tbS2.setOnClickListener(mSerieListener);
      tbS3.setOnClickListener(mSerieListener);
      
      // serie color and size listener
      OnItemSelectedListener mSerieColorListener = new OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
            vChart.setLineStyle(0,icolor[spS1color.getSelectedItemPosition()],fwidth[spS1size.getSelectedItemPosition()],cbS1dip.isChecked());
            vChart.setLineStyle(1,icolor[spS2color.getSelectedItemPosition()],fwidth[spS2size.getSelectedItemPosition()],cbS2dip.isChecked());
            vChart.setLineStyle(2,icolor[spS3color.getSelectedItemPosition()],fwidth[spS3size.getSelectedItemPosition()],cbS3dip.isChecked());
         }
         @Override
         public void onNothingSelected(AdapterView<?> parent) {
            // do nothing
         }
      };
      spS1color.setOnItemSelectedListener(mSerieColorListener);
      spS2color.setOnItemSelectedListener(mSerieColorListener);
      spS3color.setOnItemSelectedListener(mSerieColorListener);
      spS1size.setOnItemSelectedListener(mSerieColorListener);
      spS2size.setOnItemSelectedListener(mSerieColorListener);
      spS3size.setOnItemSelectedListener(mSerieColorListener);
      
      // serie dip checkbox listener
      OnCheckedChangeListener mSerieDipListener = new OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            vChart.setLineStyle(0,icolor[spS1color.getSelectedItemPosition()],fwidth[spS1size.getSelectedItemPosition()],cbS1dip.isChecked());
            vChart.setLineStyle(1,icolor[spS2color.getSelectedItemPosition()],fwidth[spS2size.getSelectedItemPosition()],cbS2dip.isChecked());
            vChart.setLineStyle(2,icolor[spS3color.getSelectedItemPosition()],fwidth[spS3size.getSelectedItemPosition()],cbS3dip.isChecked());
         }
      };
      cbS1dip.setOnCheckedChangeListener(mSerieDipListener);
      cbS2dip.setOnCheckedChangeListener(mSerieDipListener);
      cbS3dip.setOnCheckedChangeListener(mSerieDipListener);
      
      // grid togglebutton listener
      OnClickListener mGridListener = new OnClickListener() {
         @Override
         public void onClick(View v) {
            vChart.setGridVis(tbBorder.isChecked(),tbGrid.isChecked(),tbAxis.isChecked());
         }
      };
      tbBorder.setOnClickListener(mGridListener);
      tbAxis.setOnClickListener(mGridListener);
      tbGrid.setOnClickListener(mGridListener);
      
      // grid color spinner listener
      OnItemSelectedListener mGridColorListener = new OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
            vChart.setGridColor(icolor[spBorderC.getSelectedItemPosition()],
                            icolor[spGridC.getSelectedItemPosition()],
                            icolor[spAxisC.getSelectedItemPosition()]);
         }
         @Override
         public void onNothingSelected(AdapterView<?> parent) {
            // do nothing
         }
      };
      spBorderC.setOnItemSelectedListener(mGridColorListener);
      spGridC.setOnItemSelectedListener(mGridColorListener);
      spAxisC.setOnItemSelectedListener(mGridColorListener);
      
      // grid width spinner listener
      OnItemSelectedListener mGridWidthListener = new OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
            if (cbUseDip.isChecked()) vChart.setGridWidthDip(fwidth[spBorderW.getSelectedItemPosition()],
                  fwidth[spGridW.getSelectedItemPosition()],
                  fwidth[spAxisW.getSelectedItemPosition()]);
            else vChart.setGridWidth(fwidth[spBorderW.getSelectedItemPosition()],
                  fwidth[spGridW.getSelectedItemPosition()],
                  fwidth[spAxisW.getSelectedItemPosition()]);
         }
         @Override
         public void onNothingSelected(AdapterView<?> parent) {
            // do nothing
         }
      };
      spBorderW.setOnItemSelectedListener(mGridWidthListener);
      spGridW.setOnItemSelectedListener(mGridWidthListener);
      spAxisW.setOnItemSelectedListener(mGridWidthListener);

      // usedip checkbox listener
      cbUseDip.setOnCheckedChangeListener(new OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
               if (isChecked) vChart.setGridWidthDip(fwidth[spBorderW.getSelectedItemPosition()],
                     fwidth[spGridW.getSelectedItemPosition()],
                     fwidth[spAxisW.getSelectedItemPosition()]);
               else vChart.setGridWidth(fwidth[spBorderW.getSelectedItemPosition()],
                     fwidth[spGridW.getSelectedItemPosition()],
                     fwidth[spAxisW.getSelectedItemPosition()]);
         }
      });
      
      // grid antialias checkbox listener
      cbUseAA.setOnCheckedChangeListener(new OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            vChart.setGridAA(isChecked);
         }
      });
      
      // text togglebutton listener
      OnClickListener mTextListener = new OnClickListener() {
         @Override
         public void onClick(View v) {
            vChart.setTextVis(tbXtext.isChecked(),tbYtext.isChecked(),tbXtextBottom.isChecked(),tbYtextLeft.isChecked());
         }
      };
      tbXtext.setOnClickListener(mTextListener);
      tbYtext.setOnClickListener(mTextListener);
      tbXtextBottom.setOnClickListener(mTextListener);
      tbYtextLeft.setOnClickListener(mTextListener);
      
      // text color and size spinner
      OnItemSelectedListener mTextStyleListener = new OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
            vChart.setTextStyle(icolor[spTextC.getSelectedItemPosition()],
                            fsizes[spTextS.getSelectedItemPosition()]);
         }
         @Override
         public void onNothingSelected(AdapterView<?> parent) {
            // do nothing
         }
      };
      spTextC.setOnItemSelectedListener(mTextStyleListener);
      spTextS.setOnItemSelectedListener(mTextStyleListener);
      
      // background color listener
      OnItemSelectedListener mBkgColorListener = new OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
            vChart.setBackgroundColor(icolor[spBkgC.getSelectedItemPosition()]);
         }
         @Override
         public void onNothingSelected(AdapterView<?> parent) {
            // do nothing
         }
      };
      spBkgC.setOnItemSelectedListener(mBkgColorListener);
      
   }
   
}
