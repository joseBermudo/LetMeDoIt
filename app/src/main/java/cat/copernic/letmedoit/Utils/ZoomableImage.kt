package cat.copernic.letmedoit.Utils

import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import kotlin.math.atan2
import kotlin.math.sqrt


//Implementación a partir de códigos vistos en https://stackoverflow.com/questions/6650398/android-imageview-zoom-in-and-zoom-out
//Se puede utilizar en algún futuro si nos da tiempo
abstract class ZoomableImage {


    companion object{
        private var lastEvent: FloatArray? = null
        private var d = 0f
        private var newRot = 0f
        private var isZoomAndRotate = false
        private var isOutSide = false
        private val NONE = 0
        private val DRAG = 1
        private val ZOOM = 2
        private var mode = NONE
        private val start = PointF()
        private val mid = PointF()
        private var oldDist = 1f
        private var xCoOrdinate = 0f
        private var yCoOrdinate = 0f

        fun addZoomableImage(imageView: ImageView){
            imageView.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    val view = (v as ImageView)
                    view.bringToFront()
                    if (event != null) {
                        viewTransformation(view, event)
                    }
                    return true
                }

            })
        }
        private fun viewTransformation(view: View, event: MotionEvent){
            when (event.action and MotionEvent.ACTION_MASK) {
                /*MotionEvent.ACTION_DOWN -> {
                    xCoOrdinate = view.x - event.rawX
                    yCoOrdinate = view.y - event.rawY
                    start.set(event.x, event.y)
                    isOutSide = false
                    mode = DRAG
                    lastEvent = null
                }*/
                MotionEvent.ACTION_POINTER_DOWN -> {
                    oldDist = spacing(event)
                    if (oldDist > 10f) {
                        midPoint(mid, event)
                        mode = ZOOM
                    }
                    lastEvent = FloatArray(4)
                    lastEvent!![0] = event.getX(0)
                    lastEvent!![1] = event.getX(1)
                    lastEvent!![2] = event.getY(0)
                    lastEvent!![3] = event.getY(1)
                    //d = rotation(event)
                }
                MotionEvent.ACTION_UP -> {
                    isZoomAndRotate = false
                    /*if (mode === DRAG) {
                        val x = event.x
                        val y = event.y
                    }*/
                    isOutSide = true
                    mode = NONE
                    lastEvent = null
                    mode = NONE
                    lastEvent = null
                }
                MotionEvent.ACTION_OUTSIDE -> {
                    isOutSide = true
                    mode = NONE
                    lastEvent = null
                    mode = NONE
                    lastEvent = null
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    mode = NONE
                    lastEvent = null
                }
                MotionEvent.ACTION_MOVE -> if (!isOutSide) {
                    /*if (mode === DRAG) {
                        isZoomAndRotate = false
                        view.animate().x(event.rawX + xCoOrdinate).y(event.rawY + yCoOrdinate)
                            .setDuration(0).start()
                    }*/
                    if (mode === ZOOM && event.pointerCount === 2) {
                        val newDist1: Float = spacing(event)
                        if (newDist1 > 10f) {
                            val scale: Float = newDist1 / oldDist * view.scaleX
                            view.scaleX = scale
                            view.scaleY = scale
                        }
                        /*if (lastEvent != null) {
                            newRot = rotation(event)
                            view.rotation = (view.rotation + (newRot - d)) as Float
                        }*/
                    }
                }
            }
        }
        /*private fun rotation(event: MotionEvent): Float {
            val delta_x = (event.getX(0) - event.getX(1)).toDouble()
            val delta_y = (event.getY(0) - event.getY(1)).toDouble()
            val radians = atan2(delta_y, delta_x)
            return Math.toDegrees(radians).toFloat()
        }*/

        private fun spacing(event: MotionEvent): Float {
            val x = event.getX(0) - event.getX(1)
            val y = event.getY(0) - event.getY(1)
            return sqrt((x * x + y * y).toDouble()).toInt().toFloat()
        }

        private fun midPoint(point: PointF, event: MotionEvent) {
            val x = event.getX(0) + event.getX(1)
            val y = event.getY(0) + event.getY(1)
            point[x / 2] = y / 2
        }
    }
}