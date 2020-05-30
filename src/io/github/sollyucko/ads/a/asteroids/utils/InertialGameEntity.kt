package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.Asteroids
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Orientation
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Shape
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector

// This is an interface to allow multiple inheritance
interface InertialGameEntity : GameEntity, Shape {
    var orientation: Orientation
    val movementSpeed: Double

    override fun tick(game: Asteroids) {
        translate(
            Vector.RIGHT.rotated(orientation - Orientation.RIGHT) * movementSpeed,
            game
        )
    }
}