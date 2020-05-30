package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.Asteroids
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector
import kotlin.math.tanh

/** Soft speed cap. */
interface RelatavisticGameEntity: VelocityGameEntity {
    var rapidity: Vector
    val maxSpeed: Double
    val acceleration: Vector

    override val velocity
        get() = Vector(tanh(rapidity.x) * maxSpeed, tanh(rapidity.y) * maxSpeed)

    override fun tick(game: Asteroids) {
        rapidity += acceleration
        super.tick(game)
    }
}