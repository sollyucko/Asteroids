package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.VelocityGameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Ship(anchor: Point, orientation: Orientation) :
        Polygon(
            arrayOf(
                Point(10.0, 0.0),
                Point(0.0, -5.0),
                Point(5.0, 0.0),
                Point(0.0, 5.0)
            ), anchor, orientation
        ),
        VelocityGameEntity,
        KeyListener {

    private var forward: Boolean = false
    private var turnLeft: Boolean = false
    private var turnRight: Boolean = false
    private var firing: Boolean = false

    override fun keyTyped(e: KeyEvent) {}

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_LEFT -> turnLeft = true
            KeyEvent.VK_RIGHT -> turnRight = true
            KeyEvent.VK_UP -> forward = true
            KeyEvent.VK_SPACE -> firing = true
        }
    }

    override fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_LEFT -> turnLeft = false
            KeyEvent.VK_RIGHT -> turnRight = false
            KeyEvent.VK_UP -> forward = false
            KeyEvent.VK_SPACE -> firing = false
        }
    }

    override fun tick(game: Asteroids) {
        if (forward) {
            super.tick(game)
        }
        if (turnLeft) {
            rotate(-TURN_SPEED)
        }
        if (turnRight) {
            rotate(TURN_SPEED)
        }
        if (firing) {
            game.addBullet(Bullet(anchor.copy(), orientation))
        }
    }

    override val velocity: Vector
        get() = orientation.unitVector * MOVEMENT_SPEED

    companion object {
        const val MOVEMENT_SPEED = 1.5
        val TURN_SPEED = Rotation.fromDegrees(1.0)
    }
}