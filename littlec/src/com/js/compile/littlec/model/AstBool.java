package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstBool extends AstTree {

    public Object eval(){
        Object result = null;

        AstExpr astExpr = (AstExpr) child(0);
        AstOp astOp = (AstOp)child(1);
        AstExpr astExpr1 = (AstExpr) child(2);

        Object result1 = astExpr.eval();
        Object result2= astExpr1.eval();
        Integer resultInteger1 = (Integer)result1;
        Integer resultInteger2 =(Integer)result2;

        System.out.println("bool判断bool的操作符号:"+astOp.getOp());
        switch (astOp.getOp()){
            case ">":
                if(resultInteger1>resultInteger2) result= Boolean.TRUE;
                else result = Boolean.FALSE;
                break;
            case ">=":
                if(resultInteger1>=resultInteger2) result = Boolean.TRUE;
                else  result = Boolean.FALSE;
                break;
            case "<":
                if(resultInteger1<resultInteger2) result = Boolean.TRUE;
                else  result = Boolean.FALSE;
                break;
            case "<=":
                if(resultInteger1<=resultInteger2) result = Boolean.TRUE;
                else  result = Boolean.FALSE;
                break;
            case "==":
                System.out.println("进入==");
                if(resultInteger1==resultInteger2) result= Boolean.TRUE;
                else  result = Boolean.FALSE;
                System.out.println("==判断结果:"+(boolean)result);
                break;
        }
        return  result;
    }




}
