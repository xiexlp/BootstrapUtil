package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-24.
 */
public class AstWhileStmt extends AstStmt{

    public AstTree condition(){
        return child(1);
    }

    public AstTree thenStmt(){
        return child(2);
    }

    public Object eval(){
        System.out.println("语句While执行...");
        Object result =null;
        AstBool astBool = (AstBool)condition();
        System.out.println("显示bool语句内容");
        astBool.showChildren();
        boolean bool = (Boolean) astBool.eval();
        int i=0;
        while (bool){
            System.out.println("while语句执行第"+(i+1)+"次为真的情况");
            System.out.println("进入bool为真的情况");
            AstStmt astStmt = (AstStmt) thenStmt();
            System.out.println("while stmt 是否存在:"+astStmt);
            result = astStmt.eval();
            bool = (Boolean) astBool.eval();
            i++;
        }
        return result;
    }

    public String toString(){
        return "while statement";
    }

    public void show(){
        System.out.println("While 语句...");

    }

}
