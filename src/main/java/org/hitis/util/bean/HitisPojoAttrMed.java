package org.hitis.util.bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储一个类的所有可能用到的信息如:
 * 	属性名, getter方法, setter方法, 属性类型, 属性的大写格式
 * @author collonn
 */
public class HitisPojoAttrMed {
	//类的属性名
	private List<String> fieldNameList = new ArrayList<String>();
	//属性的getter方法
	private Map<String, Method> fieldGetterMap = new HashMap<String, Method>();
	//属性的setter方法
	private Map<String, Method> fieldSetterMap = new HashMap<String, Method>();
	//属性的类型
	private Map<String, Class> fieldTypeMap = new HashMap<String, Class>();
	//属性的大写格式NAME-->属性的原样name
	private Map<String, String> fieldNameUpperMap = new HashMap<String, String>();
	
	/*
	 * 根据大写的fieldName，来得到正确格式的fieldName
	 * oracle返回的List<LinkedHashMap<String, Object>中的LinkedHashMap的key总是大写
	 * mysql返回的List<LinkedHashMap<String, Object>中的LinkedHashMap的key总是保持原样
	 */
	public String getFieldName(String fieldName){
		if(this.fieldNameUpperMap.containsKey(fieldName)){
			fieldName = this.fieldNameUpperMap.get(fieldName);
		}
		return fieldName;
	}
	
	//getter and setter
	public Map<String, Method> getFieldGetterMap() {
		return fieldGetterMap;
	}
	
	public Map<String, Method> getFieldSetterMap() {
		return fieldSetterMap;
	}
	
	public Method getGetMethodByFieldName(String fieldUpperName){
		return this.fieldGetterMap.get(fieldUpperName);
	}
	
	public Method getSetMethodByFieldName(String fieldUpperName){
		return this.fieldSetterMap.get(fieldUpperName);
	}

	public List<String> getFieldNameList() {
		return fieldNameList;
	}

	public Map<String, String> getFieldNameUpperMap() {
		return fieldNameUpperMap;
	}

	public Map<String, Class> getFieldTypeMap() {
		return fieldTypeMap;
	}
}
