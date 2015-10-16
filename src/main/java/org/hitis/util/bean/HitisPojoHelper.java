package org.hitis.util.bean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HitisPojoHelper {
	/*
	 * String 类名
	 * HitisPojoAttrMed 该类的相关信息
	 */
	public static final Map<String, HitisPojoAttrMed> PojoInfoMap = new HashMap<String, HitisPojoAttrMed>();
	
	//将POJO转换成HashMap
	public static Map<String, Object> ChangePojoToMap(Object obj){
		if(obj instanceof Map){
			return (Map)obj;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			Class cls = obj.getClass();
			//检查是否需要缓存
			HitisPojoUtil.CacheGetterAndSetterMethodByClass(cls);
			
			HitisPojoAttrMed hitisPojoAttrMed = HitisPojoHelper.PojoInfoMap.get(cls.getCanonicalName());
			for(String fieldName : hitisPojoAttrMed.getFieldNameList()){
				Method getMethod = hitisPojoAttrMed.getFieldGetterMap().get(fieldName);
				if(getMethod == null){
					continue;
				}
				paramMap.put(fieldName, getMethod.invoke(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramMap;
	}
	
	//将HashMap转换成相应的POJO
	public static <T>T ChangeMapToPojo(Map<String, Object> map, Class<T> cls){
		T obj = null;
		
		if(map == null){
			return obj;
		}
		
		try{
			//检查是否需要缓存
			HitisPojoUtil.CacheGetterAndSetterMethodByClass(cls);
			
			obj = cls.newInstance();
			Iterator it = map.entrySet().iterator();
			HitisPojoAttrMed hitisPojoAttrMed = HitisPojoHelper.PojoInfoMap.get(cls.getCanonicalName());
			while(it.hasNext()){
				Entry entry = (Entry)it.next();
				String key = entry.getKey().toString();
				key = hitisPojoAttrMed.getFieldName(key);
				Object value = entry.getValue();
				
				Method setMethod = hitisPojoAttrMed.getSetMethodByFieldName(key);
				if(setMethod == null){
					continue;
				}
				
				Class fieldTypeClass = hitisPojoAttrMed.getFieldTypeMap().get(key);
				value = HitisPojoUtil.ChangeToFieldType(fieldTypeClass, value);
				
				setMethod.invoke(obj, value);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return obj;
	}
}
