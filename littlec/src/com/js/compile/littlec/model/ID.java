package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-14.
 */
public class ID {

    private String name;
    private String type;//整形，字符串等
    private String value;


    public ID(String name){
        this.name = name;
    }

    public  Value value(){
        Value v= new Value(type,value);
        return v;
    }





}
