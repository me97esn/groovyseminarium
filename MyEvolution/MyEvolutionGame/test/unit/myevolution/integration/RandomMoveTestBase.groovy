package myevolution.integration

import myevolution.BehaviourUtils
import myevolution.Behaviours
import myevolution.World
import myevolution.WorldBuilder
import org.gmock.WithGMock
import static org.hamcrest.CoreMatchers.anything

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-03-02
 * Time: 12.15
 * To change this template use File | Settings | File Templates.
 */
abstract class RandomMoveTestBase extends GroovyTestCase {
    def random = 0.5
    def randomNewCoords
    def land
    def mockRandom

    void setUp() {
        randomNewCoords = [x:32, y:500]

        def mockBehaviourUtils = mock(BehaviourUtils)
        mockBehaviourUtils.static.randomNewCoords(anything(), anything()).returns(randomNewCoords)
        mockBehaviourUtils.static.merge(anything(), anything()).returns "000000000011111"
        mockBehaviourUtils.static.match(anything(), anything(), anything()).returns( 1.0 ).atLeastOnce()

        land = mock()
        land.getTileId(anything(), anything(), anything()).returns(null).atLeast 0
        land.getTileProperty(anything(), anything(), anything()).returns(null).atLeast 0

        def mockMath = mock(Math)
        mockMath.static.random().returns(random)
    }
}
