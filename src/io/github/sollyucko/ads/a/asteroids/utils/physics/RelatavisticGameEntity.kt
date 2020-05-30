package io.github.sollyucko.ads.a.asteroids.utils.physics

import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rectangular
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Vector
import kotlin.math.tanh

/** Soft speed cap. */
interface RelatavisticGameEntity<in G: Rectangular>: VelocityGameEntity<G> {
    var rapidity: Vector
    val maxSpeed: Double
    val acceleration: Vector

    override val velocity
        get() = Vector(tanh(rapidity.x) * maxSpeed, tanh(rapidity.y) * maxSpeed)

    override fun tick(game: G) {
        rapidity += acceleration
        super.tick(game)
    }
}