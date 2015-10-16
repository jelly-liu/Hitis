package org.hitis.node;

import java.util.Map;

import org.hitis.util.HitisUtil;

/**
 * @author collonn
 * 
 * 该类是一个标签类的根类，主要负责标签的公有特性的初始化工作
 * (1)标签的原始内容
 * (2)分离出起始标签
 * (3)分离出结束标签
 * (4)分离出起始标签的属性
 * 
 * sourceCode 该node原始字符串，包含：起始标签，标签体，结束标签
 * tagStart 起始标签
 * tagEnd 结束标签
 * tagStartPropertyMap 起始标签全部属性的key和value
 */
public class HitisNode {
	protected String sourceCode;
	protected String tagStart;
	protected String tagEnd;
	protected Map<String, String> tagStartPropertyMap;
	
	public HitisNode(){
		
	}
	
	public HitisNode(String sourceCode){
		this.sourceCode = sourceCode;
		this.tagStart = HitisUtil.getTagStart(this.sourceCode);
		this.tagEnd = HitisUtil.getTagEnd(this.sourceCode);
		this.tagStartPropertyMap = HitisUtil.getTagPropertyMap(this.tagStart);
	}
	
	//除去tageStart和tagEnd，剩下的内容，如果是<select/>标签，则包含<null/>这样的子逻辑标签
	public String getTagContent() {
		String sql = this.sourceCode;
		String regex = "(" + this.tagStart + ")|(" + this.tagEnd + ")";
		sql = HitisUtil.replaceAll(sql, regex, "");
		return sql;
	}

	// getter and setter
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getTagEnd() {
		return tagEnd;
	}

	public void setTagEnd(String tagEnd) {
		this.tagEnd = tagEnd;
	}

	public String getTagStart() {
		return tagStart;
	}

	public void setTagStart(String tagStart) {
		this.tagStart = tagStart;
	}

	public Map<String, String> getTagStartPropertyMap() {
		return tagStartPropertyMap;
	}

	public void setTagStartPropertyMap(Map<String, String> tagStartPropertyMap) {
		this.tagStartPropertyMap = tagStartPropertyMap;
	}
}
