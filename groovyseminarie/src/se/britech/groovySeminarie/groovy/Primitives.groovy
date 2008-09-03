package se.britech.groovySeminarie.groovy
int i = 1
println i.class
println 1.class

def d = 1.1
println d.class

def d2 = 1.1 as double
println d2.class

d2 = 1.1 as Double
println d2.class

3.times{println "Hello $it"}