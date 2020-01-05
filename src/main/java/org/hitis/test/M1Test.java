package org.hitis.test;

import org.hitis.HitisFactory;
import org.hitis.node.SqlAndValues;
import org.hitis.util.HitisExecuter;
import org.hitis.util.HitisLog;
import org.hitis.util.HitisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M1Test {
    public static void main(String[] args) {
        List<String> hitisFileNameList = new ArrayList<String>();
        hitisFileNameList.add("testSQL.html");

        HitisFactory hitisFactory = new HitisFactory();
        hitisFactory.setEncode("UTF-8");
        hitisFactory.setHitisPackagePath("/");
        hitisFactory.setHitisFilePathList(hitisFileNameList);
        hitisFactory.init();

        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("id", 123);
        paramMap.put("name", "01");
        paramMap.put("age", "02");
        paramMap.put("email", "03");
        List<Integer> idsList = new ArrayList<Integer>();
        idsList.add(1);
        idsList.add(2);
        idsList.add(3);
        paramMap.put("idsList", idsList);
        paramMap.put("idsAry", new Integer[]{11, 22, 33});
        SqlAndValues sqlAndValues = HitisExecuter.getSqlAndValues("TB_A_SELECT", paramMap);
        String sql = sqlAndValues.getSql();
        Object[] params = sqlAndValues.getValues();

        HitisLog.print("sql as:\n" + sql);
        HitisLog.print("parameter as:" + HitisUtil.paramsToString(params));
    }
}
