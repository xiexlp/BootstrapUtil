package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstBlock extends AstTree {

    public Object eval(){
        System.out.println("block 开始执行");
        Object result = null;
        AstStmts astStmts =(AstStmts)child(0);
        result = astStmts.eval();
        return result;
    }

}
