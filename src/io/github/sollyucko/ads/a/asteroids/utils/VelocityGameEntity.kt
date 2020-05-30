package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.Asteroids
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Shape
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector

// This is an interface to allow multiple inheritance
interface VelocityGameEntity : GameEntity, Shape {
    val velocity: Vector

    override fun tick(game: Asteroids) {
        translate(velocity, game)
    }
}