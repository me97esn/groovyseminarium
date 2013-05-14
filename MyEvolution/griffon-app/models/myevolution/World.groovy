package myevolution

import org.newdawn.slick.tiled.TiledMap

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-01-22
 * Time: 21.54
 * To change this template use File | Settings | File Templates.
 */
class World implements Serializable {
    def counter = 0
    def organisms = []
    def food = [] // Temporär, ska väl snarare vara andra organismer som är "mat"

    def width = 960
    def height = 480
    def land

    def allBehaviours = []
    def newOrganisms = []

    def organismsPerCoord = new Object[width][height]

    Organism organismWithId(theId) {
        organisms.find {it.id == theId}
    }

    def verifyUniqueOrganismIds(orgs) {
        def orgsSortedPerId = [:]
        orgs.each {org ->
            if (orgsSortedPerId.get(org.id)) throw new RuntimeException("Duplicated organism id:s $org.id")
            orgsSortedPerId[org.id] = org
        }
    }

    def init() {
        verifyUniqueOrganismIds(organisms)
        land = new TiledMap("data/world_map3.tmx")
    }

    def update() {
        counter++

        // Clean out dead organisms
        organisms = organisms.findAll {it.alive}

        // Clear organismsPerCoord so any organism that has moved won't be left on the old coords
        // TODO this shouldn't be necessary to do on each organism, only the effected onces are necessary
        organismsPerCoord = new Object[width][height]

        organisms.each {Organism organism ->
            // Put the organism on it's coords
            organism.coveredCoords().each {x, y ->
                try {
                    organismsPerCoord[x][y] = organism
                } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    organismsPerCoord[x][y] = organism

                }
            }
        }

        if (counter % 10 == 0) {
            println "number of organisms: ${organisms.size()}"
        }

        organisms?.each {Organism organism ->
            // organism.radius = organism.energyLevel / 20
            organism.behave()
        }
        organisms += newOrganisms
        newOrganisms.clear()
    }

    def getTileIdByPixelCoords(x, y, z = 0, tileWidth = 32, tileHeight = 32) {
        def tileXCoord = x / tileWidth as int
        def tileYCoord = y / tileHeight as int

        land?.getTileId(tileXCoord, tileYCoord, z)
    }

    def representSituation(Organism org) {
        def result = ""

        // org.energylevel
        switch (org.energyLevel) {
            case 0..100: result = "000" + result; break
            case 101..500: result = "001" + result; break
            case 501..1000: result = "010" + result; break
            case 1001..2000: result = "100" + result; break
            default: result = "111" + result
        }

        result
    }

    def blocked(x, y) {

        try {
            def newTileId = getTileIdByPixelCoords(x, y, 1)
            def result = land?.getTileProperty(newTileId, "blocked", "")
        } catch (ArrayIndexOutOfBoundsException e) {
            // Outside of the map
            return true
        }

    }

    def organismAt(int x, int y) {
        if ((0..<width).containsWithinBounds(x) && (0..<height).containsWithinBounds(y)) {
            return organismsPerCoord[x][y]
        } else {
            // Outside of the world's boundaries
            return null
        }
    }
}
