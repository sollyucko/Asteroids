package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.physics.VelocityGameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.*

class Bullet(center: Point, private var orientation: Orientation) :
        Circle(center, RADIUS),
        VelocityGameEntity<Asteroids> {

    override fun rotate(rotation: Rotation) {
        orientation += rotation
    }

    override val velocity: Vector
        get() = orientation.unitVector * MOVEMENT_SPEED

    companion object {
        const val RADIUS = 2.0
        const val MOVEMENT_SPEED = 4.0
    }
}