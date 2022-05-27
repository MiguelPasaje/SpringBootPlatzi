package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanImplement implements MyBean {


    @Override
    public void print() {
        System.out.println("hi desde bean, my implementation");
    }
}
