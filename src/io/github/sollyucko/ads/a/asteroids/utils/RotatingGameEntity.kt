package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.Asteroids
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Orientation
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rotation

interface RotatingGameEntity: GameEntity {
    val rotationSpeed: Rotation
    var orientation: Orientation

    override fun tick(game: Asteroids) {
        orientation += rotationSpeed
    }
}