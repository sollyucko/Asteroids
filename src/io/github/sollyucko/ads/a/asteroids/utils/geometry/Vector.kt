package io.github.sollyucko.ads.a.asteroids.utils.geometry

@Suppress("MemberVisibilityCanBePrivate")
data class Vector(var x: Double, var y: Double) {
    operator fun plus(other: Vector) =
        Vector(x + other.x, y + other.y)

    operator fun plus(other: Point) =
        Point(x + other.x, y + other.y)

    operator fun times(other: Double) =
        Vector(x * other, y * other)

    val magnitude = x * x + y * y
    val orientation = Orientation.fromXY(x, y)

    @Suppress("unused")
    companion object {
        val RIGHT = Vector(1.0, 0.0)
        val UP = Vector(0.0, 1.0)
        val LEFT = Vector(-1.0, 0.0)
        val DOWN = Vector(0.0, -1.0)
        val ZERO = Vector(0.0, 0.0)
    }
}