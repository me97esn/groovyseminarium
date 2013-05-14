package myevolution

import static org.hamcrest.CoreMatchers.anything
import org.junit.Test
import org.junit.Assert
import org.gmock.WithGMock
import org.junit.Before

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-03-11
 * Time: 13.13
 * To change this template use File | Settings | File Templates.
 */
class MathUtilsTests {

    @Test
    void test_toPositiveAngle_positive_angle() {
            Assert.assertEquals(3.14, MathUtils.toPositiveAngle(3.14), 0.0)
    }

    @Test
    void test_atan2_negative_angle() {
        Assert.assertEquals(2*Math.PI - 1.5, MathUtils.toPositiveAngle(-1.5), 0.01)
    }
}
