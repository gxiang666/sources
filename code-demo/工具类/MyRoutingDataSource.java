package com.atguigu.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource {

    //共享的Key
    private static ThreadLocal<String> key = new ThreadLocal<String>();


    protected Object determineCurrentLookupKey() {


        return this.key.get();
    }

    public static void setKey(String key_in) {

        key.set(key_in);
    }


/*

	private static ThreadLocal<String> key = new ThreadLocal<String>();

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return this.key.get();
	}

	public static String getKey() {
		return key.get();
	}

	public static void setKey(String key_in) {
		key.set(key_in);
	}
*/


}
