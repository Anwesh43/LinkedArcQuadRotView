package com.anwesh.uiprojects.arcquadrotview

/**
 * Created by anweshmishra on 24/06/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.content.Context
import android.app.Activity

val nodes : Int = 5
val arcs : Int = 2
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val scGap : Float = 2.9f
val scDiv : Double = 0.51
val foreColor : Int = Color.parseColor("#283593")
val backColor : Int = Color.parseColor("#BDBDBD")
val rotDeg : Float = 360f / arcs
val finalDeg : Float = 360f
val hArcFactor : Float = 3.5f
val startDeg : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawArcRot(i : Int, size : Float, deg : Float, paint : Paint) {
    save()
    rotate(deg)
    drawArc(RectF(0f, -size / hArcFactor, size, size / hArcFactor), startDeg, startDeg, false, paint)
    restore()
}

fun Canvas.drawAQRNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.style = Paint.Style.STROKE
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.color = foreColor
    var deg : Float = 0f
    val sf : Float = 1f - 2 * (i % 2)
    save()
    translate(w / 2 + (w / 2 + size) * sc2, gap * (i + 1))
    rotate(finalDeg * sc2)
    for (j in 0..(arcs - 1)) {
        val sc : Float = sc1.divideScale(j, arcs)
        deg += rotDeg * sc
        save()
        rotate(deg)
        drawArcRot(j, size, deg, paint)
        restore()
    }
    restore()
}

class ArcQuadRotView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}