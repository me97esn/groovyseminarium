package myevolution

import org.gmock.WithGMock

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-03-12
 * Time: 19.21
 * To change this template use File | Settings | File Templates.
 */
@WithGMock
class WorldTests extends GroovyTestCase {
    void test_all_organism_ids_are_unique() {
        new World().verifyUniqueOrganismIds([new Organism("1", 1.0, 1.0, 1.0), new Organism("2", 1.0, 1.0, 1.0)])
        // Inga fel kastade
    }

    /**
     * lägen på energiNivå:
     * 000: <= 20
     * 001: <= 100
     * 010: <= 500
     * 100:  <= 1000
     * 101:  <= 2000
     * 111: >  2000
     */
    void test_representSituation_with_energyLevel() {
        // Doesn't touch food -> last char = '0'
        def world = new World()
        def org = mock(Organism) {
        }
        // Note that the last zero is for touching food
        org.energyLevel.returns 0
        org.energyLevel.returns 99
        org.energyLevel.returns 100

        org.energyLevel.returns 499
        org.energyLevel.returns 500

        org.energyLevel.returns 999
        org.energyLevel.returns 1000

        org.energyLevel.returns 1001
        org.energyLevel.returns 1999
        org.energyLevel.returns 2000

        org.energyLevel.returns 3000

        play {
            assertEquals("000", world.representSituation(org))
            assertEquals("000", world.representSituation(org))
            assertEquals("000", world.representSituation(org))

            assertEquals("001", world.representSituation(org))
            assertEquals("001", world.representSituation(org))

            assertEquals("010", world.representSituation(org))
            assertEquals("010", world.representSituation(org))

            assertEquals("100", world.representSituation(org))
            assertEquals("100", world.representSituation(org))
            assertEquals("100", world.representSituation(org))

            assertEquals("111", world.representSituation(org))
        }
    }

    /**
     * Detta används egentligen inte nu, men om jag ska använda en bytearray-repr av världen är det såhär man kan göra det.
     * TODO bör väl isf vara ett subset av världen?
     */
    void _test_world_as_byte_array() {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new World());
        oos.flush();
        oos.close();
        bos.close();
        bytes = bos.toByteArray();
        assertNotNull bytes

        BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[(bytes.length - i / 8 - 1) as int] & (1 << (i % 8))) > 0) {
                bits.set(i);
            }
        }
        assertNotNull bits

    }

    def assertBlocked(tileProperty, closure) {
        def world = new World(
                land: mock() {
                    getTileProperty(0, "blocked", "").returns tileProperty
                }
        )
        def worldMock = mock(world)
        def x = 0, y = 0, z = 1
        worldMock.getTileIdByPixelCoords(x, y, z).returns 0

        play {
            closure(worldMock, x, y)
        }
    }

    void test_is_blocked() {
        assertBlocked(true) {worldMock, x, y ->
            assertTrue worldMock.blocked(x, y)
        }
    }

    void test_is_not_blocked() {
        assertBlocked(false) {worldMock, x, y ->
            assertFalse worldMock.blocked(x, y)
        }
    }

    void test_all_duplicated_organism_ids() {
        shouldFail {
            new World().verifyUniqueOrganismIds([new Organism("1", 1.0, 1.0, 1.0), new Organism("1", 1.0, 1.0, 1.0)])
        }
    }

    void test_organismAt() {
        def org1 = mock()
        def org2 = mock()
        World world = new World()
        world.with{
            organismsPerCoord = new Object[width][height]
            organismsPerCoord[0][0] = org1
            organismsPerCoord[0][1] = org1
            organismsPerCoord[0][10] = org2
        }

        play{
            assertEquals org1, world.organismAt( 0,0 )
            assertEquals org1, world.organismAt( 0,1 )
            assertEquals org2, world.organismAt( 0,10 )
            assertNull world.organismAt( 10,10 )
        }
    }
}
