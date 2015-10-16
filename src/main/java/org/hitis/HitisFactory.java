package org.hitis;

import java.util.ArrayList;
import java.util.List;

import org.hitis.util.HitisLog;
import org.hitis.util.HitisUtil;

/**
 * @author collonn
 * *************************************
 * ***email:collonn@126.com
 * ***qq:195358385
 * *************************************
 */
public class HitisFactory {
	public static String encode = "UTF-8";
	public static String hitisPackagePath;
	public static List<String> hitisFilePathList;
	
	public HitisFactory(){
		
	}
	
	//spring bean的初始化方法
	public void init(){
		try{
			if(hitisFilePathList == null || hitisFilePathList.size() == 0){
				HitisLog.Warning("PLEASE CONFIG HITIS FILE");
				return;
			}
			
			/*
			 * 转换成:包路径加文件名的相对于classpath的路径
			 * /com/manager/hitis/Pojo_Insert.txt
			 */
			List<String> newPathList = new ArrayList<String>();
			for(String fileName : hitisFilePathList){
				newPathList.add(hitisPackagePath + fileName);
			}
			hitisFilePathList = newPathList;
			
			//解析所有文件到缓存
			HitisUtil.parseHitisFileAsNew();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//getter and setter
	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		HitisFactory.encode = encode;
	}

	public List<String> getHitisFilePathList() {
		return hitisFilePathList;
	}

	public void setHitisFilePathList(List<String> hitisFilePathList) {
		HitisFactory.hitisFilePathList = hitisFilePathList;
	}

	public String getHitisPackagePath() {
		return hitisPackagePath;
	}

	public void setHitisPackagePath(String hitisPackagePath) {
		HitisFactory.hitisPackagePath = hitisPackagePath;
	}
	
}
