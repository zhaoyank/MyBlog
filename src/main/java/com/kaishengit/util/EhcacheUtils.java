package com.kaishengit.util;

import java.io.Serializable;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class EhcacheUtils {

	private static CacheManager cacheManager = new CacheManager();
	
	public static Ehcache getEhcache(String cacheName) {
		return cacheManager.getEhcache(cacheName);
	}
	
	public static void set(String cacheName, Object key, Object value) {
		Element element = new Element(key, value);
		getEhcache(cacheName).put(element);
	}
	
	public static void set(String cacheName, Serializable key, Serializable value) {
		Element element = new Element(key, value);
		getEhcache(cacheName).put(element);
	}
	
	public static Object get(String cacheName, Object key) {
		Element element = getEhcache(cacheName).get(key);
		return element == null? null : element.getObjectValue();
	}
	
	public static void removeAll(String cacheName) {
		getEhcache(cacheName).removeAll();
	}
}
