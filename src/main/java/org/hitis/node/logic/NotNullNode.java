package org.hitis.node.logic;


import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;

import java.util.Map;

/**
 * @author collonn
 * 
 * <nn key="id">
 * 	and id = ?
 * </nn>
 */
public class NotNullNode extends LogicNode {
	public NotNullNode(){
		
	}
	
	public NotNullNode(String sourceCode){
		super(sourceCode);
	}


    @Override
    public SqlAndValues getSQLAndValues(Map<String, Object> params) {
        SqlAndValues sqlAndValues = new SqlAndValues();
        if(!this.isNullValueOfKey(params)){
            sqlAndValues.setSql(this.getTagContent());
            sqlAndValues.setValues(new Object[]{params.get(this.key)});
        }
        return sqlAndValues;
    }

}
