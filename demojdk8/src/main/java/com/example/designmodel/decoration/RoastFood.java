package com.example.designmodel.decoration;

/**
   * 烤食物
  * @author xinye
  *
  */
 public class RoastFood extends FoodDecoration {
     
     private Food food;
     
     public RoastFood(Food f){
         this.food = f;
     }
     
     @Override
     public String getDesc() {
         return getDecoration() + food.getDesc();
     }
     
     private String getDecoration(){
         return "烤";
     }
 }