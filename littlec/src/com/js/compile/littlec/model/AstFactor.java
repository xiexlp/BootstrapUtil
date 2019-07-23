package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstFactor  extends AstTree{

    public static final int FACTOR_EXPR=1;
    public static final int FACTOR_ID=2;
    public static final int FACTOR_NUM=3;

    private int factorType;

    public Object eval(){
        Object result=null;

       switch (factorType){
           case FACTOR_EXPR:
               AstExpr astExpr = (AstExpr) child(0);
               result = astExpr.eval();
               System.out.println("factor expr eval result:"+result);
               break;
           case FACTOR_ID:
               AstLiteral astLiteral = (AstLiteral)child(0);
               String name = astLiteral.getName();
               result = Env.envGet(name);
               System.out.println("id eval result:"+result);
               break;
           case FACTOR_NUM:
               AstNum astNum = (AstNum)child(0);
               result = astNum.eval();
               System.out.println("num eval result:"+result);
               break;
       }
       return result;
    }

    public void setFactorType(int factorType) {
        this.factorType = factorType;
    }
}
