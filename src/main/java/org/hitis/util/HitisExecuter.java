package org.hitis.util;

import java.util.Map;

import org.hitis.node.HitisNode;
import org.hitis.node.SqlAndValues;
import org.hitis.node.StatementNode;
import org.hitis.util.bean.HitisPojoHelper;

public class HitisExecuter {
	public static SqlAndValues getSqlAndValues(String hitisId, Map<String, Object> paramMap){
		if(!HitisConstant.HitisNodeAbsMap.containsKey(hitisId)){
			HitisLog.Error("找不到相关节点，请检查大小写，hitisId=" + hitisId);
			return null;
		}

		HitisNode hitisNodeAbs = HitisConstant.HitisNodeAbsMap.get(hitisId);
		StatementNode selectNode = (StatementNode)hitisNodeAbs;

		return selectNode.getSqlAndValues(paramMap);
	}
	
	public static void ReloadHitisNodeByHitisId(String hitisNodeId){
		HitisUtil.reloadHitisNodeByHitisId(hitisNodeId);
	}
	
	public static void ParseHitisFileAsNew(){
		HitisUtil.parseHitisFileAsNew();
	}
	
	public static Map<String, Object> ChangePojoToMap(Object obj){
		return HitisPojoHelper.ChangePojoToMap(obj);
	}
	
	public static <T>T ChangeMapToPojo(Map<String, Object> map, Class<T> cls){
		return HitisPojoHelper.ChangeMapToPojo(map, cls);
	}
}
