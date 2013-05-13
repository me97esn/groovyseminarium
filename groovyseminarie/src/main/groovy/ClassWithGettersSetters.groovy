package se.britech.groovySeminarie.groovy
public class Magazine{
	private def pages
	String title
	def author
}

def book = new Magazine()
book.pages = 200 // Sätt property
// book.setPages(300) // Setter finns inte till private properties

book.title = "Groovy recipies" // Sätt property
book.setTitle("Groovy recipies") // Setter genereras vid kompilering

