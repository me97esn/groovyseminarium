package se.britech.groovySeminarie.groovy
new File("C:/programmering/workspace/groovyseminarie/dokument/aFileWithText.txt").eachLine{
	println it
}
// filen stängs automatiskt när eachLine avslutas
// (Execute Around Method pattern)
