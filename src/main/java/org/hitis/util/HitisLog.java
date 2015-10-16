package org.hitis.util;

import java.io.Serializable;

public class HitisLog {
	//日志级别，0:什么都不输出, 1:ERROR, 2:WARNING 3:DEBUG, 4:INFO
	public static int Log_Level = 4;
	private static final String PRE = "[Hitis]------";
	
	public static void Error(Serializable obj){
		if(HitisLog.Log_Level  > 0){
			StringBuffer sb = new StringBuffer();
			sb.append("[ERROR]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Error(Serializable obj, Class cls){
		if(HitisLog.Log_Level  > 0){
			StringBuffer sb = new StringBuffer();
			sb.append("[ERROR]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Error(Serializable obj, Class cls, String methodName){
		if(HitisLog.Log_Level  > 0){
			StringBuffer sb = new StringBuffer();
			sb.append("[ERROR]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append("[" + methodName + "()]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Warning(Serializable obj){
		if(HitisLog.Log_Level  > 1){
			StringBuffer sb = new StringBuffer();
			sb.append("[WARNING]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.out.println(sb.toString());
		}
	}
	
	public static void Warning(Serializable obj, Class cls){
		if(HitisLog.Log_Level  > 1){
			StringBuffer sb = new StringBuffer();
			sb.append("[WARNING]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.out.println(sb.toString());
		}
	}
	
	public static void Warning(Serializable obj, Class cls, String methodName){
		if(HitisLog.Log_Level  > 1){
			StringBuffer sb = new StringBuffer();
			sb.append("[WARNING]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append("[" + methodName + "()]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.out.println(sb.toString());
		}
	}
	
	public static void Debug(Serializable obj){
		if(HitisLog.Log_Level > 2){
			StringBuffer sb = new StringBuffer();
			sb.append("[DEBUG]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Debug(Serializable obj, Class cls){
		if(HitisLog.Log_Level > 2){
			StringBuffer sb = new StringBuffer();
			sb.append("[DEBUG]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Debug(Serializable obj, Class cls, String methodName){
		if(HitisLog.Log_Level > 2){
			StringBuffer sb = new StringBuffer();
			sb.append("[DEBUG]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append("[" + methodName + "()]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Info(Serializable obj){
		if(HitisLog.Log_Level > 3){
			StringBuffer sb = new StringBuffer();
			sb.append("[INFO]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Info(Serializable obj, Class cls){
		if(HitisLog.Log_Level > 3){
			StringBuffer sb = new StringBuffer();
			sb.append("[INFO]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Info(Serializable obj, Class cls, String methodName){
		if(HitisLog.Log_Level > 3){
			StringBuffer sb = new StringBuffer();
			sb.append("[INFO]");
			sb.append("[" + cls.getCanonicalName() + "]");
			sb.append("[" + methodName + "()]");
			sb.append(HitisLog.PRE);
			sb.append(obj);
			System.err.println(sb.toString());
		}
	}
	
	public static void Ptl(Serializable obj){
		StringBuffer sb = new StringBuffer();
		sb.append("[FOREVER]");
		sb.append(HitisLog.PRE);
		sb.append(obj);
		System.err.println(sb.toString());
	}
	
	public static void Ptl(Serializable obj, Class cls){
		StringBuffer sb = new StringBuffer();
		sb.append("[FOREVER]");
		sb.append("[" + cls.getCanonicalName() + "]");
		sb.append(HitisLog.PRE);
		sb.append(obj);
		System.err.println(sb.toString());
	}
	
	public static void Ptl(Serializable obj, Class cls, String methodName){
		StringBuffer sb = new StringBuffer();
		sb.append("[FOREVER]");
		sb.append("[" + cls.getCanonicalName() + "]");
		sb.append("[" + methodName + "()]");
		sb.append(HitisLog.PRE);
		sb.append(obj);
		System.err.println(sb.toString());
	}
}
