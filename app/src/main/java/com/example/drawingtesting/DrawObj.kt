package com.example.drawingtesting

import android.graphics.PointF

data class DrawObj(val origin: PointF) {
    var endPoint: PointF = origin
}