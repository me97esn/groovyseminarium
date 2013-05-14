package myevolution

import griffon.core.GriffonApplication
import org.codehaus.griffon.runtime.core.AbstractGriffonController
import org.newdawn.slick.GameContainer

import org.newdawn.slick.tiled.TiledMap

class MyEvolutionController extends AbstractGriffonController {
    private MyEvolutionModel model;

    public void setModel(MyEvolutionModel model) { this.model = model; }

    public void onSlickInit(GriffonApplication app, GameContainer container) {
        container.minimumLogicInterval = 1
        container.maximumLogicInterval = 0.001
        model.world.land = new TiledMap("data/world_map3.tmx")
        model.load();
    }

    public void onSlickUpdate(GriffonApplication app, GameContainer container, int delta) {
        model.world.update()
    }
}
