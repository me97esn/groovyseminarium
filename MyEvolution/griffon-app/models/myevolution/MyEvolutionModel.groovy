package myevolution

import org.newdawn.slick.Color
import org.newdawn.slick.Graphics

class MyEvolutionModel {
    def graphics
    def world = new World()

    void load() {
        graphics = new Graphics()

        def behaviours = new Behaviours()

        world = new WorldBuilder(behaviours: [
                new Behaviour(condition: "11111", action: 'randomMove', threshold: 0.8),
                new Behaviour(condition: "11111", action: 'storeEnergy', threshold: 0.0),
                new Behaviour(condition: "11111", action: 'eat', threshold: 0.0),
                new Behaviour(condition: "11111", action: 'die', threshold: 0.0),
                new Behaviour(condition: "11111", action: 'mutate', threshold: 0.0),
                new Behaviour(condition: "11111", action: 'consumeEnergy', threshold: 0.0),
                new Behaviour(condition: "11111", action:'reproduce', threshold: 0.6),
                new Behaviour(condition: "11111", action: 'photosyntesis', threshold: 0.0)
        ], radius: 10).with {
            ____________________________
            ____________________________
            ____________________________
            ________o__o____o__o__o____o_
            __________________o__________
            _________o____o_o__o_____o_____o__
            ________o_____o__o____o_oo___o______
            __________o_______________o____
            _____o__o_____o____o____o_________
            ____o_________o____o____________
            _______o_______________o_____o__
            _________o__o___o_o______________
            ______o___________o___o_________
            __________________o__________
            _________o____o_o__o_____o_____o__
            ________o_____o__o____o_oo___o______
            __________________________________


        }.build()

        world.width = 1960
        world.height = 1480

        world.init()
    }
}