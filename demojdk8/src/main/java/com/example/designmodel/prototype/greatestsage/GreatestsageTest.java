package com.example.designmodel.prototype.greatestsage;

import com.example.designmodel.prototype.CloneUtil;
import com.example.designmodel.prototype.simple.StudentClon;
import com.example.moudle.retry.Student;

import java.util.ArrayList;
import java.util.Date;

/**
 * 文件名：GreatestsageTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 23:53
 */
public class GreatestsageTest {
    public static void main(String[] args) {
        StudentClon t1 = new StudentClon();
        t1.setAge(11);
        t1.setName("jeb");
        t1.setBirthday(new Date());

        ArrayList<Student> objects = new ArrayList<>();  objects.add(new Student());
        t1.setStudentList(objects);

        StudentClon clone = CloneUtil.clone(t1);

        System.out.println(t1 == clone);
        System.out.println(clone.getStudentList() == t1.getStudentList());
        System.out.println(clone.getStudentList());
        System.out.println(t1.getStudentList());


    }
}
