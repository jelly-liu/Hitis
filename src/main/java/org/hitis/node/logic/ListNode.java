package org.hitis.node.logic;

import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;

import java.util.List;
import java.util.Map;


/**
 * 注意：这个节点，是不占用SQL的点位符的，而是直接用真实的值去替换你占位符，
 * getFinalSql()则返回类似id in (?, ?, ?)的处理后的语句
 * getFinalValue()则返回实际的参数值的数组
 * 
 * @author collonn
 * 
 * <ls key="ids" must="true">
 * 	and id in (?)
 * </ls>
 * 
 * the sql:
 * xxx where 1 = 1 and id in (?) will be changed to sub
 * xxx where 1 = 1 and id in (?, ?, ?)
 */
public class ListNode extends LogicNode {
	public ListNode(){
		
	}
	
	public ListNode(String sourceCode){
		super(sourceCode);
	}

	@Override
	public SqlAndValues getSQLAndValues(Map<String, Object> params) {
		SqlAndValues sqlAndValues = new SqlAndValues();

		if(this.isEmptyValueOfKey(params)){
			return sqlAndValues;
		}

		Object value = params.get(this.key);
		Object[] values = null;
		if(value instanceof List){
			values = ((List<Object>)value).toArray();
		}else{
			values = (Object[])value;
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < values.length; i++){
			sb.append("?, ");
		}
		if(values.length > 0){
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
		}

		String sql = super.getTagContent();
		sql = sql.replaceAll("\\?", sb.toString());

		sqlAndValues.setSql(sql);
		sqlAndValues.setValues(values);

		return sqlAndValues;
	}
}
