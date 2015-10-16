package org.hitis.node.logic;


import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;
import org.hitis.util.HitisUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author collonn
 * 
 * <ep key="id">
 * 	and id = ?
 * </ep>
 */
public class EmptyNode extends LogicNode {
	public EmptyNode(){
		
	}
	
	public EmptyNode(String sourceCode){
		super(sourceCode);
	}

	@Override
	public SqlAndValues getSQLAndValues(Map<String, Object> params) {
		SqlAndValues sqlAndValues = new SqlAndValues();
		if(this.isEmptyValueOfKey(params)){
			sqlAndValues.setSql(this.getTagContent());
		}
		return sqlAndValues;
	}

}
