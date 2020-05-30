package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.Asteroids
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector

interface AccelerationGameEntity : VelocityGameEntity {
    override var velocity: Vector
    val acceleration: Vector

    override fun tick(game: Asteroids) {
        velocity += acceleration
        super.tick(game)
    }
}