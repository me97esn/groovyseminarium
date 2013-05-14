package myevolution

import org.newdawn.slick.geom.Rectangle

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-27
 * Time: 15:43
 *
 */
class BoundingBox extends Rectangle {
    BoundingBox(float minX, float minY, float width, float height) {
        super(minX, minY, width, height)
    }

    def coveredCoords() {
        def result = []
        (minY..maxY).each {y ->
            (minX..maxX).each {x ->
                result << [x as int, y as int]
            }
        }
        result
    }
}
