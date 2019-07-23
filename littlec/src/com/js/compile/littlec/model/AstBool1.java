package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-20.
 */
public class AstBool1 extends AstTree{

    public AstBool1(){

    }

    public Object eval(){
        Object result = null;
        AstExpr astExpr = (AstExpr) child(0);
        AstOp astOp = (AstOp) child(1);
        //第二种情况
        if(astOp!=null){

        }
        return result;
    }

}
