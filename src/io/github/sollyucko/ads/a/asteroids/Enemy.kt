package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.GameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Circle
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Point

class Enemy : Circle(Point(RADIUS, RADIUS), RADIUS), GameEntity<Asteroids> {
    override fun tick(game: Asteroids) {
        // TODO: fire bullets at player
    }

    companion object {
        const val RADIUS = 20.0
    }
}