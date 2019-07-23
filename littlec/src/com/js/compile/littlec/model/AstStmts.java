package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstStmts extends AstTree {

    public Object eval(){
        System.out.println("语句群开始执行....");
        AstStmt astStmt =(AstStmt) child(0);
        Object result =null;
        if(astStmt!=null) {
            result=astStmt.eval();
        }
        AstStmts astStmts =(AstStmts) child(1);
        if(astStmts!=null) {
            result=astStmts.eval();
        }
        return result;
    }


}
