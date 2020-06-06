package io.github.sollyucko.ads.a.asteroids.utils.geometry

import io.github.sollyucko.ads.a.asteroids.utils.randomDoubleNear
import kotlin.math.min
import kotlin.random.Random

data class Point(var x: Double, var y: Double) {
    operator fun plus(other: Vector) =
        Point(x + other.x, y + other.y)

    operator fun minus(other: Point) =
        Vector(x - other.x, y - other.y)

    companion object {
        fun createRandom(bounds: Rectangular) =
            Point(
                Random.nextDouble(bounds.availWidth.toDouble()),
                Random.nextDouble(bounds.availHeight.toDouble())
            )

        fun createRandomNear(point: Point, bounds: Rectangular) =
            Point(
                randomDoubleNear(point.x, min(point.x, bounds.availWidth - point.x)),
                randomDoubleNear(point.y, min(point.y, bounds.availHeight - point.y))
            )

        fun createRandomOnBorder(bounds: Rectangular) =
            when (Random.nextInt(4)) {
                0 -> Point(
                    0.0,
                    Random.nextDouble(bounds.availWidth.toDouble())
                )
                1 -> Point(
                    Random.nextDouble(bounds.availHeight.toDouble()),
                    0.0
                )
                2 -> Point(
                    bounds.availHeight.toDouble(),
                    Random.nextDouble(bounds.availWidth.toDouble())
                )
                3 -> Point(
                    Random.nextDouble(bounds.availWidth.toDouble()),
                    bounds.availWidth.toDouble()
                )
                else -> throw RuntimeException("Random is broken")
            }
    }
}