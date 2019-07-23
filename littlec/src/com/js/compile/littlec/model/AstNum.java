package com.js.compile.littlec.model;

/**
 * Created by Administrator on 2017-10-18.
 */
public class AstNum extends AstTree{

    String nums;

    public AstNum(String nums){
        this.nums = nums;
    }

    public int numChildren(){
        return 0;
    }

    public int eval(){
        System.out.println("astnum 求值:"+nums);
        return Integer.parseInt(nums);
    }



}
