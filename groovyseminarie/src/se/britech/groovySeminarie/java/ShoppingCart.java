package se.britech.groovySeminarie.java;

import java.math.BigDecimal;
import java.util.Collection;

import se.britech.groovySeminarie.groovy.Item;

public class ShoppingCart {
	Collection<Item> items;
	
	public double calcTotalPrice() {
		double price = 0.0;
		for (Item item : items) {
			price += ((BigDecimal)item.getPrice()).doubleValue();
		}
		return price;
	}
}
