package com.luncode.arith;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/** 
 * @author 李启仑
 * 创建时间：2016年9月12日 下午3:25:41 
 * 说明： 
 */
public class MapSortByKey implements Comparator<Integer>{
	public boolean asc;
	public MapSortByKey(boolean asc){
		this.asc = asc;
	}
	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1==null || o2==null){
			return -1;
		}
		if(!asc) {
			return o2.compareTo(o1);
		}
		return o1.compareTo(o2);
	}
	public static Map<Integer, String> sort(boolean asc, Map<Integer, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<Integer, String> sortMap = new TreeMap<Integer, String>(new MapSortByKey(asc));
		sortMap.putAll(map);
		return sortMap;
	}
}
