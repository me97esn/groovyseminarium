package myevolution

import org.newdawn.slick.Color

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-03-16
 * Time: 09:46
 *
 */
class BehaviourUtils {

    static Color colorForDna(String dnaStr, maxValue=0xffffff) {
        def maxDna = ""
        dnaStr.length().times{maxDna += "1"}
        def match = BehaviourUtils.match(maxDna, dnaStr)

        new Color(maxValue * match as int)
    }

    static def randomNewCoords(Organism organism, length) {
        // vinkel alltid mellan -pi och pi
        def direction = MathUtils.toPositiveAngle(-Math.PI + 2 * Math.PI * Math.random())

        def x = organism.centerX
        def y = organism.centerY

        def newX = x + Math.cos(direction) * length
        def newY = y - Math.sin(direction) * length // -1 på enhetscirkeln => +1 i y-koordinat
        [x: newX as float, y: newY as float]
    }

    static def match(dna, represention, startindex = 0) {
        def matchingChars = 0.0
        if (dna.length() < represention.length()) throw new MyEvolutionException("DNA cannot be shorter than representation")

        def cutDownDna = dna.substring(startindex, represention.length() + startindex)

        for (int i = 0; i < cutDownDna.length(); i++) {
            if (represention[i] == cutDownDna[i]) matchingChars++

        }
        matchingChars / represention.length()
    }

    static def merge(representation1, representation2) {
        representation1 + representation2
    }

    static def randomDnaChange(def dna, float amountToChange = 0.05) {
        def result = ""
        dna.each {c ->
            if (Math.random() <= amountToChange) {
                // toggle the char: 1 => 0, 0 => 1
                if (c == "1") {
                    result += "0"
                } else {
                    result += "1"
                }
            } else {
                result += c
            }
        }
        result
    }
}
