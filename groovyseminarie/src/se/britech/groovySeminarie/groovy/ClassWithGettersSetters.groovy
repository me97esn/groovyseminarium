package se.britech.groovySeminarie.groovy
public class Book{
	def pages
	String title
	def author
}

def book = new Book()
book.pages = 300
book.setPages(300)

book.title = "Groovy recipies"
book.setTitle("Groovy recipies")

