package io.github.sollyucko.ads.a.asteroids.utils.geometry

import io.github.sollyucko.ads.a.asteroids.utils.Paintable
import io.github.sollyucko.ads.a.asteroids.utils.mod
import io.github.sollyucko.ads.a.asteroids.utils.zipWithNextWrapping
import java.awt.Graphics
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

/**
CLASS: Polygon
DESCRIPTION: A polygon is a sequence of points in space defined by a set of
such points, an offset, and a rotation. The offset is the
distance between the origin and the center of the shape.
The rotation is measured in degrees, 0-360.
USAGE: You are intended to instantiate this class with a set of points that
forever defines its shape, and then modify it by repositioning and
rotating that shape. In defining the shape, the relative positions
of the points you provide are used, in other words: {(0,1),(1,1),(1,0)}
is the same shape as {(9,10),(10,10),(10,9)}.
NOTE: You don't need to worry about the "magic math" details.
 */
open class Polygon(
    private val shape: Array<Point>,
    anchor: Point,
    directionDegrees: Double // Zero degrees is left.
) : Shape, Paintable {
    init {
        // First, we find the shape's top-most left-most boundary, its origin.
        val origin = shape[0].copy()
        for ((x, y) in shape) {
            if (x < origin.x) origin.x = x
            if (y < origin.y) origin.y = y
        }

        // Then, we orient all of its points relative to the real origin.
        for (p in shape) {
            p.x -= origin.x
            p.y -= origin.y
        }
    }

    override var anchor = anchor
        set(value) {
            field = value
            pointCache = null
        }

    var directionDegrees = directionDegrees
        set(value) {
            field = value
            pointCache = null
        }

    private var pointCache: Array<Point>? = null

    /** "getPoints" applies the rotation and offset to the shape of the polygon. */
    private val points: Array<Point>
        get() {
            pointCache?.let { return it }

            val center = findCenter()
            return shape.map { (x, y) ->
                Point(
                    (x - center.x) * cos(Math.toRadians(directionDegrees))
                            - (y - center.y) * sin(Math.toRadians(directionDegrees))
                            + center.x / 2
                            + anchor.x,
                    (x - center.x) * sin(Math.toRadians(directionDegrees))
                            + (y - center.y) * cos(Math.toRadians(directionDegrees))
                            + center.y / 2
                            + anchor.y
                )
            }.toTypedArray()
        }

    /** "contains" implements some magical math (i.e. the ray-casting algorithm). */
    override operator fun contains(point: Point): Boolean {
        val crossingNumber = points.asIterable()
            .zipWithNextWrapping()
            .count { (a, b) ->
                (a.x < point.x && point.x <= b.x || b.x < point.x && point.x <= a.x)
                        && point.y > a.y + (b.y - a.y) / (b.x - a.x) * (point.x - a.x)
            }
        return crossingNumber % 2 == 1
    }

    override fun collidesWith(other: Shape): Boolean? =
        points.any { it in other }

    override fun rotate(degrees: Double) {
        directionDegrees = mod(directionDegrees + degrees, 360.0)
    }

    override fun paint(brush: Graphics) {
        val (xPoints, yPoints) = points.map { Pair(it.x, it.y) }.unzip()
        brush.drawPolygon(
            xPoints.map { it.toInt() }.toIntArray(),
            yPoints.map { it.toInt() }.toIntArray(),
            xPoints.size // xPoints and yPoints should have the same size
        )
    }

    /*
    The following methods are private access restricted because, as this access
    level always implies, they are intended for use only as helpers of the
    methods in this class that are not private. They can't be used anywhere else.
    */

    /** "findArea" implements some more magic math. */
    private fun findArea(): Double {
        return shape
            .asIterable()
            .zipWithNextWrapping()
            .map { (a, b) -> a.x * b.y - b.x * a.y }
            .sum()
            .div(2)
            .absoluteValue
    }

    /** "findCenter" implements another bit of math. */
    private fun findCenter(): Point {
        val sum = shape
            .asIterable()
            .zipWithNextWrapping()
            .map { (a, b) ->
                Vector(
                    (a.x + b.x) * (a.x * b.y - b.x * a.y),
                    (a.y + b.y) * (a.x * b.y - b.x * a.y)
                )
            }
            .reduce { acc, x -> acc + x }

        val area = findArea()
        return Point(
            abs(sum.x / (6 * area)),
            abs(sum.y / (6 * area))
        )
    }
}
