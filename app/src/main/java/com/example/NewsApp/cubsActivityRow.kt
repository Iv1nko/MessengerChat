package com.example.NewsApp.com.example.messengerchat

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.NewsApp.ChatLogActivity
import com.example.NewsApp.R
import com.example.NewsApp.User_list
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class cubsActivityRow(val chatMessage: ChatLogActivity.News): Item<GroupieViewHolder>(){
    var chatPartnerUser: User_list? = null
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.message_textView_latestMessage).text = chatMessage.displayname
        val chatPartnerId: String
//        if(chatMessage.from_id == FirebaseAuth.getInstance().uid){
//            chatPartnerId = chatMessage.to_id
//        }
//        else{
//            chatPartnerId = chatMessage.from_id
//        }
        val ref = FirebaseDatabase.getInstance().getReference("/news/1")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatPartnerUser = snapshot.getValue(User_list::class.java)
                viewHolder.itemView.findViewById<TextView>(R.id.userName_textView_latestMessages).text = chatPartnerUser?.name
                val targetImageView = viewHolder.itemView.findViewById<ImageView>(R.id.imageView_latestMessages)
                Picasso.get()
                    .load(chatPartnerUser?.image_id)
                    .fit()
                    .centerCrop()
                    .into(targetImageView)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("Error","in onCancelled")
            }
        })
    }
    override fun getLayout(): Int {
        return R.layout.latest_message_row
    }
}