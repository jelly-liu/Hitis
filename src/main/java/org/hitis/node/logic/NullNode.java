package org.hitis.node.logic;


import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;

import java.util.Map;

/**
 * @author collonn
 * 
 * <nu key="id">
 * 	and id = ?
 * </nu>
 */
public class NullNode extends LogicNode {
	public NullNode(){
		
	}
	
	public NullNode(String sourceCode){
		super(sourceCode);
	}

	@Override
	public SqlAndValues getSQLAndValues(Map<String, Object> params) {
		SqlAndValues sqlAndValues = new SqlAndValues();
		if(this.isNullValueOfKey(params)){
			sqlAndValues.setSql(this.getTagContent());
		}
		return sqlAndValues;
	}
	
}
