package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.Asteroids

interface GameEntity {
    fun tick(game: Asteroids)
}
