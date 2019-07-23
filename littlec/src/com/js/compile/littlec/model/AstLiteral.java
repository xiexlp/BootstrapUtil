package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstLiteral extends AstLeaf{

    String name;

    public AstLiteral(String name){
        this.name = name;
    }

    public int numChildren(){
        return 0;
    }

    public String getName(){
        return name;
    }

    public void show(){
        System.out.println("literal:"+name);
    }
}
