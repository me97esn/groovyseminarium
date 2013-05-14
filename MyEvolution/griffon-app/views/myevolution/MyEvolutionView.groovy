package myevolution

import org.newdawn.slick.Color
import org.apache.commons.lang.builder.ToStringBuilder

application(title: 'MyEvolution',
        size: [960, 480],
        icon: 'griffon-icon-48x48.png',
        icons: ['griffon-icon-48x48.png',
                'griffon-icon-32x32.png',
                'griffon-icon-16x16.png']) {
  app.game.onRender = { container, g ->
    model.with {
      world.land.render 0,0
      //land.draw(0, 0)

      Color bg = new Color(0xFF, 0xFF, 0xFF)
      graphics.currentColor = bg
      // graphics.fillRect(0, 0, world.width, world.height)

      Color c = new Color(99, 00, 99)

      graphics.currentColor = c

      float sizeOfAllOrganisms = 10

      world.organisms.each { organism->
        graphics.currentColor = organism.color
        graphics.fill organism
      }

      world.food.each { food->
        graphics.currentColor = food.color
        graphics.fill food
      }
    }

  }
}
