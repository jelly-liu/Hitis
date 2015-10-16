package org.hitis.node;

import org.hitis.node.logic.EmptyNode;
import org.hitis.node.logic.EqualNode;
import org.hitis.node.logic.GreaterEqualNode;
import org.hitis.node.logic.GreaterNode;
import org.hitis.node.logic.LessEqualNode;
import org.hitis.node.logic.LessNode;
import org.hitis.node.logic.ListNode;
import org.hitis.node.logic.NotEmptyNode;
import org.hitis.node.logic.NotEqualNode;
import org.hitis.node.logic.NotNullNode;
import org.hitis.node.logic.NullNode;
import org.hitis.util.HitisConstant;

/**
 * Node对象工厂
 * @author collonn
 */
public class NodeFactory {
	//创建<select/>标签的简单静态工厂
	public static HitisNode CreateStNode(String segment){
		HitisNode hitisNode = null;
		
		String segmentLowerCase = segment.toLowerCase();
		if(segmentLowerCase.startsWith(HitisConstant.StatementTagFeatureStart)){
			hitisNode = new StatementNode(segment);
		}
		
		return hitisNode;
	}
	
	//创建<notnull/>, <notequal/>, <greater/>, <less/>等逻辑标签的静态工厂
	public static LogicNode CreateLogicNode(String segment){
		LogicNode logicNode = null;
		String segmentLowerCase = segment.toLowerCase();
		if(segmentLowerCase.startsWith(HitisConstant.EmptyTagFeatureStart)){
			logicNode = new EmptyNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.NotEmptyTagFeatureStart)){
			logicNode = new NotEmptyNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.NullTagFeatureStart)){
			logicNode = new NullNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.NotNullTagFeatureStart)){
			logicNode = new NotNullNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.EqualTagFeatureStart)){
			logicNode = new EqualNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.NotEqualTagFeatureStart)){
			logicNode = new NotEqualNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.GreaterEqualTagFeatureStart)){
			logicNode = new GreaterEqualNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.GreaterTagFeatureStart)){
			logicNode = new GreaterNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.LessEqualTagFeatureStart)){
			logicNode = new LessEqualNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.LessTagFeatureStart)){
			logicNode = new LessNode(segment);
		}else if(segmentLowerCase.startsWith(HitisConstant.ListTagFeatureStart)){
			logicNode = new ListNode(segment);
		}
		
		return logicNode;
	}
}
