package io.github.sollyucko.ads.a.asteroids.utils.geometry

import io.github.sollyucko.ads.a.asteroids.utils.mod
import java.lang.Math.toDegrees
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

// Zero corresponds to right. Values increase counter-clockwise.
@Suppress("DataClassPrivateConstructor", "unused")
data class Orientation private constructor(internal val radians: Double) {
    private val degrees: Double
        get() = toDegrees(radians)

    val x: Double
        get() = cos(radians)

    val y: Double
        get() = sin(radians)

    val unitVector
        get() = Vector(x, y)

    operator fun plus(other: Rotation) =
        fromRadians(radians + other.radians)

    operator fun minus(other: Rotation) =
        fromRadians(radians - other.radians)

    operator fun minus(other: Orientation) =
        Rotation.fromRadians(radians - other.radians)

    companion object {
        val RIGHT = fromRadians(0.0)
        val UP = fromRadians(PI / 2)
        val LEFT = fromRadians(PI)
        val DOWN = fromRadians(3 * PI / 2)

        fun fromXY(x: Double, y: Double) =
            fromRadians(atan2(y, x))

        internal fun fromRadians(radians: Double) =
            Orientation(mod(radians, 2 * PI))

        fun createRandom() =
            fromRadians(Random.nextDouble(2 * PI))
    }
}