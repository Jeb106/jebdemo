package com.example.designmodel.prototype.simple;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * 文件名：StudentClon
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 22:10
 */
@Data
public class StudentClon implements Cloneable, Serializable {

    private static final long serialVersionUID = -6593637145295680825L;
    private String name;
    private int age;
    private Date birthday;
    public ArrayList studentList;

    @Override
    protected Object clone() throws CloneNotSupportedException {
         StudentClon studentClon = null;
        try {
            studentClon = (StudentClon)super.clone();
            studentClon.studentList  = (ArrayList)studentList.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentClon;
    }
}





