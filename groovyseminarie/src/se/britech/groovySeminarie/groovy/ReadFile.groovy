package se.britech.groovySeminarie.groovy
def url = "C:/programmering/workspace/groovyseminarie/dokument/aFileWithText.txt"
new File(url).eachLine{
	println it
}
// filen stängs automatiskt när eachLine avslutas
// (Execute Around Method pattern)
