package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstTerm extends AstTree {

    public Object eval() {
        Object result = null;
        System.out.println("term求值开始");
        AstFactor astFactor = (AstFactor) child(0);
        Object result1 = astFactor.eval();

        AstTerm1 astTerm1 = (AstTerm1) child(1);
        if (astTerm1 != null) {
            AstOp astOp = (AstOp) astTerm1.child(0);
            Object result2 = astTerm1.eval();

            if (astOp != null) {
                System.out.println("astop:" + astOp.getOp());
                switch (astOp.getOp()) {
                    case "*":
                        result = (Integer) result1 * (Integer) result2;
                        break;
                    case "/":
                        result = (Integer) result1 / (Integer) result2;
                        break;
                }
            }
        }else {
            result = result1;
        }
        return result;
    }


}
