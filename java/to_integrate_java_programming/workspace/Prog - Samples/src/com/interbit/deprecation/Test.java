package com.interbit.deprecation;

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Foo f=new Foo();
		f.bar();
	}

}
