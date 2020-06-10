package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rectangular

interface GameEntity<in G : Rectangular> {
    fun tick(game: G)
}
