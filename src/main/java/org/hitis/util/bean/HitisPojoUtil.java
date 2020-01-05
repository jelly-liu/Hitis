package org.hitis.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hitis.util.HitisLog;

/**
 * Hitis操作POJO的功能类
 * 只对getXXX 和 setXXX有效，对isXXX无效
 * @author collonn
 */
public class HitisPojoUtil {
	//清除缓存
	public static void ClearCache(){
		HitisPojoHelper.PojoInfoMap.clear();
	}
	
	//将value转换成对应类型的值
	public static Object ChangeToFieldType(Class fieldTypeClass, Object value){
		Object obj = null;
		
		if(fieldTypeClass == String.class){
			obj = new String(value.toString());
		}else if(fieldTypeClass == Integer.class){
			obj = Integer.valueOf(value.toString());
		}else if(fieldTypeClass == Long.class){
			obj = Long.valueOf(value.toString());
		}else if(fieldTypeClass == Float.class){
			obj = Float.valueOf(value.toString());
		}else if(fieldTypeClass == Double.class){
			obj = Double.valueOf(value.toString());
		}else if(fieldTypeClass == BigDecimal.class){
			obj = new BigDecimal(value.toString());
		}else if(fieldTypeClass == java.util.Date.class){
			try {
				obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return obj;
	}
	
	//Pojo属性和方法缓存
	public static void CacheGetterAndSetterMethodByClass(Class cls){
		String classFullName = cls.getCanonicalName();
		if(HitisPojoHelper.PojoInfoMap.containsKey(classFullName)){
			return;
		}
		
		HitisPojoAttrMed hitisPojoAttrMed = new HitisPojoAttrMed();
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields){
			String fieldName = field.getName();
			hitisPojoAttrMed.getFieldNameList().add(fieldName);
			hitisPojoAttrMed.getFieldTypeMap().put(fieldName, field.getType());
			hitisPojoAttrMed.getFieldNameUpperMap().put(fieldName.toUpperCase(), fieldName);
			hitisPojoAttrMed.getFieldGetterMap().put(fieldName, HitisPojoUtil.GetGetMethod(field, cls));
			hitisPojoAttrMed.getFieldSetterMap().put(fieldName, HitisPojoUtil.GetSetMethod(field, cls));
		}
		
		HitisLog.print("CACHED NEW POJO'S INFO:" + classFullName);
		HitisPojoHelper.PojoInfoMap.put(classFullName, hitisPojoAttrMed);
	}
	
	//根据属性，得到其对应的getter方法
	public static Method GetGetMethod(Field field, Class<?> cls){
		Method getMethod = null;
		try {
			String fieldName = field.getName();
			String methodName = "get" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
			getMethod = cls.getMethod(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMethod;
	}
	
	//根据属性得到其对就的setter方法
	private static Method GetSetMethod(Field field, Class<?> cls){
		Method setMethod = null;
		try {
			String fieldName = field.getName();
			String methodName = "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
			Class paramClass = field.getType();
			setMethod = cls.getMethod(methodName, paramClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setMethod;
	}
	
}