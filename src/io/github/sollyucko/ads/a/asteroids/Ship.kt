package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.GameEntity
import io.github.sollyucko.ads.a.asteroids.utils.physics.RelatavisticGameEntity
import io.github.sollyucko.ads.a.asteroids.utils.physics.RotatingGameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.lang.RuntimeException

class Ship(anchor: Point, orientation: Orientation) :
        Polygon(
            arrayOf(
                Point(10.0, 0.0),
                Point(0.0, -5.0),
                Point(5.0, 0.0),
                Point(0.0, 5.0)
            ), anchor, orientation
        ),
        RelatavisticGameEntity<Asteroids>,
        RotatingGameEntity<Asteroids>,
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
        super<RelatavisticGameEntity>.tick(game)
        super<RotatingGameEntity>.tick(game)

        if (firing) {
            game.addBullet(Bullet(anchor.copy(), orientation))
        }
    }

    override var rapidity: Vector = Vector.ZERO

    override val acceleration: Vector
        get() = if (forward) {
            orientation.unitVector * ACCELERATION
        } else {
            Vector.ZERO
        }

    override val maxSpeed: Double
        get() = MAX_SPEED

    @Suppress("BooleanLiteralArgument")
    override val rotationSpeed: Rotation
        get() = when(Pair(turnLeft, turnRight)) {
            Pair(false, false) -> Rotation.ZERO
            Pair(false, true) -> TURN_SPEED
            Pair(true, false) -> -TURN_SPEED
            Pair(true, true) -> Rotation.ZERO
            else -> throw RuntimeException("WTF?!")
        }

    companion object {
        const val ACCELERATION = .15
        const val MAX_SPEED = 3.0
        val TURN_SPEED = Rotation.fromDegrees(1.0)
    }
}