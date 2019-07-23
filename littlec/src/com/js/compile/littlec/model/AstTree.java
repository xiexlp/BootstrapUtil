package com.js.compile.littlec.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-16.
 */
public class AstTree {

    //Map envMap = new HashMap();

   public AstTree(){

   }


    List<AstTree> children = new ArrayList();

    public AstTree child(int i){
        return children.get(i);
    }

    public int numChildren(){
        return children.size();
    }



    public void addChild(AstTree astTree){
        children.add(astTree);
    }


    public void showChildren(){
        for(AstTree astTree:children ){
            System.out.println(astTree);
        }
    }

    public void show(){
        for(AstTree astTree:children){
            System.out.println("ast tree children show:");
            astTree.show();
        }
    }




}
