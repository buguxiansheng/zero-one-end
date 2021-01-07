package com.lcfc.demo.modules.sys.service.Impl;

public class Test extends Thread{

    // 原子  可见   有序
    volatile int a;

    @Override
    public void run(){
        System.out.println(" ////");
    }


}
