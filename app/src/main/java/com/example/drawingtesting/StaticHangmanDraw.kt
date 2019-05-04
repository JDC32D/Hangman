package com.example.drawingtesting

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*

class StaticHangmanDraw : View {

    private var iX = null
    private var iY = null
    private var eX = null
    private var eY = null
    private val size = 180
    private var rad = size / 2f
    private val paint = Paint(1)
    private val borderWidth = 5f
    private val v = findViewById<View>(R.id.title_draw_view)
    private val totalX = v.width
    private val totalY = v.height

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context): super(context, null)

    init {
        paint.color =0xfff8efe0.toInt()
        paint.strokeWidth = 50f
    }

    //paint.color = 0x22ff0000
    //0xfff8efe0

    override fun onDraw(canvas: Canvas) {
        drawHangman(canvas)
        super.onDraw(canvas)
    }

    private fun drawHangman(canvas: Canvas) {
        //Log.wtf("StaticDrawing", "($totalX,$totalY)")
        canvas.drawCircle((size/.75f), (size/2f), rad, paint)
        canvas.drawLine(240f, 180f, 240f, 500f, paint) //Torso
        canvas.drawLine(240F, 280f, 150f, 225f, paint) //RArm
        canvas.drawLine(240F, 280f, 330f, 225f, paint) //LArm
        canvas.drawLine(240F, 500f, 300f, 550f, paint) //LFoot
        canvas.drawLine(240F, 500f, 180f, 550f, paint) //RFoot
    }

}