package se.britech.groovySeminarie.groovy
def url = "C:/programmering/workspace/groovyseminarie/dokument/aFileWithText.txt"
new File(url).eachLine{
	println it
}
// filen st�ngs automatiskt n�r closure:t avslutas
