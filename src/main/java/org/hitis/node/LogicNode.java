package org.hitis.node;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hitis.node.HitisNode;
import org.hitis.util.HitisConstant;
import org.hitis.util.HitisLog;
import org.hitis.util.HitisUtil;

/**
 * @author collonn
 * 
 * key 每个Logic标签的起标签都有一个key属性
 * 	   与paramMap中的某个key名是对应的
 */
public abstract class LogicNode extends HitisNode {
	protected String key;
	
	public LogicNode(){
		
	}
	
	public LogicNode(String sourceCode){
		super(sourceCode);
		this.key = this.tagStartPropertyMap.get("key");
	}

	protected boolean isNullValueOfKey(Map<String, Object> params){
		Object value = params.get(this.key);
		if(value == null){
			return true;
		}
		return false;
	}

	protected boolean isEmptyValueOfKey(Map<String, Object> params){
		return isEmptyOfObject(params.get(this.key));
	}

	protected boolean isEmptyOfObject(Object value){
		if(value == null){
			return true;
		}

		if(value instanceof List){
			if(((List)value).size() == 0){
				return true;
			}
			return false;
		}else{
			if(HitisUtil.isEmpty(value.toString())){
				return true;
			}
			return false;
		}
	}

	public abstract SqlAndValues getSQLAndValues(Map<String, Object> params);
	
	//getter and setter
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
