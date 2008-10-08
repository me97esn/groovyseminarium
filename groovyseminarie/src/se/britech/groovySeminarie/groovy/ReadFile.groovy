package se.britech.groovySeminarie.groovy
new File("C:/text.txt").eachLine{
	println it
}
// filen stängs automatiskt när eachLine avslutas
// (Execute Around Method pattern)
