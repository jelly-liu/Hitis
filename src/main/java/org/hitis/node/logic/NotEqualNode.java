package org.hitis.node.logic;

import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;
import org.hitis.util.HitisConstant;

import java.util.Map;


/**
 * @author collonn
 * 
 * <ne key="type" compareValue="01" compareType="string">
 * 	and id = ?
 * </ne>
 * 
 * value of compareType is "number" or "string"
 * [value of key] is not equal than [value of compareValue] ? return sql : return "";
 */
public class NotEqualNode extends LogicNode {
	protected Object compareValue;
	protected String compareType = HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER;
	
	public NotEqualNode(){
		
	}
	
	public NotEqualNode(String sourceCode){
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

		boolean ne = false;
		String value = params.get(this.key).toString();
		if(value != null && this.compareValue != null){
			if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.STRING)){
				if(value.toString().compareTo(this.compareValue.toString()) != 0){
					ne = true;
				}
			}else if(this.compareType.equals(HitisConstant.LOGIC_TAG_PROPERTY_TYPE.NUMBER)){
				if(Integer.parseInt(value.toString()) != Integer.parseInt(this.compareValue.toString())){
					ne = true;
				}
			}
		}

		if(ne){
            sqlAndValues.setSql(this.getTagContent());
            sqlAndValues.setValues(new Object[]{params.get(this.key)});
		}

		return sqlAndValues;
	}
}
