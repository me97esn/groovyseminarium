package se.britech.groovySeminarie.groovy
public class Magazine{
	def pages
	String title
	def author
}

def book = new Magazine()
book.pages = 300
book.setPages(300)

book.title = "Groovy recipies"
book.setTitle("Groovy recipies")

