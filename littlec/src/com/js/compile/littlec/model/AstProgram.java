package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstProgram extends AstTree{

    public Object eval(){
        System.out.println("程序开始执行....");
        AstBlock astBlock = (AstBlock) child(0);
        return astBlock.eval();
    }

}
