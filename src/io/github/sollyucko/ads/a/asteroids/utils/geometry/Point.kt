package io.github.sollyucko.ads.a.asteroids.utils.geometry

data class Point(var x: Double, var y: Double) {
    operator fun plus(other: Vector) =
        Point(x + other.x, y + other.y)

    operator fun minus(other: Point) =
        Vector(x - other.x, y - other.y)
}