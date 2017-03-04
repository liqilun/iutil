package com.luncode.arith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/** 
 * @author 李启仑
 * 创建时间：2016年9月12日 下午3:25:41 
 * 说明： 
 */
public class MapSortByValue implements Comparator<Map.Entry<String, Integer>>{
	public boolean asc;
	public MapSortByValue(boolean asc){
		this.asc = asc;
	}
	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if(o2.getValue()==null || o1.getValue()==null){
			return -1;
		}
		if(!asc) {
			return o2.getValue().compareTo(o1.getValue());
		}
		return o1.getValue().compareTo(o2.getValue());
	}
	public static Map<String, Integer> sort(boolean asc, Map<String, Integer> map){
		if (map == null) {
			return null;
		}
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(entryList, new MapSortByValue(asc));
		for(Map.Entry<String, Integer> entry : entryList){
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
