package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-21.
 */
public class ObjectResult {

    public static final int TYPE_STRING=0;
    public static final int TYPE_INT=1;
    public static final int TYPE_FLOAT=2;

    int type=0;
    String value="";

    @Override
    public String toString(){
       return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
