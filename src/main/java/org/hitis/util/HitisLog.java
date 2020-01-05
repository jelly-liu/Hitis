package org.hitis.util;

public class HitisLog {
	/**
	 * HitisLog.print("your name is {}, and your age is {}", "tom", 20);
	 * @param msg
	 * @param parameters
	 */
	public static void print(String msg, Object ...parameters){
		if(parameters == null || parameters.length == 0){
			System.out.println(msg);
			return;
		}

		String msg2 = "";
		String[] segments = msg.split("\\{\\}");
		for(int i = 0; i < segments.length; i++){
			msg2 += segments[i];
			if(i < parameters.length){
				msg2 += parameters[i];
			}else{
				msg2 += "{}";
			}
		}

		System.out.println(msg2);
	}

	public static void main(String[] args) {
		print("your name is {}, and your age is {}", "tom", 20);
	}
}
