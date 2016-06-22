/*******************************************************************************
 * Descharts library
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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
* LineChartView() class represents a line graph widget.
*/

public class LineChartView extends CartesianView {

   // data holder
   private ArrayList<ChartValueSerie> mSeries = new ArrayList<ChartValueSerie>();
   private int mXnum=0;
   private int mLabelMaxNum = 10;

   // objects
   private Paint mPnt = new Paint();
   
   /** 
    * Constructor.
    */
   public LineChartView(Context context){
      super(context);
      initPaint();
   }
   
   /** 
    * Constructor.
    */
   public LineChartView(Context context,AttributeSet attrs) {
      super(context,attrs);
      initPaint();
   }
   
   /** 
    * Draws the plot.
    */
   public void onDraw(Canvas cnv) {

      // create cache bitmap if necessary
      if ((mBmp==null)||(bRedraw)) {
         
         // get view sizes
         getViewSizes();
         // get min,max data ranges
         getXYminmax();
         // calculate the adjusted grid ranges and drawing parameters
         if (p_yscale_auto) calcYgridRange();
         // calculate drawing coefficients
         calcXYcoefs();

         // create bitmap and canvas to draw
         mBmp = Bitmap.createBitmap(p_width,p_height,Config.ARGB_8888);
         mCnv = new Canvas(mBmp);
         
         // draw inner grid
         if (p_grid_vis) drawGrid();
         // label on X axis
         if (p_xtext_vis) drawXlabel();
         // label on Y axis
         if (p_ytext_vis) drawYlabel();
         // draw outer border
         if (p_border_vis) drawBorder();
         // draw zero axis if internal
         if (p_axis_vis) drawAxis();
         // draw data
         drawData();
         
         // bitmap refresh complete
         bRedraw = false;
      }
      
      // draw bitmap
      cnv.drawBitmap(mBmp,0,0,null);
   }
   
   /** 
    * Clears ArrayList of series.
    */
   public void clearSeries(){
      while (mSeries.size()>0){
         mSeries.remove(0);
      }
      bRedraw = true;
      postInvalidate();
   }
   
   /** 
    * Adds a serie to data holder ArrayList.
    */
   public void addSerie(ChartValueSerie serie) {
      mSeries.add(serie);
      bRedraw = true;
      postInvalidate();
   }

   public void deleteSerie(ChartValueSerie serie){
      mSeries.remove(serie);
      bRedraw = true;
      postInvalidate();
   }
   /** 
    * Returns ArrayList of series.
    */
   public ArrayList<ChartValueSerie> getSeries() {
      return mSeries;
   }
   
   /** 
    * Sets line visibility.
    */
   public void setLineVis(int index,boolean show) {
      mSeries.get(index).setVisible(show);
      bRedraw = true;
      postInvalidate();
   }
   
   /**
    * Sets line color and size.
    */
   public void setLineStyle(int index,int color,float size) {
      mSeries.get(index).setStyle(color,size);
      bRedraw = true;
      postInvalidate();
   }

   /**
    * Sets line color and size and dip.
    */
   public void setLineStyle(int index,int color,float size,boolean usedip) {
      mSeries.get(index).setStyle(color,size,usedip);
      bRedraw = true;
      postInvalidate();
   }

   /**
    * Sets maximum number of labels on X axis.
    */
   public void setLabelMaxNum(int maxnum) {
      if (maxnum<=0) return;
      mLabelMaxNum = maxnum;
      bRedraw = true;
      postInvalidate();
   }
   
   /** 
    * Gets X,Y ranges across all series
    */
   protected void getXYminmax() {
      ChartValueSerie serie;
      for (ii=0;ii<mSeries.size();ii++) {
         serie = mSeries.get(ii);
         if (ii==0) {
            mXnum = serie.getSize();
            mYmin = serie.mYmin;
            mYmax = serie.mYmax;
         } else {
            if (serie.getSize()>mXnum) mXnum = serie.getSize();
            if (serie.mYmin<mYmin) mYmin = serie.mYmin;
            if (serie.mYmax>mYmax) mYmax = serie.mYmax;     
         }
      }
   }
   
   /** 
    * Draw data from all series
    */
   public void drawData() {
      float pY;
      boolean pValid;
      for (ChartValueSerie serie : mSeries) { 
         if (serie.isVisible()) {
            // set paint
            mPnt.reset();
            mPnt.setStyle(Paint.Style.STROKE);
            mPnt.setColor(serie.mColor);
            if (serie.mUseDip) mPnt.setStrokeWidth(dipToPixel(serie.mWidth));
            else mPnt.setStrokeWidth(serie.mWidth);
            mPnt.setAntiAlias(true);
            // iterate through points
            pValid = false;
            mPath.reset();
            for (ii=0;ii<serie.mPointList.size();ii++) {
               pY = serie.mPointList.get(ii).y;
               if (Float.isNaN(pY)) {
                  pValid = false;
               } else if (!pValid) {
                  mPath.moveTo(sX+bX+ii*aX,eY-(pY-bY)*aY);
                  pValid = true;
               } else {
                  mPath.lineTo(sX+bX+ii*aX,eY-(pY-bY)*aY);
               }
            }
            // draw line
            mCnv.drawPath(mPath,mPnt);
         }
      }
   }

  public void reDrawData(){

  }


   /**
    * Calculates drawing coefficients
    */
   protected void calcXYcoefs() {
      aX = (float) dX/mXnum;
      bX = (float) aX/2;
      aY = (float) dY/Math.abs(mYmaxGrid-mYminGrid);
      bY = (float) mYminGrid;
   }
   
   /** 
    * Draw X label on top or bottom
    */
   protected void drawXlabel() {
      mPntText.setTextAlign(Paint.Align.CENTER);
      mPath.reset();
      ChartValueSerie mLabel = mSeries.get(0);
      String label;
      int numlab = mLabel.getSize();
      int numdiv = 1 + (numlab-1)/mLabelMaxNum;
      if (p_xtext_bottom) {
         for (ii=0;ii<mLabel.getSize();ii++) {
            mPath.moveTo(sX+bX+ii*aX,eY-3);
            mPath.lineTo(sX+bX+ii*aX,eY+3);
            label = mLabel.mPointList.get(ii).t;
            if ((label!=null)&&(ii<numlab)&&((ii%numdiv)==0))
               mCnv.drawText(label,sX+bX+ii*aX,eY+p_text_size+2,mPntText);
         }
      } else {
         for (ii=0;ii<mLabel.getSize();ii++) {
            mPath.moveTo(sX+bX+ii*aX,sY-3);
            mPath.lineTo(sX+bX+ii*aX,sY+3);
            label = mLabel.mPointList.get(ii).t;
            if ((label!=null)&&(ii<numlab)&&((ii%numdiv)==0))
               mCnv.drawText(label,sX+bX+ii*aX,sY-p_text_size+3,mPntText);
         }               
      }
      mCnv.drawPath(mPath,mPntAxis);
   }   
   
}
