package io.github.sollyucko.ads.a.asteroids.utils

import kotlin.math.pow
import kotlin.random.Random

fun <T> Iterable<T>.zipWithNextWrapping(): List<Pair<T, T>> = zipWithNext() + Pair(last(), first())

fun sleep(millis: Long) {
    try {
        Thread.sleep(millis)
    } catch (exc: InterruptedException) {
    }
}

fun mod(a: Double, b: Double) = ((a % b) + b) % b

fun <T> MutableList<T>.assign(from: Iterable<T>) {
    clear()
    addAll(from)
}

fun randomDoubleNear(x: Double, maxDist: Double) =
    x + Random.nextDouble((maxDist).pow(1 / 10)).pow(10)