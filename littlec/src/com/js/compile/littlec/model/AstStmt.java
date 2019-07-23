package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstStmt extends AstTree{

    public AstStmt(){

    }

    public static final int STMT_EQUAL=1;
    public static final int STMT_IF=2;
    public static final int STMT_WHILE=3;
    public static final int STMT_DO=4;
    public static final int STMT_DO_WHILE=5;
    public static final int STMT_BLOCK=6;
    public static final int STMT_BREAK=7;

    int stmtType=0;

    public AstStmt(int stmtType){
        this.stmtType = stmtType;
    }

    public Object eval() {
//        if(stmtType==STMT_EQUAL){
//
//        }else if(stmtType == STMT_IF){
//
//        }
        System.out.println("stmt语句开始执行...");
        Object result = null;
        switch (stmtType) {
            case STMT_EQUAL:
                System.out.println("赋值语句执行");
                AstLiteral literal = (AstLiteral) child(0);
                String name = literal.getName();
                AstOp astOp = (AstOp) child(1);
                System.out.println("表达式求值的操作符号:"+astOp.getOp());
                AstExpr astExpr = (AstExpr) child(2);
                //astExpr.show();
                System.out.println("表达式=求值.....");
                Object result1 = astExpr.eval();
                System.out.println("表达式求值的结果:"+result1);
                Env.envPut(name, result1);
                break;
            case STMT_IF:
                //AstIfStmt astIfStmt = (AstIfStmt) ;
                AstIfStmt astIfStmt = (AstIfStmt)child(1);
                astIfStmt.eval();
                break;
            case STMT_WHILE:
                AstWhileStmt astWhileStmt = (AstWhileStmt)child(1);
                astWhileStmt.eval();
                break;
            case STMT_DO:
                break;
            case STMT_DO_WHILE:
                break;
            case STMT_BLOCK:
                System.out.println("进入stmt block的执行");
                AstBlock astBlock = (AstBlock)child(0);
                System.out.println("block是否为空:"+astBlock);
                result = astBlock.eval();
                break;
            case STMT_BREAK:
                System.out.println("break");
                break;
            case 0:
                System.out.println("默认没有执行");
                break;
        }
        return result;
    }

    public void setStmtType(int stmtType) {
        this.stmtType = stmtType;
    }
}
