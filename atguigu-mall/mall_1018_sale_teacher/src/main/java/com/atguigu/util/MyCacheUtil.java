package com.atguigu.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.atguigu.bean.OBJECT_T_MALL_SKU;

import redis.clients.jedis.Jedis;

public class MyCacheUtil {

	public static String interKeys(String... keys) {
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			return null;
		}

		// 生成动态的key
		String k0 = "combine";
		for (int i = 0; i < keys.length; i++) {
			k0 = k0 + "_" + keys[i];
		}
		if (!jedis.exists(k0)) {
			jedis.zinterstore(k0, keys);
		}
		return k0;
	}

	public static <T> List<T> getList(String key, Class<T> t) {
		List<T> list = new ArrayList<T>();

		// 第三方数据调用
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			return null;
		}

		Set<String> zrange = jedis.zrange(key, 0, -1);
		Iterator<String> iterator = zrange.iterator();
		while (iterator.hasNext()) {
			String skuStr = iterator.next();

			T sku = MyJsonUtil.json_to_object(skuStr, t);

			list.add(sku);
		}

		return list;
	}

	public static <T> void setKey(String key, List<T> list) {

		// 第三方数据调用
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			// 记日志
		}

		jedis.del(key);
		// 同步reids
		for (int i = 0; i < list.size(); i++) {
			jedis.zadd(key, i, MyJsonUtil.object_to_json(list.get(i)));
		}

	}

	public static boolean if_key(String key) {
		// 第三方数据调用
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			// 记日志
		}
		Boolean exists = jedis.exists(key);

		return exists;
	}

}
