package se.britech.groovySeminarie.java;

import java.util.Collection;
import java.util.LinkedList;

import se.britech.groovySeminarie.groovy.Item;

public class UseShoppingCart {
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		
		Item lingonsylt = new Item();
		lingonsylt.setName("lingonsylt");
		lingonsylt.setPrice(59.50);
		
		Item blodpudding = new Item();
		blodpudding.setName("blodpudding");
		blodpudding.setPrice(9.90);
		
		Collection<Item> items = new LinkedList<Item>();
		items.add(lingonsylt);
		items.add(blodpudding);
		
		cart.setItems(items);
		
		System.out.println("Total price: "+cart.calcTotalPrice());
	}
}
