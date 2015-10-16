package org.hitis.util;

import java.util.HashMap;
import java.util.Map;

import org.hitis.node.HitisNode;


/**
 * @author collonn
 * 所有的正则匹配都 "不区分" 大小写
 * 
 * 	short name mapping table
 *	----------------------------------------
 *	statement -- st
 *	----------------------------------------
 *	notnull		-- nn, null		-- nu
 *	notempty	-- np, empty	-- ep
 *	notequal	-- ne, equal	-- eq
 *	lessequal	-- le, lessthan	-- lt
 *	greaterequal-- ge, greater	-- gt
 *	list		-- ls
 */
public class HitisConstant {
	public static final String RSMAP_KEY_FINALSQL = "RSSQL";
	public static final String RSMAP_KEY_FINALPARAM = "RSPAM";
	
	public static class LOGIC_TAG_PROPERTY_TYPE{
		public static final String STRING = "string";
		public static final String NUMBER = "number";
	}
	
	//HitisNode只有一个子类:StatementNode
	public static final Map<String, HitisNode> HitisNodeAbsMap = new HashMap<String, HitisNode>();
	
	//总记录数默认的KEY
	public static final String HITIS_TOTAL_ROW_KEY = "HITIS_TOTAL_ROW";
	
	//根据HitisId来重新载入<st id="xxx"/>节点
	public static final String ReloadHitisNodeByHitisIdParten = "<sql id=\"###\".*?>(.|\\s)*?</sql>";

	//在文本中找出所有符合规则匹配 #{xxx}
	public static final String MustValuOfKeyParten = "#\\{.*\\}";
	
	//一级节点正则表达式
	public static final String StatementPattern = "<sql.*?>(.|\\s)*?</sql>";
	
	//二级节点正则表达式
	public static final String NotNullPattern = "<notnull.*?>(.|\\s)*?</notnull>";
	public static final String NullPattern = "<null.*?>(.|\\s)*?</null>";
	public static final String NotemptyPattern = "<notempty.*?>(.|\\s)*?</notempty>";
	public static final String EmptyPattern = "<empty.*?>(.|\\s)*?</empty>";
	public static final String NotequalPattern = "<ne.*?>(.|\\s)*?</ne>";
	public static final String EqualPattern = "<eq.*?>(.|\\s)*?</eq>";
	public static final String GreaterEqualPattern = "<ge.*?>(.|\\s)*?</ge>";
	public static final String GreaterPattern = "<gt.*?>(.|\\s)*?</gt>";
	public static final String LessEqualPattern = "<le.*?>(.|\\s)*?</le>";
	public static final String LessPattern = "<lt.*?>(.|\\s)*?</lt>";
	public static final String ListPattern = "<ls.*?>(.|\\s)*?</ls>";
	
	public static final String StatementTagFeatureStart = "<sql";
	public static final String NotNullTagFeatureStart = "<notnull";
	public static final String NullTagFeatureStart = "<null";
	public static final String NotEmptyTagFeatureStart = "<notempty";
	public static final String EmptyTagFeatureStart = "<empty";
	public static final String NotEqualTagFeatureStart = "<ne";
	public static final String EqualTagFeatureStart = "<eq";
	public static final String GreaterEqualTagFeatureStart = "<ge";
	public static final String GreaterTagFeatureStart = "<gt";
	public static final String LessEqualTagFeatureStart = "<le";
	public static final String LessTagFeatureStart = "<lt";
	public static final String ListTagFeatureStart = "<ls";
	
	//一级节点正则表达式
	public static final String ALLSecondPattern = 
		"(" + HitisConstant.StatementPattern + ")";
	
	//二级节点(逻辑节)点正则表达式
	public static final String AllThirdPattern = 
		"(" + HitisConstant.NotNullPattern + ")|" +
		"(" + HitisConstant.NullPattern + ")|" +
		"(" + HitisConstant.NotemptyPattern + ")|" +
		"(" + HitisConstant.EmptyPattern + ")|" +
		"(" + HitisConstant.NotequalPattern + ")|" +
		"(" + HitisConstant.EqualPattern + ")|" +
		"(" + HitisConstant.GreaterPattern + ")|" +
		"(" + HitisConstant.GreaterEqualPattern + ")|" +
		"(" + HitisConstant.LessPattern + ")|" +
		"(" + HitisConstant.LessEqualPattern + ")|" +
		"(" + HitisConstant.ListPattern + ")";
}
