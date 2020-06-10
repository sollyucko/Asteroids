package io.github.sollyucko.ads.a.asteroids.utils.geometry

import io.github.sollyucko.ads.a.asteroids.utils.Paintable
import java.awt.Graphics

open class Circle(var center: Point, var radius: Double) : Shape, Paintable {
    override var anchor: Point
        get() = center
        set(value) {
            center = value
        }

    override fun contains(point: Point) =
        (center - point).magnitude <= radius

    override fun collidesWith(other: Shape): Boolean? =
        when (other) {
            is Circle -> (center - other.center).magnitude <= radius + other.radius
            else -> null
        }

    override fun rotate(rotation: Rotation) {
        // Does nothing
    }

    override fun paint(brush: Graphics) {
        brush.fillOval(
            (center.x - radius).toInt(),
            (center.y - radius).toInt(),
            (2 * radius).toInt(),
            (2 * radius).toInt()
        )
    }
}