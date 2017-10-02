package application;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ItemListed {
	
   private String itemName;
   
   private double itemPrice;
   
   ItemListed()
   {
       itemName="";
       itemPrice = 0;
   }
   
   ItemListed(String itemName, double itemPrice)
   {
       this.itemName = itemName;
       this.itemPrice = itemPrice;
   }
   
   public String getItemName()
   {
	   return itemName;
	   }
   
   public double getItemPrice()
   {
	   return itemPrice;
	   }
   
   
   public void setItemName(String itemName)
   {
	   this.itemName = itemName;
	   }
   
   public void setItemPrice(double itemPrice)
   {
	   this.itemPrice = itemPrice;
	   }
}