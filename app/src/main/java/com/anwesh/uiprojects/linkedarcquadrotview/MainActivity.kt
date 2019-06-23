package com.anwesh.uiprojects.linkedarcquadrotview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.arcquadrotview.ArcQuadRotView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ArcQuadRotView.create(this)
    }
}
