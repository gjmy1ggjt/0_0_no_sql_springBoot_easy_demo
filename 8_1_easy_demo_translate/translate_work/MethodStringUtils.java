package com.example.demo.translate_work;

/**
 * Created by Administrator on 2020/2/29.
 */
public class MethodStringUtils {

    public static boolean isMethodLine(String s) {

        return ListUtils.hasString(s, Constant.getWordListName()) && ListUtils.hasString(s, Constant.getCaseName());

    }

    public static String getMethodName(String s) {

        String methodParam = s.substring(0, s.indexOf(Constant.caseLeftType));

        return methodParam.substring(methodParam.lastIndexOf(Constant.nullType) + 1, methodParam.length()).trim();
    }

//    public static String getMethodNameByLine(String s) {
//
//        if (isMethodLine(s)) {
//
//            String methodParam = s.substring(0, s.indexOf(Constant.caseLeftType));
//
//            return methodParam.substring(methodParam.lastIndexOf(Constant.nullType) + 1, methodParam.length()).trim();
//
//        } else {
//
//
//
//        }
//
//    }
}
