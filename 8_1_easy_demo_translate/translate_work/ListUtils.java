package com.example.demo.translate_work;

import java.util.List;

/**
 * Created by Administrator on 2020/2/29.
 */
public class ListUtils {

    public static boolean isEmpty(List list) {

        if (list != null && list.size() > 0) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    public static boolean hasString(String str, List<String> list) {

        for (String l : list) {

            if (str.indexOf(l) != -1) {

                return true;

            }
        }

        return false;
    }

}
