package myevolution

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-03-10
 * Time: 11.20
 * To change this template use File | Settings | File Templates.
 */
class MathUtils {

    /**
     * Returnerar vinkel mellan 0 och 2PI
     * @return
     */
    static def toPositiveAngle = { angle ->
        def result
        if(angle < 0){
            result = 2 * Math.PI + angle // + eftersom -- => plus, vi vill dra ifrån en negativ vinkel
        }else{
            result = angle
        }
        result
    }
}
