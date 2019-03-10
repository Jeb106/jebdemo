package com.example.designmodel.prototype.simple;

import com.example.moudle.retry.Student;

import java.util.ArrayList;
import java.util.Date;

/**
 * 文件名：ClonTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 22:12
 */
public class ClonTest {
    public static void main(String[] args) {
        StudentClon t1 = new StudentClon();
        t1.setAge(11);
        t1.setName("jeb");
        t1.setBirthday(new Date());

        ArrayList<Student> objects = new ArrayList<>();  objects.add(new Student());
        t1.setStudentList(objects);

        try {
            StudentClon clone = (StudentClon)t1.clone();
            System.out.println(clone == t1);
            System.out.println(clone.getStudentList() == t1.getStudentList());
            System.out.println(clone.getName() +"_"+ t1.getName());
            System.out.println(clone.getBirthday().getTime() +"_"+ t1.getBirthday().getTime());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

}
