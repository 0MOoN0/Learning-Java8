package com.ylpriv.learingjava8.function;

import org.junit.Test;

public class MyMain{

    @Test
    public void fun1() {

        //调用接口静态方法
        MyInterface.sayHi();
        //调用接口default方法
        MyClass myClass = new MyClass();
        myClass.sayHello();
    }

    @Test
    public void fun2(){
        MyClass myClass = new MyClass();
        myClass.sayHello();
    }
    /*
     * output: Hello2
     */

    @Test
    public void fun3(){
        MyClass3 myClass3 = new MyClass3();
        myClass3.sayHello();
    }
    /*
        output:MyClass2
     */


}
