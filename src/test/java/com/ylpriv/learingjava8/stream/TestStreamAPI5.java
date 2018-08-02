package com.ylpriv.learingjava8.stream;

import com.ylpriv.learingjava8.entiry.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
}
