package org.hitis.node;

import org.hitis.node.logic.PlainTextNode;
import org.hitis.util.HitisConstant;
import org.hitis.util.HitisUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author collonn
 * id 标签的id
 * logicNodeList 其下所有逻辑标签的一个合集
 *
 * 该类主要有两个功能
 * 1，负责把<st/>中的内容解析成一第列的LogicNode类
 * 2，负责根据sql和参数值，生成最终的sql和参数数组
 */
public class StatementNode extends HitisNode {
	private String id;
	//其下所有逻辑标签的一个合集
	private List<LogicNode> logicNodeList = new ArrayList<LogicNode>();
	
	public StatementNode(){
		
	}
	
	public StatementNode(String sourceCode){
		super(sourceCode);
		this.id = this.tagStartPropertyMap.get("id");
		this.parseToLogicNode(this.getTagContent());
	}

    public SqlAndValues getSqlAndValues(Map<String, Object> params){
        SqlAndValues finalSqlAndValues = new SqlAndValues();

        StringBuilder sql = new StringBuilder();
        List<Object> valueList = new LinkedList<Object>();

        for(LogicNode logicNode : this.logicNodeList){
            SqlAndValues sqlAndValues = logicNode.getSQLAndValues(params);
            if(HitisUtil.isEmpty(sqlAndValues.getSql())){
                continue;
            }

            sql.append(sqlAndValues.getSql());
            if(sqlAndValues.getValues() != null && sqlAndValues.getValues().length > 0){
                for(Object value : sqlAndValues.getValues()){
                    valueList.add(value);
                }
            }
        }

        finalSqlAndValues.setSql(sql.toString());
        finalSqlAndValues.setValues(valueList.toArray());

        return finalSqlAndValues;
    }
	
	//递归方法调用
	//从上到下，**有顺序** 的分离出其下的所有的逻辑标签 以及 逻辑标签外的所有表述内容
	private void parseToLogicNode(String str){
		Pattern pattern = Pattern.compile(HitisConstant.AllThirdPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			//该匹配的logicTag [[之前]] 的所有内容
			String sql = str.substring(0, matcher.start());

			if(!HitisUtil.isEmpty(sql)){
				this.logicNodeList.add(new PlainTextNode(sql));
			}

			//匹配成功的整个逻辑标签
			LogicNode logicNode = NodeFactory.CreateLogicNode(matcher.group());
			this.logicNodeList.add(logicNode);
			
			//该匹配的logicTag [[之后]] 的所有内容
			String nextSql = str.substring(matcher.end());
			this.parseToLogicNode(nextSql);
		}else{
			if(!HitisUtil.isEmpty(str)){
				this.logicNodeList.add(new PlainTextNode(str));
			}
		}
	}
	
	//getter and setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<LogicNode> getLogicNodeList() {
		return logicNodeList;
	}

	public void setLogicNodeList(List<LogicNode> logicNodeList) {
		this.logicNodeList = logicNodeList;
	}
	
}
