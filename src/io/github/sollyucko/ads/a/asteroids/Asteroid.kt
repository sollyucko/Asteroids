package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.physics.VelocityGameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.*
import io.github.sollyucko.ads.a.asteroids.utils.randomDoubleNear
import kotlin.math.min
import kotlin.random.Random

class Asteroid private constructor(shape: Array<Point>, anchor: Point, orientation: Orientation, val size: Double) :
        Polygon(shape, anchor, orientation),
        VelocityGameEntity<Asteroids> {

    override val velocity: Vector
        get() = orientation.unitVector * MOVEMENT_SPEED

    companion object {
        const val MOVEMENT_SPEED = 2.0
        private const val MAX_START_SIZE = 30.0
        private const val MIN_START_SIZE = 15.0
        const val MINIMUM_SIZE = 10.0
        private const val NUM_VERTICES = 5

        private fun generateShape(size: Double) =
            generateSequence {
                Point(
                    Random.nextDouble(size),
                    Random.nextDouble(size)
                )
            }.take(NUM_VERTICES).toList().toTypedArray()

        private fun createRandom(anchor: Point, size: Double) =
            Asteroid(
                generateShape(size),
                anchor,
                Orientation.createRandom(),
                size
            )

        fun createRandom(bounds: Rectangular) =
            createRandom(
                Point(
                    Random.nextDouble(bounds.availWidth.toDouble()),
                    Random.nextDouble(bounds.availHeight.toDouble())
                ),
                Random.nextDouble(MIN_START_SIZE, MAX_START_SIZE)
            )

        fun createRandomNear(point: Point, bounds: Rectangular, size: Double) =
            createRandom(
                Point(
                    randomDoubleNear(point.x, min(point.x, bounds.availWidth - point.x)),
                    randomDoubleNear(point.y, min(point.y, bounds.availHeight - point.y))
                ),
                size
            )
    }
}