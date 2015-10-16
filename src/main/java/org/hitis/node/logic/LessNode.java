package org.hitis.node.logic;

import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;
import org.hitis.util.HitisConstant;

import java.util.Map;


/**
 * @author collonn
 * 
 * <lt key="type" compareValue="01" compareType="number">
 * 	and id = ?
 * </lt>
 * 
 * value of compareType is "number" or "string"
 * [value of key] is less than [value of compareValue] ? return sql : return "";
 */
public class LessNode extends LogicNode {
	private Object compareValue;
	private String compareType = HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER;
	
	public LessNode(){
		
	}
	
	public LessNode(String sourceCode){
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

		boolean lt = false;
		String value = params.get(this.key).toString();
		if(value != null && this.compareValue != null){
			if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.STRING)){
				if(value.toString().compareTo(this.compareValue.toString()) < 0){
					lt = true;
				}
			}else if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER)){
				if(Integer.parseInt(value.toString()) < Integer.parseInt(this.compareValue.toString())){
					lt = true;
				}
			}
		}

		if(lt){
			sqlAndValues.setSql(this.getTagContent());
			sqlAndValues.setValues(new Object[]{params.get(this.key)});
		}

		return sqlAndValues;
	}
}
