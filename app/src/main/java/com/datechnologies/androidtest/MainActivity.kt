package com.datechnologies.androidtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.datechnologies.androidtest.animation.AnimationActivity
import com.datechnologies.androidtest.chat.ChatActivity
import com.datechnologies.androidtest.login.LoginActivity

/**
 * The main screen that lets you navigate to all other screens in the app.
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.activity_main_title)
        setContentView(R.layout.activity_main)
        /**
         * =========================================================================================
         * INSTRUCTIONS
         * =========================================================================================
         *
         * 1. UI must work on Android phones of multiple sizes. Do not worry about Android Tablets.
         *
         * 2. Use this starter project as a base and build upon it. It is ok to remove some of the
         * provided code if necessary.
         *
         * 3. Read the additional 'TODO' comments throughout the codebase, they will guide you.
         *
         * 3. Please take care of the bug(s) we left for you in the project as well.
         *
         * Thank you and Good luck. -  D & A Technologies
         * =========================================================================================
         */
    }

    //==============================================================================================
    // Button Click Methods
    //==============================================================================================
    fun onChatClicked(v: View?) {
        ChatActivity.start(this)
    }

    fun onLoginClicked(v: View?) {
        LoginActivity.start(this)
    }

    fun onAnimationClicked(v: View?) {
        AnimationActivity.start(this)
    }

    companion object {
        //==============================================================================================
        // Static Class Methods
        //==============================================================================================
        fun start(context: Context) {
            val starter = Intent(context,  MainActivity::class.java)
            context.startActivity(starter)
        }
    }
}