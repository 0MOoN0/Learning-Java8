package com.ylpriv.learingjava8.stream;

import com.ylpriv.learingjava8.entiry.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

//中止操作2
public class TestStreamAPI6 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    /*
    归约
    reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
 */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream()
                //开始时x会被赋值为0，y赋值为1，Lambda会返回两个相加的值  计算：0+1
                //第二步x会被赋值为Lambda的返回值，y会被赋值为2           计算:1+2
                //同上                                                    计算:3+4
                .reduce(0, (x, y) -> x + y);

        System.out.println(sum);

        System.out.println("----------------------------------------");

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                //Double::sum是jdk1.8添加的方法，返回两个相加的数
                .reduce(Double::sum);

        System.out.println(op.get());
    }


    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test3(){
        List<String> list = emps.stream()
                .map(Employee::getName)
                //转换为List
                .collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("----------------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                //转换为Set
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("----------------------------------");

        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                //使用自定义的集合类
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    @Test
    public void test4(){
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));

        System.out.println(max.get());

        Optional<Employee> op = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        System.out.println(op.get());

        //工资总和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println(sum);

        //工资平均值
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(avg);

        //总数
        Long count = emps.stream()
                .collect(Collectors.counting());

        System.out.println(count);

        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(dss.getMax());
    }

    //分组
    @Test
    public void test5(){
        Map<Employee.Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        map.forEach(((status, employees) -> {
            System.out.println(status);
            employees.forEach(System.out::println);
            System.out.println("----------------");
        }));
//        System.out.println(map);
    }

    //多级分组
    @Test
    public void test6(){
        Map<Employee.Status, Map<String, List<Employee>>> map = emps.stream()
                //先根据状态(Status)分组，再根据年龄(age)分组
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 60)
                        return "老年";
                    else if(e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));

        System.out.println(map);
    }

    //分区/分片
    @Test
    public void test7(){
        Map<Boolean, List<Employee>> map = emps.stream()
                //根据员工薪水分片
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));
        map.forEach(((aBoolean, employees) -> {
            System.out.println(aBoolean);
            employees.forEach(System.out::println);
        }));
    }

    @Test
    public void test8(){
        String str = emps.stream()
                .map(Employee::getName)
                //output:李四张三王五赵六赵六赵六田七
//                .collect(Collectors.joining());
                //output:李四,张三,王五,赵六,赵六,赵六,田七
//                .collect(Collectors.joining(","));
                //output:==李四,张三,王五,赵六,赵六,赵六,田七==
                .collect(Collectors.joining(",","==","=="));
        System.out.println(str);
    }

}
