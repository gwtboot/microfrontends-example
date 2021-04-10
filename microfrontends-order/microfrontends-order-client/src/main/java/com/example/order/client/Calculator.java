package com.example.order.client;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(namespace = JsPackage.GLOBAL, isNative = true)
public class Calculator {
	
	public Calculator() {
	}

	public native double calculateSum(Double[] values);

}