package com.shoeshop.Model;

import java.util.ArrayList;
import java.util.List;

import annotation.Columns;
import annotation.Id;
import annotation.Table;


@Table(name = "Shoes")
public class Shoe {
	@Id(name = "id")
	private int id;
	@Columns(name = "name")
	private String name;
	@Columns(name = "size")
	private double size;
	@Columns(name = "price")
	private double price;
	private List<Purchase> purchases;
	
	
	public Shoe(int id,String name,double size,double price) {
		this.id = id;
		this.name = name;
		this.size = size;
		this.price = price;
	this.purchases = new ArrayList<Purchase>();
		
	}
	
	public Shoe(String name,double size,double price) {
		this.name = name;
		this.size = size;
		this.price = price;
	this.purchases = new ArrayList<Purchase>();
		
	}

    public void addPurchase(Purchase purchase){
        this.purchases.add(purchase);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Shoe{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", price ='" + price + '\'' +
                '}';
    }

   
    
    

}
