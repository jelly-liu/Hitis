package org.hitis.node.logic;

import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;
import org.hitis.util.HitisConstant;

import java.util.Map;


/**
 * @author collonn
 * 
 * <gt key="type" compareValue="01" compareType="string">
 * 	and id = ?
 * </gt>
 * 
 * value of compareType is "number" or "string"
 * [value of key] is greater than [value of compareValue] ? return sql : return "";
 */
public class GreaterNode extends LogicNode {
	private Object compareValue;
	private String compareType = HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER;
	
	public GreaterNode(){
		
	}
	
	public GreaterNode(String sourceCode){
		super(sourceCode);
		this.compareValue = this.tagStartPropertyMap.get("compareValue");
		this.compareType = this.tagStartPropertyMap.get("compareType");
	}

	@Override
	public SqlAndValues getSQLAndValues(Map<String, Object> params) {
		SqlAndValues sqlAndValues = new SqlAndValues();

		if(this.isEmptyValueOfKey(params)){
			return sqlAndValues;
		}

		boolean gt = false;
		String value = params.get(this.key).toString();
		if(value != null && this.compareValue != null){
			if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.STRING)){
				if(value.toString().compareTo(this.compareValue.toString()) > 0){
					gt = true;
				}
			}else if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER)){
				if(Integer.parseInt(value.toString()) > Integer.parseInt(this.compareValue.toString())){
					gt = true;
				}
			}
		}

		if(gt){
			sqlAndValues.setSql(this.getTagContent());
			sqlAndValues.setValues(new Object[]{params.get(this.key)});
		}

		return sqlAndValues;
	}
}
