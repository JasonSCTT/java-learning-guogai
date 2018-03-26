package net.guo.util;

import java.io.*;

public class Print {
	public static void print() {
		System.out.println();
	}

	public static void print(Object obj) {
		System.out.println(obj);
	}

	public static void printnb(Object obj) {
		System.out.println();
	}

	public static PrintStream printf(String format,Object... args) {
		return System.out.printf(format,args);
	}

}