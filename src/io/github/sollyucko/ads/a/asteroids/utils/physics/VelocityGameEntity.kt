package io.github.sollyucko.ads.a.asteroids.utils.physics

import io.github.sollyucko.ads.a.asteroids.utils.GameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rectangular
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Shape
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector

// This is an interface to allow multiple inheritance
interface VelocityGameEntity<in G : Rectangular> : GameEntity<G>, Shape {
    val velocity: Vector

    override fun tick(game: G) {
        translate(velocity, game)
    }
}