package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-23.
 */
public class AstStmt1 extends AstTree{

    public Object eval(){
        Object result = null;
        AstLiteral astLiteral = (AstLiteral)child(0);
        if(astLiteral!=null){
            String name = astLiteral.getName();
            if(name.equals("else")){
                AstStmt astStmt = (AstStmt) child(1);
                result = astStmt.eval();
            }
        }
        return result;
    }

}
