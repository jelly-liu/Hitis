package org.hitis.node.logic;

import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;

import java.util.Map;

/**
 * @author collonn
 * 
 * <np key="id">
 * 	and id = ?
 * </np>
 */
public class NotEmptyNode extends LogicNode {
	public NotEmptyNode(){
		
	}
	
	public NotEmptyNode(String sourceCode){
		super(sourceCode);
	}

    @Override
    public SqlAndValues getSQLAndValues(Map<String, Object> params) {
        SqlAndValues sqlAndValues = new SqlAndValues();
        if(!this.isEmptyValueOfKey(params)){
            sqlAndValues.setSql(this.getTagContent());
            sqlAndValues.setValues(new Object[]{params.get(this.key)});
        }
        return sqlAndValues;
    }
}
