package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstIfStmt extends AstStmt {

    public AstTree condition(){
        return child(1);
    }

    public AstTree thenStmt(){
        return child(2);
    }

    public AstTree elseStmt1(){
        return child(3);
    }

    public Object eval(){

        System.out.println("语句IF执行...");
        Object result =null;
        AstBool astBool = (AstBool)condition();
        System.out.println("显示bool语句内容");
        astBool.showChildren();
        boolean bool = (Boolean) astBool.eval();

        if(bool){
            System.out.println("进入bool为真的情况");
            AstStmt astStmt = (AstStmt) thenStmt();
            result = astStmt.eval();
        }else {
            System.out.println("进入bool为假的情况");
            AstStmt1 astStmt1 =(AstStmt1)elseStmt1();
            result = astStmt1.eval();
        }
        return result;
    }

    public String toString(){
        return "ifstatement";
    }

    public void show(){

        System.out.println("If 语句...");

    }

}
