package com.datechnologies.androidtest.animation

import android.animation.Animator
import android.app.ActionBar
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import com.datechnologies.androidtest.MainActivity
import com.datechnologies.androidtest.R
import kotlin.math.log
import android.view.View.DragShadowBuilder
import androidx.annotation.RequiresApi
import androidx.core.view.DragStartHelper
import androidx.core.view.MotionEventCompat


/**
 * Screen that displays the D & A Technologies logo.
 * The icon can be moved around on the screen as well as animated.
 */
class AnimationActivity : AppCompatActivity() {
    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.animation_title)
        setContentView(R.layout.activity_animation)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val logo = findViewById<ImageView>(R.id.logo)

        // Couldn't figure out how to get the image drag to work
        // This is the only thing I found through the Documentation, but it works only for API 24 +
        // and even this was for dragging and dropping to a particular location to move an object
        logo.setOnLongClickListener { view :View ->
            val item = ClipData.Item(view.tag as? CharSequence)

            val dragData = ClipData(
                view.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item)
            view.startDragAndDrop(dragData, MyDragShadowBuilder(view), null, 0)
            return@setOnLongClickListener true
        }
        val fade = findViewById<Button>(R.id.fade)
        fade.setOnClickListener {
            logo.alpha = 1f
            logo.animate()
                .alpha(0f)
                .setDuration(3000)
                .setListener(object: Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        logo.alpha = 0f
                        logo.animate()
                            .alpha(1f)
                            .setDuration(3000)
                            .setListener(null)
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                })
        }
    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                android.R.id.home -> onBackPressed()
            }
        }
        return true
    }

    companion object {
        //==============================================================================================
        // Class Properties
        //==============================================================================================
        //==============================================================================================
        // Static Class Methods
        //==============================================================================================
        fun start(context: Context) {
            val starter = Intent(context, AnimationActivity::class.java)
            context.startActivity(starter)
        }
    }
}


// Part of the drag and drop code. Do not understand how this works
private class MyDragShadowBuilder(v: View) : View.DragShadowBuilder(v) {

    private val shadow = ColorDrawable(Color.LTGRAY)

    // Defines a callback that sends the drag shadow dimensions and touch point back to the
    // system.
    override fun onProvideShadowMetrics(size: Point, touch: Point) {
        // Sets the width of the shadow to half the width of the original View
        val width: Int = view.width / 2

        // Sets the height of the shadow to half the height of the original View
        val height: Int = view.height / 2

        // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
        // Canvas that the system will provide. As a result, the drag shadow will fill the
        // Canvas.
        shadow.setBounds(0, 0, width, height)

        // Sets the size parameter's width and height values. These get back to the system
        // through the size parameter.
        size.set(width, height)

        // Sets the touch point's position to be in the middle of the drag shadow
        touch.set(width / 2, height / 2)
    }

    // Defines a callback that draws the drag shadow in a Canvas that the system constructs
    // from the dimensions passed in onProvideShadowMetrics().
    override fun onDrawShadow(canvas: Canvas) {
        // Draws the ColorDrawable in the Canvas passed in from the system.
        shadow.draw(canvas)
    }
}