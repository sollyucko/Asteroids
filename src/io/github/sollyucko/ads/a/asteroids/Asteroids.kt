package io.github.sollyucko.ads.a.asteroids

import io.github.sollyucko.ads.a.asteroids.utils.GameCanvas
import io.github.sollyucko.ads.a.asteroids.utils.assign
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Orientation
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Point
import io.github.sollyucko.ads.a.asteroids.utils.geometry.Shape.Companion.collide
import io.github.sollyucko.ads.a.asteroids.utils.sleep
import java.awt.Color
import java.awt.Graphics
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.system.exitProcess

/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
*/
class Asteroids : GameCanvas("Asteroids!", 800, 600) {
    private val ship = Ship(
        Point(
            (availWidth / 2).toDouble(),
            (availHeight / 2).toDouble()
        ),
        Orientation.RIGHT
    )
    private val bullets = mutableListOf<Bullet>()
    private var enemy: Enemy? = Enemy()
    private val enemyBullets = mutableListOf<Bullet>()
    private val asteroids = generateSequence { Asteroid.createRandom(this) }.take(NUM_ASTEROIDS).toMutableList()
    private var score: Int = 0

    init {
        isFocusable = true
        requestFocus()
        addKeyListener(ship)
    }

    override fun paint(brush: Graphics) {
        brush.color = Color.black
        brush.fillRect(0, 0, availWidth, availHeight)

        brush.color = Color.white
        brush.drawString(score.toString(), 0, brush.font.size)

        ship.tick(this)
        ship.paint(brush)

        enemy?.tick(this)
        enemy?.paint(brush)

        bullets.assign(bullets.filter { bullet ->
            enemy?.let {
                if (collide(bullet, it)) {
                    it.hp -= 1
                    if (it.hp <= 0) {
                        enemy = null
                    }
                    return@filter false
                }
            }

            for (asteroid in asteroids) {
                if (collide(bullet, asteroid)) {
                    asteroids.remove(asteroid)
                    if (asteroid.size > Asteroid.MINIMUM_SIZE) {
                        score += (asteroid.size * asteroid.size).roundToInt()
                        asteroids.add(Asteroid.createRandomNear(asteroid.anchor, this, asteroid.size / 2.0))
                        asteroids.add(Asteroid.createRandomNear(asteroid.anchor, this, asteroid.size / 2.0))
                    } else {
                        if (Random.nextInt(2) == 0) {
                            asteroids.add(Asteroid.createRandomOnBorder(this))
                        }
                    }
                    return@filter false
                }
            }

            bullet.tick(this)
            bullet.paint(brush)
            true
        })

        for (bullet in enemyBullets) {
            if (collide(ship, bullet)) {
                endGame(win = false)
            }

            bullet.tick(this)
            bullet.paint(brush)
        }

        for (asteroid in asteroids) {
            if (collide(ship, asteroid)) {
                endGame(win = false)
            }

            asteroid.tick(this)
            asteroid.paint(brush)
        }

        if (enemy == null && asteroids.isEmpty()) {
            endGame(win = true)
        }
    }

    private fun endGame(win: Boolean) {
        if (win) {
            println("You won!")
        } else {
            println("You lost!")
        }
        println("Final score: $score")
        exitProcess(0) // TODO: improve this
    }

    fun addBullet(bullet: Bullet) {
        bullets.add(bullet)
    }

    fun addEnemyBullet(bullet: Bullet) {
        enemyBullets.add(bullet)
    }

    fun getPlayerPosition(): Point =
        ship.anchor

    companion object {
        const val NUM_ASTEROIDS = 20
    }
}

fun main() {
    println("You control the spaceship in the middle of the screen.")
    println("Press space to shoot, left/right to turn, and up to move forwards.")
    println("You win if you get rid of all the asteroids.")
    println("You lose if an asteroid hits you.")
    sleep(1000)
    val a = Asteroids()
    a.repaint()
}