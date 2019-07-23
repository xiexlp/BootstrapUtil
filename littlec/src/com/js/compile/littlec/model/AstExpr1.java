package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstExpr1 extends AstTree {

    public Object eval(){
        Object result =(Object) 0;

        AstOp astOp = (AstOp) child(0);
        String op = astOp.getOp();

        AstTerm astTerm = (AstTerm) child(1);
        Object result1 = astTerm.eval();
        System.out.println("expr result第一个值："+result1);
        AstExpr1 astExpr1 = (AstExpr1) child(2);
        Object result2 =(Object)0;
        if(astExpr1!=null) {
            result2 = astExpr1.eval();
            AstOp astOp1 =(AstOp)astExpr1.child(0);
            String op1 = astOp1.getOp();
            if(op1==null){
                result = result1;
                return result;
            }
            if(op1.equals("+")){
                result= (Integer)result1+(Integer)result2;
            }else if(op1.equals("-")){
                result = (Integer)result1-(Integer)result2;
            }
        }else {
            result = result1;
        }
        return result;
    }

}
