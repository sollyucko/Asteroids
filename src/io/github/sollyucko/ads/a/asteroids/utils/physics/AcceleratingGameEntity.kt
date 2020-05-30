package io.github.sollyucko.ads.a.asteroids.utils.physics

import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rectangular
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector

interface AcceleratingGameEntity<in G: Rectangular> : VelocityGameEntity<G> {
    override var velocity: Vector
    val acceleration: Vector

    override fun tick(game: G) {
        velocity += acceleration
        super.tick(game)
    }
}