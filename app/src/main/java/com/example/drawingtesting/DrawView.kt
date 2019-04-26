package com.example.drawingtesting

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.Color
import android.graphics.Paint
import android.text.TextUtils.indexOf

data class DrawObj(val origin: PointF) {
    var endPoint: PointF = origin
//    var start: PointF = origin
//    var radius: Float = 0.toFloat()
}

class DrawView: View {

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context): super(context, null)

    var currentDrawObj: DrawObj? = null
    val paint = Paint()
    var objs = arrayListOf<DrawObj>()

    init {
        //19:30
        paint.color = 0x22ff0000

    }

    override fun onDraw(canvas: Canvas?) {
        // Log when drawing occurs
        //currentDrawObj?.let {
        //This forEach gives persistence on the screen
        objs.forEach{
            var radius = Math.abs(it.endPoint.y - it.origin.x)
            paint.strokeWidth = 100.toFloat()

            if(objs.size == 1) {
                canvas?.drawCircle(it.origin.x, it.endPoint.y, radius, paint)
            }
            else {
                if(objs.indexOf(it) == 0) {
                    canvas?.drawCircle(it.origin.x, it.endPoint.y, radius, paint)
                } else {
                    canvas?.drawLine(it.origin.x, it.origin.y, it.endPoint.x, it.endPoint.y, paint)
                    //canvas?.drawCircle(objs[1].start.x, objs[1].endPoint.y, objs[1].radius, paint)
                }
            }
        }
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event == null) { return false } // passed in as nullable type, just safe

        val currentPoint = PointF(event.x, event.y) //FloatingPoint

        when(event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                currentDrawObj = DrawObj(currentPoint)
                objs.add(currentDrawObj!!)
            }
            MotionEvent.ACTION_MOVE -> {
                currentDrawObj?.let {
                    it.endPoint = currentPoint
                }
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
//                currentDrawObj?.let {
//                    objs.add(it)
//                }
//                invalidate()
                //Only nulling reference, not actual object
                currentDrawObj = null
                //performClick()
            }
        }
        return true
    }


}

    /*
    https://www.youtube.com/watch?v=LidF-TNx0es&feature=youtu.be

    Everything defined in the XML are layout attributes or attribute sets
    we need to be sure this info gets forwarded to our superview
    you have to have certain constructors for it to show up correctly

    -- 56:00 --
    Whenever you pull in Google maps, you pull in their custom map view
    Right into the XML com.google.[mapview]

    We need onDraw and onTouch
    Hold onto anything we draw on the screen
    Use onDraw to draw,
    Touch events update what we see on the screen as it appears on the screen.
    We will persist them on the screen.
     */

    /*
    https://www.youtube.com/watch?v=rZoIWDFwwW8&feature=youtu.be

    Because we created a subclass of a view we are able to use this in our XML
    You can do this with views or viewgroups
    A viewgroup in this case would be a constraint layout
    viewgroups can contain otherview groups.

    -- 4:00 --
    AcionMask is where we get actionup,down,etc.

    -- 12:00 --
    Paths
    To draw multiple lines, only create one path.
    Arcs have a third point, bezie path?
    Can set clipping rotation, it gets finiky.
    Paths wont show on the screen until you draw them and give them a color.

    -- 23:00 --
    to get the view to re draw itself, invalidate


    -- 31:00 --
    Everything in Java is pass by reference

    -- 32:00 --
    performClick() -- OS clicks for people who have disabilities

    -- 36:50 --
    Draw a signature / letters with a path


     */