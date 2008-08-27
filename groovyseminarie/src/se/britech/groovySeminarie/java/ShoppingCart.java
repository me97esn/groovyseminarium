package se.britech.groovySeminarie.java;

import java.util.Collection;

import se.britech.groovySeminarie.groovy.Item;

public class ShoppingCart {
	Collection<Item> items;
	
	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public double calcTotalPrice() {
		double price = 0.0;
		for (Item item : items) {
			price += (Double)item.getPrice();
		}
		return price;
	}
}
