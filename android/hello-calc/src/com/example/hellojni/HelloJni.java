package com.example.hellojni;

public class HelloJni {
	public native double add(double a,double b);
	public native double sub(double a,double b);
	public native double mul(double a,double b);
	public native double div(double a,double b);

	static {
		System.loadLibrary("hello-jni");
	}
}
