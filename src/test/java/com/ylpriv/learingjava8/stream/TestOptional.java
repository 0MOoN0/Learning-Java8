package com.ylpriv.learingjava8.stream;

import com.ylpriv.learingjava8.entiry.Employee;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

public class TestOptional {

    @Test
    public void test4(){
        Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99));

        //map方法接收一个Function函数式接口：Function<? super T, ? extends U> mapper，U为返回值
        //如果Employee不存在，将返回一个empty的Optional，flatMap同
        Optional<String> op2 = op.map(Employee::getName);
        System.out.println(op2.get());

        //Optional的flatMap方法接收一个返回值为Optional的Function函数式接口：Function<? super T, Optional<U>> mapper
        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }

    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(new Employee());

        //判断是否为空
        if(op.isPresent()){
            System.out.println(op.get());
        }

        //如果Optional里没有元素，则使用其他元素代替
        Employee emp = op.orElse(new Employee("张三"));
        System.out.println(emp);

        //如果Optional里没有元素，则通过函数式接口Supplier<? extends T> other获得
        Employee emp2 = op.orElseGet(() -> new Employee());
        System.out.println(emp2);
    }

    @Test
    public void test2(){
/*		Optional<Employee> op = Optional.ofNullable(null);
		System.out.println(op.get());*/

        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
        //Exception:java.util.NoSuchElementException: No value present
    }

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);
        //output:Employee [id=0, name=null, age=0, salary=0.0, status=null]
    }

}
