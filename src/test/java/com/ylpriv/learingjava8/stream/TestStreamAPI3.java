package com.ylpriv.learingjava8.stream;

import com.ylpriv.learingjava8.entiry.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI3 {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );
    List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

    /*
    映射
        map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    */
    @Test
    public void fun(){

        Stream<String> str = emps.stream()
                .map((e) -> e.getName());

        System.out.println("-------------------------------------------");


        Stream<String> stream = strList.stream()
                .map(String::toUpperCase);//map只执行一次，返回一个Stream对象

        stream.forEach(System.out::println);
    }

    //map
    @Test
    public void fun2(){
        Stream<Stream<Character>> stream2 = strList.stream()
                .map(TestStreamAPI3::filterCharacter);

        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
    }

    //flatMap
    @Test
    public void fun3(){
        Stream<Character> stream3 = strList.stream()
                //使用flatMap将所有流整合为一个流
                .flatMap(TestStreamAPI3::filterCharacter);

        stream3.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

}
