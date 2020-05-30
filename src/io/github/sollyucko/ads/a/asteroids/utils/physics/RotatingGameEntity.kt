package io.github.sollyucko.ads.a.asteroids.utils.physics

import io.github.sollyucko.ads.a.asteroids.utils.GameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Orientation
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rectangular
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rotation

interface RotatingGameEntity<in G: Rectangular>: GameEntity<G> {
    val rotationSpeed: Rotation
    var orientation: Orientation

    override fun tick(game: G) {
        orientation += rotationSpeed
    }
}