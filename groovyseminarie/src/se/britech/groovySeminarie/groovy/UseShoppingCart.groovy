package se.britech.groovySeminarie.groovyimport se.britech.groovySeminarie.java.ShoppingCart
def cart = new ShoppingCart()
def lingonsylt=new Item(name:"Lingonsylt", price: 49.50)
def blodpudding=new Item(name:"Blodpudding", price:9.90)
cart.items = [lingonsylt, blodpudding]

println "Total price: ${cart.calcTotalPrice()}"