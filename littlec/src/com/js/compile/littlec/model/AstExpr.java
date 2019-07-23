package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstExpr extends AstTree {

    public Object eval(){

        System.out.println("expr 求值开始");

        Object result =null;
        AstTerm astTerm = (AstTerm) child(0);
        AstExpr1 astExpr1 = (AstExpr1) child(1);
        System.out.println("astExpr1是否存在:"+astExpr1);
        Object result1=astTerm.eval();
        System.out.println("astTerm 返回结果:"+result1);
        if(astExpr1!=null){
            System.out.println("expr进入expr1");
            Object result2=astExpr1.eval();

            AstOp astOp = (AstOp) astExpr1.child(0);
            String op = astOp.getOp();
            Integer re =0;
            if(op.equals("+")){
                result = (Integer)result1+(Integer)result2;
            }else if(op.equals("-")){
                result = (Integer)result1-(Integer)result2;
            }
        }else {
            result = result1;
            //System.out.println("表达式返回结果:"+result);
        }

        System.out.println("表达式返回结果为:"+result);

        return result;
    }

}
