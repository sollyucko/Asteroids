package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.InertialGameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Circle
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Point

class Bullet(center: Point, override var directionDegrees: Double) :
        Circle(center, RADIUS),
        InertialGameEntity {

    override fun rotate(degrees: Double) {
        directionDegrees += degrees
    }

    override val movementSpeed
        get() = MOVEMENT_SPEED

    companion object {
        const val RADIUS = 2.0
        const val MOVEMENT_SPEED = 4.0
    }
}