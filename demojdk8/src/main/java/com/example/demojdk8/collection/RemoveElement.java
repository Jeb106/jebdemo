package com.example.demojdk8.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件名：RemoveElement
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-20 22:11
 */
public class RemoveElement {

    public static void removeElementFor() {
        List<String> list = Lists.newArrayList("1", "12", "13", "14", "15", "0");

        System.out.println("加强 for 循环  循环删除元素，直接异常。");
        list = Lists.newArrayList("1", "12", "13", "14", "15", "0");
        for (String s : list) {
            if (s.contains("1")) {
                list.remove(s);
            }
        }
        System.out.println("过滤完：" + list.toString());
    }
    public static void removeElementFor(ArrayList<String> lists,String t) {

        for (int i = 0; i < lists.size(); i++) {
          //  if (t.equals(lists.get(i))) {
                lists.remove(i);
         //   }
        }
    }
   public static void removeElement(ArrayList<String> lists,Object t) {

        Iterator<String> iterator = lists.iterator();
        while (iterator.hasNext()) {
            if (t.toString().equals(iterator.next())) {
                lists.remove(t);
            }
        }
    }

 public static void removeElementJdk8(ArrayList<String> lists,String t) {

       lists.removeIf(a->a.contains(t));
     System.out.println(lists);
    }



    public static void main(String[] args) {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        lists.add("3");
       removeElementFor();
       // removeElementJdk8(lists, "3");
       // removeElementFor(lists, "3");
        //removeElement(lists, "2");
        System.out.println(lists);

    }
}
