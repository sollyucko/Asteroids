package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.GameEntity
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Circle
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Point

class Enemy : Circle(Point(RADIUS*2, RADIUS*2), RADIUS), GameEntity<Asteroids> {
    override fun tick(game: Asteroids) {
        val orientation = (game.getPlayerPosition() - anchor).orientation
        game.addEnemyBullet(Bullet(center, orientation))
    }

    var hp = START_HP

    companion object {
        const val RADIUS = 20.0
        const val START_HP = 10
    }
}