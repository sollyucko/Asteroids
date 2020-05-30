package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.Asteroid
import io.github.sollyucko.ads.a.asteroids.Asteroids
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Shape
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector

// This is an interface to allow multiple inheritance
interface InertialGameEntity : GameEntity, Shape {
    var directionDegrees: Double
    val movementSpeed: Double

    override fun tick(game: Asteroids) {
        translate(Vector(1.0, 0.0).rotated(directionDegrees) * movementSpeed, game)
    }
}