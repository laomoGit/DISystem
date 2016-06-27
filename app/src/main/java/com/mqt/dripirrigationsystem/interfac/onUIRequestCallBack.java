package com.mqt.dripirrigationsystem.interfac;

public interface OnUIRequestCallback {
	void onUIRequestStart();

	void onUIRequestSuccess(String res);

	void onUIRequestError(Exception e);
}