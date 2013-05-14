package myevolution

import junit.framework.TestCase
import org.gmock.WithGMock
import org.newdawn.slick.geom.Rectangle
import static org.hamcrest.CoreMatchers.anything

@WithGMock
class OrganismTests extends GroovyTestCase {
    def organism = new Organism(10, 10, 1)

    protected void setUp() {
        super.setUp()
    }

    void test_bounding_box() {
        def organism = new Organism(1, 5, 5, 1)
        assertEquals(organism.centerX, organism.boundingBox().centerX, 0.01f)
        assertEquals(organism.centerY, organism.boundingBox().centerY, 0.01f)
        assertEquals( organism.maxX, organism.boundingBox().maxX, 0.01f )
        assertEquals( organism.maxY, organism.boundingBox().maxY, 0.01f )

        def coveredPixels = []
        (4..6).each{x->
            (4..6).each{y->
                coveredPixels << [x,y]
            }
        }

        assertEquals coveredPixels.sort(), organism.boundingBox().coveredCoords().sort()
    }

    void test_bounding_box_with_other_coords() {
        def organism = new Organism(1, 5, 5, 5)
        def box = organism.boundingBox(100,1000)
        assertEquals(100, box.centerX, 0.5f)
        assertEquals(1000, box.centerY, 0.5f)
    }

    protected void tearDown() {
        super.tearDown()
    }

    /**
     * 1      3        4
     *
     *
     *
     * 2      o        4b
     *
     *
     * 5      6        7
     *
     */
    void testCalcDirection() {
        def org1 = new Organism(0, 0, 1)
        def org2 = new Organism(0, 10, 1)
        def org3 = new Organism(10, 0, 1)
        def org4 = new Organism(20, 0, 1)
        def org4b = new Organism(20, 10, 1)
        def org5 = new Organism(0, 20, 1)
        def org6 = new Organism(10, 20, 1)
        def org7 = new Organism(20, 20, 1)

        assertEquals(angleOf(org1), 90 + 45)
        assertEquals(angleOf(org2), 180)
        assertEquals(angleOf(org3), 90)

        assertEquals(angleOf(org4), 45)
        assertEquals(angleOf(org4b), 0)
        assertEquals(angleOf(org5), -135)
        assertEquals(angleOf(org6), -90)
        assertEquals(angleOf(org7), -45)
    }

    def angleOf(org) {
        Math.toDegrees(organism.calcDirection(org)) as int
    }

    void test_that_direction_equals_direction_of_organism() {
        def organism = new Organism(10, 10, 10)
        organism.direction = Math.PI / 2

        assertTrue organism.direction - organism.calcDirection(new Organism(10, 0, 1)) < 0.000001
    }

    void testCalcDistance() {
        def org1 = new Organism(0, 0, 1)
        def org2 = new Organism(0, 10, 1)
        def org3 = new Organism(10, 0, 1)

        assertEquals(
                organism.calcDistance(org1),
                Math.hypot(10, 10) - organism.radius - org1.radius

        )
        assertEquals(organism.calcDistance(org2), 10 - organism.radius - org2.radius)

        assertEquals(organism.calcDistance(org3), 10 - organism.radius - org3.radius)
    }

    /**
     * def match = organism.match(mergedRepresentation)
     * Match ger en float procentuell matchning
     */
    void test_match_perfect_match() {
        def organism = new Organism(1, 1, 1, 1, "0111001100")
        assertEquals(1.0, organism.match("0111001100")) // perfect match
        assertEquals(0.5, organism.match("1000101100")) // partial match
        assertEquals(0.0, organism.match("1000110011")) // no match
    }

    void test_match_dna_is_longer_than_representation() {
        def organism = new Organism(1, 1, 1, 1, "0000011")

        assertEquals(1.0, organism.match("000"), 0)
        assertEquals(1.0, organism.match("000"))
    }

    void test_match_dna_is_shorter_than_representation() {
        def organism = new Organism(1, 1, 1, 1, "0000")
        shouldFail(MyEvolutionException) {
            organism.match("000000")
        }
    }

    void test_merged_dna_with_position_should_be_partial_match() {
        def organism = new Organism(1, 1, 1, 1, "1011110101001101101010110000110010100111000001000100101110010001010000011111101010110100010010011000")
        def match = organism.match("000000000011111", 15)
        assertTrue("Match should be between 0 and 1, was: $match", (0.01..0.99).containsWithinBounds(match))
    }

    void test_collides_no_collisions() {
        def organism = new Organism(1, 1, 1, 1)
        def orgMock = mock(organism){
            coveredCoords(anything(),anything()).returns([[0,0]])
        }
        organism.world = mock(World){
            organismAt(0, 0).returns null
        }
        play {
            def collides = organism.collides()
            assertEquals(collides, [])
        }
    }

    void test_collides_when_it_covers_another_organism() {
        def organism = new Organism(1, 1, 1, 1)
        def anotherOrganism = mock(Organism)
        def world = mock(World)
        organism.world = world
        def mockOrg = mock(organism)

        def coords = [[0, 0], [0, 1], [1, 0], [1, 1]]
        mockOrg.coveredCoords(anything(), anything()).returns(coords)
        world.organismAt(anything(), anything()).returns(anotherOrganism).times(2)
        world.organismAt(anything(), anything()).returns(organism).times(2)

        play {
            assertEquals([anotherOrganism], organism.collides())
        }
    }

    void test_behave() {
         // TODO test it
    }
}



