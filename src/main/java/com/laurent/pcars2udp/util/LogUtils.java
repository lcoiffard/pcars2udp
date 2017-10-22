package com.laurent.pcars2udp.util;

import org.springframework.stereotype.Component;

@Component
public class LogUtils {
	public synchronized void print(String str) {
		System.out.print(str);
	}

	public synchronized void println(String str) {
		System.err.println(str);
	}
}
