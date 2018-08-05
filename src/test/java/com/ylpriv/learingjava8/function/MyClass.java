package com.ylpriv.learingjava8.function;


public class MyClass implements MyInterface,MyInterface2{

    //MyInterface和MyInterface2出现了方法冲突，需要重写sayHello方法
    @Override
    public void sayHello() {
        //调用MyInterface2的sayHello方法
        MyInterface2.super.sayHello();
    }
}
