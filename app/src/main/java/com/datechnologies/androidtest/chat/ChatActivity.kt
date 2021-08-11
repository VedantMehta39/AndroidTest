package com.datechnologies.androidtest.chat


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.datechnologies.androidtest.MainActivity
import com.datechnologies.androidtest.R
import com.datechnologies.androidtest.api.ChatLogMessageModel
import java.util.*


/**
 * Screen that displays a list of chats from a chat log.
 */
class ChatActivity : AppCompatActivity() {
    //==============================================================================================
    // Class Properties
    //==============================================================================================
    private var recyclerView: RecyclerView? = null
    private var chatAdapter: ChatAdapter? = null

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        chatAdapter = ChatAdapter()
        recyclerView!!.adapter = chatAdapter
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL,
                false)
        val tempList: MutableList<ChatLogMessageModel> = ArrayList()
        val chatLogMessageModel = ChatLogMessageModel(message = "This is test data. Please retrieve real data.")
        tempList.add(chatLogMessageModel)
        tempList.add(chatLogMessageModel)
        tempList.add(chatLogMessageModel)
        tempList.add(chatLogMessageModel)
        tempList.add(chatLogMessageModel)
        tempList.add(chatLogMessageModel)
        tempList.add(chatLogMessageModel)
        tempList.add(chatLogMessageModel)
        chatAdapter!!.setChatLogMessageModelList(tempList)

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.

        // TODO: Retrieve the chat data from http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
        // TODO: Parse this chat data from JSON into ChatLogMessageModel and display it.
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        //==============================================================================================
        // Static Class Methods
        //==============================================================================================
        fun start(context: Context) {
            val starter = Intent(context, ChatActivity::class.java)
            context.startActivity(starter)
        }
    }
}