package io.github.sollyucko.ads.a.asteroids.utils

import io.github.sollyucko.ads.a.asteroids.utils.geometry.Rectangular
import java.awt.Canvas
import java.awt.Frame
import java.awt.Graphics
import java.awt.Image
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import kotlin.math.max
import kotlin.system.exitProcess

/*
A painted canvas in its own window, updated every tenth of a second.
*/
abstract class GameCanvas(
    name: String?,
    canvasWidth: Int,
    canvasHeight: Int
) : Canvas(), Rectangular {
    private var buffer: Image

    /** 'paint' will be called every tenth of a second that the game is on. */
    abstract override fun paint(brush: Graphics)

    /**
    'update' paints to a buffer then to the screen, then waits a tenth of
    a second before repeating itself, assuming the game is on. This is done
    to avoid a choppy painting experience if repainted in pieces.
     */
    override fun update(brush: Graphics) {
        val startTimeNano = System.nanoTime()
        paint(buffer.graphics)
        brush.drawImage(buffer, 0, 0, this)
        val endTimeNano = System.nanoTime()
        sleep(max(10 - (endTimeNano - startTimeNano) / 1000000, 0))
        repaint()
    }

    override val availWidth: Int
        get() = width

    override val availHeight: Int
        get() = height

    init {
        // Frame can be read as 'window' here.
        val frame = Frame(name)
        @Suppress("LeakingThis")
        frame.add(this)
        frame.setSize(canvasWidth, canvasHeight)
        frame.isVisible = true
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                exitProcess(0)
            }
        })
        @Suppress("LeakingThis")
        buffer = createImage(canvasWidth, canvasHeight)
        frame.addComponentListener(object : ComponentListener {
            override fun componentMoved(e: ComponentEvent?) {}

            override fun componentResized(e: ComponentEvent?) {
                buffer = createImage(frame.width, frame.height)
            }

            override fun componentHidden(e: ComponentEvent?) {}

            override fun componentShown(e: ComponentEvent?) {}
        })
    }
}