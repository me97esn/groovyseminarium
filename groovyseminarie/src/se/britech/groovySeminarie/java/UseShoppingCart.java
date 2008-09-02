package se.britech.groovySeminarie.java;
import java.util.Collection;import java.util.LinkedList;import se.britech.groovySeminarie.groovy.Item;
public class UseShoppingCart {
	public static void main(String[] args) {
		Item lingonsylt = new Item();
		lingonsylt.setName("lingonsylt");
		lingonsylt.setPrice(59.50);
		
		Item blodpudding = new Item();
		blodpudding.setName("blodpudding");
		blodpudding.setPrice(9.90);
		
		Collection<Item> items = new LinkedList<Item>();
		items.add(lingonsylt);
		items.add(blodpudding);
		
		ShoppingCart cart = new ShoppingCart();
		cart.setItems(items);
		System.out.println("Total price: "+cart.calcTotalPrice());
	}
}
