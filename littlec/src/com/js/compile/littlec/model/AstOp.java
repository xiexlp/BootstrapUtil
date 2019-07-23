package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-19.
 */
public class AstOp extends AstLeaf{

    String op;

    public AstOp(String op){
        this.op =op;
    }

    public String getOp() {
        return op;
    }

    public void show(){
        System.out.println("op:"+op);
    }




}
