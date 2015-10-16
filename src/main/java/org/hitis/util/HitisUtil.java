package org.hitis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hitis.HitisFactory;
import org.hitis.node.HitisFile;
import org.hitis.node.HitisNode;
import org.hitis.node.NodeFactory;

public class HitisUtil {

	/**
	 * @param string 原始字符串
	 * @param regex 正则表达式
	 * @param to 将匹配的第一个值替换成to
	 * @return
	 */
	public static String replaceOnce(String string, String regex, String to){
		String newString = null;

		//替换tag头和tag尾，只取中间的字符串
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		newString = matcher.replaceFirst(to);

		return newString;
	}

	/**
	 * @param string 原始字符串
	 * @param regex 正则表达式
	 * @param to 将匹配的所有值替换成to
	 * @return
	 */
	public static String replaceAll(String string, String regex, String to){
		String newString = null;

		//替换tag头和tag尾，只取中间的字符串
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		newString = matcher.replaceAll(to);

		return newString;
	}
	
	//精简所有连续的空白符(\t或\s)为一个空格符
	public static String reduceTabAndSpace(String string){
		String newString = null;
		
		//合并多个tab为一个
		Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		newString = matcher.replaceAll(" ");
		
		return newString;
	}

	//精简所有连续的空白符(\t或\s)为一个空格符
	public static String trimAll(String string){
		String newString = null;

		//合并多个tab为一个
		Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		newString = matcher.replaceAll("");

		return newString;
	}

	public static boolean isEmpty(String text){
		if(text == null){
			return true;
		}

		if(text.equals("")){
			return true;
		}

		if(text.trim().equals("")){
			return true;
		}

		Pattern pattern = Pattern.compile("^(\\s+)&", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		if(matcher.find()){
			return true;
		}

		return false;
	}
	
	//去除所有的注释内容<!-- xxx -->
	public static String trimHeadAndTail(String string){
		String newString = null;
		
		Pattern pattern = Pattern.compile("(^\\s*)|(\\s*$)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		newString = matcher.replaceAll("");
		
		return newString;
	}
	
	//分离出tagStart，如<select id="select1">
	public static String getTagStart(String sourceCode){
		Pattern pattern = Pattern.compile("^<.*?>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sourceCode);
		if(matcher.find()){
			return matcher.group();
		}else{
			return null;
		}
	}
	
	//分离出tagEnd, 如</select>
	public static String getTagEnd(String sourceCode){
		Pattern pattern = Pattern.compile("(</\\w+?>)$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sourceCode);
		
		if(matcher.find()){
			return matcher.group();
		}else{
			return null;
		}
	}
	
	/*
	 * 分离出任意一个tag的属性的key和value
	 * 如将其<equal key="type" compareValue="1" compareType="string" must="true">
	 * 分离成key->type, compareValue->1, compareType->string, must->true
	 * 注意： key和value全部会转换成小写
	 */
	public static Map<String, String> getTagPropertyMap(String tagStart){
		Map<String, String> propertyMap = new HashMap<String, String>();
		
		if(tagStart == null || tagStart.trim().equals("")){
			return propertyMap;
		}
		
		Pattern pattern = Pattern.compile("\\w+=\"\\w+\"");
		Matcher matcher = pattern.matcher(tagStart);
		
		//key="type", compareValue="1"
		while(matcher.find()){
			String keyValue = matcher.group();
			String key = keyValue.substring(0, keyValue.indexOf("="));
			String value = keyValue.substring(keyValue.indexOf("=") + 2, keyValue.length() - 1);
			
			propertyMap.put(key, value);
		}
		
		return propertyMap;
	}
	
	//根据fianlSql来截取一个查询总记录数的sql
	public static String ChangToTotalRowSql(String finalSql){
		String totalSql = null;
		
		Pattern pattern = Pattern.compile(".*?from", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(finalSql);
		
		totalSql = matcher.replaceFirst("select count(*) " + HitisConstant.HITIS_TOTAL_ROW_KEY + " from");
		
		return totalSql;
	}
	
	//将数组值拼接成String
	public static String paramsToString(Object[] params){
		if(params == null){
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for(Object param : params){
			if(param == null){
				sb.append("null" + ",");
			}else{
				sb.append("\"" + param.toString() + "\"" + ",");
			}
		}
		if(sb.length() >0){
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * @param0 文件相对于classpath的路径
	 * @param1 编码格式，默认以UTF-8编码
	 * 注意，每行尾部加了空格，以防止多个语句被直接连接，造成SQL缺少关键字的错误
	 */
	public static String readHitisFile(String path, String encode){
		InputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		
		try{
			fis = HitisUtil.class.getResourceAsStream(path);
			if(fis == null){
				HitisLog.Warning("CAN NOT FIND HITIS FILE:" + path);
			}else{
				isr = new InputStreamReader(fis, encode);
				br = new BufferedReader(isr);
				String data = null;
				while((data = br.readLine()) != null){
					sb.append(data).append("\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis != null){
					fis.close();
				}
				if(isr != null){
					isr.close();
				}
				if(br != null){
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	//遍历出目录下的所有文件，取得每个文件的绝对路径，返回一个List<String>
	public static List<String> fetchAllFiles(File folder, List<String> fileAbsPathList){
		File[] files = folder.listFiles();
		for(File file : files){
			if(file.isFile()){
				fileAbsPathList.add(file.getAbsolutePath().replaceAll("\\\\", "/"));
			}else{
				fetchAllFiles(file, fileAbsPathList);
			}
		}
		return fileAbsPathList;
	}
	
	//取得Map中的所的有Key，并以 "," 分隔
	public static String getAllMapKey(Map map){
		StringBuilder ids = new StringBuilder();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = (Entry)it.next();
			ids.append("\"" + entry.getKey().toString() + "\"" + ",");
		}
		if(map.size() > 0){
			ids.deleteCharAt(ids.length() - 1);
		}
		return ids.toString();
	}
	
	//根据HitisId来重新载入<st id="xxx"/>节点
	public static void reloadHitisNodeByHitisId(String hitisNodeId){
		HitisLog.Info("reloadHitisNodeByHitisId:" + hitisNodeId);
		//重新读取文件
		String filePath = HitisFile.HitisNodeFileMap.get(hitisNodeId);
		String fileContent = HitisUtil.readHitisFile(filePath, HitisFactory.encode);
		HitisFile.HitisFileContentMap.put(filePath, fileContent);
		
		//匹配出相应的节点
		String ptnStr = HitisConstant.ReloadHitisNodeByHitisIdParten;
		ptnStr = ptnStr.replaceAll("###", hitisNodeId);
		Pattern pattern = Pattern.compile(ptnStr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(fileContent);
		
		if(matcher.find()){
			//重新生成Node
			String nodeContent = matcher.group();
			HitisNode hitisNodeAbs = NodeFactory.CreateStNode(nodeContent);
			String nodeId = hitisNodeAbs.getTagStartPropertyMap().get("id");
			HitisConstant.HitisNodeAbsMap.put(nodeId, hitisNodeAbs);
		}
	}
	
	/*
	 * 重新载入所有的Hitis文件
	 * 1:hitisPackage不能变
	 * 2:自动加载hitisPackage下的所有文件(新加的和删除的文件都会及时刷新到缓存)
	 * 3:文件中相应节点的更新自然也会刷新到缓存
	 */
	public static void parseHitisFileAsNew(){
		try{
			HitisLog.Info("****** START CACHE HITIS NODE ******");
			HitisConstant.HitisNodeAbsMap.clear();
			HitisFile.HitisFileContentMap.clear();
			HitisFile.HitisNodeFileMap.clear();
			
			//重新读取所有文件内容
			for(String filePath : HitisFactory.hitisFilePathList){
				HitisFile.HitisFileContentMap.put(filePath, HitisUtil.readHitisFile(filePath, HitisFactory.encode));
			}
			
			//重新解析所有文件内容并刷新到缓存
			for(String filePath : HitisFactory.hitisFilePathList){
				HitisLog.Info("FOUND HITIS FILE:" + filePath);
				String hitisContent = HitisFile.HitisFileContentMap.get(filePath);
				hitisContent = HitisUtil.replaceAll(hitisContent, "<!--(.|\\s)*?-->", "");//去除所有注释内容
				Pattern pattern = Pattern.compile(HitisConstant.ALLSecondPattern, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(hitisContent);
				
				while(matcher.find()){
					HitisNode hitisNode = NodeFactory.CreateStNode(matcher.group());
					String nodeId = hitisNode.getTagStartPropertyMap().get("id");
					if(!HitisConstant.HitisNodeAbsMap.containsKey(nodeId)){
						HitisFile.HitisNodeFileMap.put(nodeId, filePath);
						HitisConstant.HitisNodeAbsMap.put(nodeId, hitisNode);
					}else{
						HitisLog.Warning("SAME HITIS ID WAS FOUNDED:" + nodeId + ", IN FILE:" + filePath);
					}
				}
			}
			
			//打印出所有找到的Hitis Id
			HitisLog.Info("FOUND HITIS ID:[" + HitisUtil.getAllMapKey(HitisConstant.HitisNodeAbsMap) + "]");
			HitisLog.Info("****** CACHE HITIS NODE SUCCESS ******");
		}catch(Exception e){
			HitisLog.Error("(#_#)Failed(#_#)");
			e.printStackTrace();
		}
	}
}
