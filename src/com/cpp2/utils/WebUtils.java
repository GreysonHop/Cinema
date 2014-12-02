package com.cpp2.utils;

import java.util.UUID;

public class WebUtils {
	public static String makeName(){
		return UUID.randomUUID().toString();
	}
}
