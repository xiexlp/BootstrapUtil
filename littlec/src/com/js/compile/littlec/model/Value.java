package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-14.
 */
public class Value {

    private String type;
    private String valueString;

    public Value(String type,String valueString){
        this.type=type;
        this.valueString= valueString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }
}
