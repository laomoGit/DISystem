package com.mqt.dripirrigationsystem.interfac;

public interface onUIRequestCallBack {
	void onUIRequestStart();

	void onUIRequestSuccess(String res);

	void onUIRequestError(Exception e);
}