package com.js.compile.littlec.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-19.
 */
public class Env {
    public static  Map envMap = new HashMap<>();

    public static void envPut(String key,Object o){
        envMap.put(key,o);
    }

    public static Object envGet(String key){
        return envMap.get(key);
    }

}
