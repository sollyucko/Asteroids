package io.github.sollyucko.ads.a.asteroids.utils.geometry

interface Shape {
    var anchor: Point

    operator fun contains(point: Point): Boolean

    fun collidesWith(other: Shape): Boolean?

    fun rotate(rotation: Rotation)
    
    fun translate(vector: Vector) {
        anchor += vector
    }
    fun translate(vector: Vector, bounds: Rectangular) {
        translate(vector)

        if (anchor.x < 0) {
            anchor.x += bounds.availWidth
        } else if (anchor.x > bounds.availWidth) {
            anchor.x -= bounds.availWidth
        }

        if (anchor.y < 0) {
            anchor.y += bounds.availHeight
        } else if (anchor.y > bounds.availHeight) {
            anchor.y -= bounds.availHeight
        }
    }
    

    companion object {
        fun collide(a: Shape, b: Shape): Boolean =
            a.collidesWith(b)
                ?: b.collidesWith(a)
                ?: throw RuntimeException("Unable to collide ${a.javaClass} and ${b.javaClass}")
    }
}
