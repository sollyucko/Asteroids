package io.github.sollyucko.ads.a.asteroids.utils.geometry

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Suppress("MemberVisibilityCanBePrivate")
data class Vector(var x: Double, var y: Double) {
    operator fun plus(other: Vector) =
        Vector(x + other.x, y + other.y)

    operator fun plus(other: Point) =
        Point(x + other.x, y + other.y)

    operator fun times(other: Double) =
        Vector(x * other, y * other)

    val magnitude = x * x + y * y
    val angleRadians = atan2(y, x)

    fun rotated(rotationDegrees: Double): Vector {
        val magnitude = magnitude
        val newAngleDegrees = angleRadians + Math.toRadians(rotationDegrees)
        return Vector(
            magnitude * cos(
                newAngleDegrees
            ), magnitude * sin(newAngleDegrees)
        )
    }
}