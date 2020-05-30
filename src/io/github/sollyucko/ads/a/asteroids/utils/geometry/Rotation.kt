package io.github.sollyucko.ads.a.asteroids.utils.geometry

import java.lang.Math.toDegrees
import java.lang.Math.toRadians

/**
 * Values increase counter-clockwise.
 * */
@Suppress("DataClassPrivateConstructor", "unused")
data class Rotation private constructor(val radians: Double) {
    val degrees: Double
        get() = toDegrees(radians)

    operator fun unaryMinus() =
        fromRadians(-radians)

    operator fun plus(other: Orientation) =
        Orientation.fromRadians(radians + other.radians)

    operator fun plus(other: Rotation) =
        fromRadians(radians + other.radians)

    operator fun minus(other: Rotation) =
        fromRadians(radians - other.radians)

    operator fun times(other: Double) =
        fromRadians(radians * other)

    operator fun div(other: Double) =
        fromRadians(radians / other)

    companion object {
        fun fromRadians(radians: Double) = Rotation(radians)
        fun fromDegrees(degrees: Double) = Rotation(toRadians(degrees))
    }
}