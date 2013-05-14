package emils
public class Magazine{
	private def pages
	String title
	def author
}

def book = new Magazine()
book.pages = 200 // S�tt property
// book.setPages(300) // Setter finns inte till private properties

book.title = "Groovy recipies" // S�tt property
book.setTitle("Groovy recipies") // Setter genereras vid kompilering

