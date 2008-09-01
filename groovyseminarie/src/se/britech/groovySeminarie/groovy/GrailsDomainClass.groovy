package se.britech.groovySeminarie.groovy
class Book {
   Long id
   Long version
   String title
   Date releaseDate
   String author
}

def b = Book.findByTitle("The Shining")
b = Book.findByTitleAndAuthor("The Sum of All Fears", "Tom Clancy")
b = Book.findByReleaseDateBetween(firstDate, new Date())
b = Book.findByReleaseDateGreaterThanEquals(firstDate)
b = Book.findByReleaseDateLessThanEquals(firstDate)
b = Book.findByTitleLike("%Hobbit%")
b = Book.findByTitleIlike("%Hobbit%") // (since 0.5) - ignorecase
b = Book.findByTitleNotEqual("Harry Potter")
b = Book.findByReleaseDateIsNull()
b = Book.findByReleaseDateIsNotNull()
