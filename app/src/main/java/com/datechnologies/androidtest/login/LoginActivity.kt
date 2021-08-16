package com.datechnologies.androidtest.login


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.datechnologies.androidtest.MainActivity
import com.datechnologies.androidtest.R
import kotlin.time.ExperimentalTime


/**
 * A screen that displays a login prompt, allowing the user to login to the D & A Technologies Web Server.
 *
 */
class LoginActivity : AppCompatActivity() {
    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.login_title)
        setContentView(R.layout.activity_login)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val vm: LoginViewModel by viewModels()
        val emailEditText = findViewById<EditText>(R.id.email_address)
        val passwordEditText = findViewById<EditText>(R.id.password)
        emailEditText.setText(vm.email)
        passwordEditText.setText(vm.password)
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    vm.email = it.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    vm.password = it.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            vm.login().observe(this, { response ->
                LoginResponseDialog(response).show(supportFragmentManager, "LOGIN_RESPONSE_FRAG")
            })
        }
        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // TODO: Add a ripple effect when the buttons are clicked
        // TODO: Save screen state on screen rotation, inputted username and password should not disappear on screen rotation


        // TODO: Send 'email' and 'password' to http://dev.rapptrlabs.com/Tests/scripts/login.php
        // TODO: as FormUrlEncoded parameters.


        // TODO: When you receive a response from the login endpoint, display an AlertDialog.
        // TODO: The AlertDialog should display the 'code' and 'message' that was returned by the endpoint.
        // TODO: The AlertDialog should also display how long the API call took in milliseconds.
        // TODO: When a login is successful, tapping 'OK' on the AlertDialog should bring us back to the MainActivity

        // TODO: The only valid login credentials are:
        // TODO: email: info@rapptrlabs.com
        // TODO: password: Test123
        // TODO: so please use those to test the login.
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
        // Static Class Methods
        //==============================================================================================
        fun start(context: Context) {
            val starter = Intent(context, LoginActivity::class.java)
            context.startActivity(starter)
        }
    }
}
