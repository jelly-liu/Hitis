package org.hitis.test;

import org.hitis.util.HitisConstant;
import org.hitis.util.HitisLog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M2Test {
    public static void main(String[] args) throws Exception {
//        String str = "\n" +
//                "        and age = #{age}\n" +
//                "        and email = #{email}\n" +
//                "        ";
//        Pattern pattern = Pattern.compile(HitisConstant.MustValuOfKeyParten, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(str);
//        while(matcher.find()){
//            String group = matcher.group();
//            HitisLog.Info("group=" + group);
//            String key = group.substring(2);
//            key = key.substring(0, key.length() - 1);
//            HitisLog.Info("key=" + key);
//        }

        String[] strs = new String[]{};
        List a = new ArrayList();
        System.out.println(a.getClass().isArray());
    }
}
