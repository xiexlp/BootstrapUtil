package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstTerm1 extends AstTree {

    public Object eval(){
        Object result =null;
        AstOp astOp = (AstOp) child(0);
        AstFactor astFactor =(AstFactor)child(1);
        AstTerm1 astTerm1 =(AstTerm1)child(2);

        if(astOp==null){
            //都为空
            return null;
        }

        Object result1 = astFactor.eval();
        Object result2 = astTerm1.eval();

        AstOp astOp1 =(AstOp)astTerm1.child(0);

        switch (astOp1.getOp()){
            case "*":
                result =(Integer)result1*(Integer)result2;
                break;
            case "/":
                result =(Integer)result1/(Integer)result2;
                break;
        }
        return  result;
    }

    public void show(){

        for(AstTree astTree:children){
            System.out.println("ast term show");
            astTree.show();
        }

    }

}
