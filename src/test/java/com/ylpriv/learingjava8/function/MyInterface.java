package com.ylpriv.learingjava8.function;

public interface MyInterface {

    //使用default关键字的方法
    default void sayHello(){
        System.out.println("Hello");
    }

    //接口中的静态方法
    static void sayHi(){
        System.out.println("Hi");
    }
}
