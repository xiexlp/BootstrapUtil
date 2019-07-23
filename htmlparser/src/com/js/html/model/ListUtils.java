package com.js.html.model;

import java.util.List;

/**
 * Created by Administrator on 2017-10-28.
 */
public class ListUtils {

    public static List concat(List list1,List list2){
        for(Object o:list2){
            list1.add(o);
        }
        return list1;
    }

}
