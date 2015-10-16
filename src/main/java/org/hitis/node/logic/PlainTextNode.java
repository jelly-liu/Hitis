package org.hitis.node.logic;


import org.hitis.node.LogicNode;
import org.hitis.node.SqlAndValues;
import org.hitis.util.HitisConstant;
import org.hitis.util.HitisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlainTextNode extends LogicNode {
	public PlainTextNode() {

	}

	public PlainTextNode(String sourceCode) {
		super(sourceCode);
	}

	@Override
	public SqlAndValues getSQLAndValues(Map<String, Object> params) {
		SqlAndValues sqlAndValues = new SqlAndValues();

		String tagContent = this.getTagContent();
		Pattern pattern = Pattern.compile(HitisConstant.MustValuOfKeyParten, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(tagContent);

		List<Object> values = new ArrayList<Object>();
		while(matcher.find()){
			String group = matcher.group();

			String key = group.substring(2);
			key = key.substring(0, key.length() - 1);
			if(!params.containsKey(key)){
				throw new RuntimeException("parameter map does not contains this required key=" + key);
			}
			Object value = params.get(key);
			if(this.isEmptyOfObject(value)){
				throw new RuntimeException("parameter map contains this required key=" +key + ", but value is empty, value=" + value);
			}

			values.add(value);
		}

		//替换#{xxx}为?
		tagContent = HitisUtil.replaceAll(tagContent, HitisConstant.MustValuOfKeyParten, "?");
		sqlAndValues.setSql(tagContent);
		sqlAndValues.setValues(values.toArray());

		return sqlAndValues;
	}
}
