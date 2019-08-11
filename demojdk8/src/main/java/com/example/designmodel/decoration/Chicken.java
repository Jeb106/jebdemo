package com.example.designmodel.decoration;

/**
  * 鸡肉
  * @author xinye
  *
  */
 public class Chicken extends Food {
     public Chicken(){
         desc = "鸡肉";
     }
     @Override
     public String getDesc() {
         return desc;
     }
 
 }