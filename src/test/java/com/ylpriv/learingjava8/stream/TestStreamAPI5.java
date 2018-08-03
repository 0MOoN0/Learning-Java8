package com.ylpriv.learingjava8.stream;

import com.ylpriv.learingjava8.entiry.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TestStreamAPI5 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test1(){
        boolean bl = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(bl);

        boolean bl1 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(bl1);

        boolean bl2 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(bl2);
    }
    @Test
    public void test2(){
        //Optional 容器，orElse(T other)，如果为空，可以使用其他对象代替
        Optional<Employee> op = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(op.get());

        System.out.println("--------------------------------");

        Optional<Employee> op2 = emps.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();

        System.out.println(op2.get());
    }

    @Test
    public void test3(){
        long count = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .count();

        System.out.println(count);

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                //通过compare方法比较两个数的大小，再通过reduce比较Stream中所有的数
                .max(Double::compare);

        System.out.println(op.get());

        Optional<Employee> op2 = emps.stream()
                //min方法的实现方法于max相同，不同的地方是比较方法中，当a小于等于b时，返回a
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

        System.out.println(op2.get());
    }

    //注意：流进行了终止操作后，不能再次使用
    @Test
    public void test4(){
        Stream<Employee> stream = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE));

        long count = stream.count();

        //java.lang.IllegalStateException: stream has already been operated upon or closed
        stream.map(Employee::getSalary)
                .max(Double::compare);
    }

}
