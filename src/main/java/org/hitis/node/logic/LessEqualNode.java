package org.hitis.node.logic;

import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;
import org.hitis.util.HitisConstant;

import java.util.Map;


/**
 * @author collonn
 * 
 * <le key="type" compareValue="01" compareType="number">
 * 	and id = ?
 * </le>
 * 
 * value of compareType is "number" or "string"
 * [value of key] is lessequal than [value of compareValue] ? return sql : return "";
 */
public class LessEqualNode extends LogicNode {
	private Object compareValue;
	private String compareType = HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER;
	
	public LessEqualNode(){
		
	}
	
	public LessEqualNode(String sourceCode){
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

		boolean le = false;
		String value = params.get(this.key).toString();
		if(value != null && this.compareValue != null){
			if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.STRING)){
				if(value.toString().compareTo(this.compareValue.toString()) <= 0){
					le = true;
				}
			}else if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER)){
				if(Integer.parseInt(value.toString()) <= Integer.parseInt(this.compareValue.toString())){
					le = true;
				}
			}
		}

		if(le){
			sqlAndValues.setSql(this.getTagContent());
			sqlAndValues.setValues(new Object[]{params.get(this.key)});
		}

		return sqlAndValues;
	}
}
