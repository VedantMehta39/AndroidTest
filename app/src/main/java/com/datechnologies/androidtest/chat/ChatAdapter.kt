package com.datechnologies.androidtest.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.datechnologies.androidtest.R
import com.datechnologies.androidtest.api.chatmessage.ChatLogMessageModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


/**
 * A recycler view adapter used to display chat log messages in [ChatActivity].
 *
 */
class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    //==============================================================================================
    // Class Properties
    //==============================================================================================
    private var chatLogMessageModelList: List<ChatLogMessageModel>

    //==============================================================================================
    // Class Instance Methods
    //==============================================================================================
    fun setChatLogMessageModelList(chatLogMessageModelList: List<ChatLogMessageModel>) {
        this.chatLogMessageModelList = chatLogMessageModelList
        notifyDataSetChanged()
    }

    //==============================================================================================
    // RecyclerView.Adapter Methods
    //==============================================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.chat_message, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ChatViewHolder, position: Int) {
        val chatLogMessageModel: ChatLogMessageModel = chatLogMessageModelList[position]
        viewHolder.bindData(chatLogMessageModel)
    }

    override fun getItemCount(): Int {
        return chatLogMessageModelList.size
    }

    //==============================================================================================
    // ChatViewHolder Class
    //==============================================================================================
    class ChatViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val profileImageView: CircleImageView = view.findViewById(R.id.profile_photo)
        private val nameTextView: TextView = view.findViewById(R.id.name)
        private val messageTextView: TextView = view.findViewById(R.id.message)
        private val PICASSO_SERVICE by lazy {
            Picasso.get()
        }

        fun bindData(data: ChatLogMessageModel){
            PICASSO_SERVICE.load(data.avatarUrl).into(profileImageView)
            nameTextView.text = data.username
            messageTextView.text = data.message
        }

    }

    //==============================================================================================
    // Constructor
    //==============================================================================================
    init {
        chatLogMessageModelList = ArrayList<ChatLogMessageModel>()
    }
}