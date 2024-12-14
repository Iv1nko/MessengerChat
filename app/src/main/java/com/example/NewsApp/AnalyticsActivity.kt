package com.example.NewsApp.com.example.messengerchat

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.NewsApp.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class AnalyticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        supportActionBar?.title = "About club"





//        val adapter = GroupAdapter<GroupieViewHolder>()
//        val adapterCounts = GroupAdapter<GroupieViewHolder>()
//        val recycler_all_users = findViewById<RecyclerView>(R.id.recycler_all_users)
//        val ref = FirebaseDatabase.getInstance().getReference("/user")
//        val firebaseSearchQuery = ref.orderByChild("name")
//        firebaseSearchQuery.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                p0.children.forEach {
//                    val user = it.getValue(User_list::class.java)
//                    val id = it.child("id").value
//                    val allMessages = FirebaseDatabase.getInstance().getReference("/messages/$id")
//                    Log.d("AnalyticsActivity","id: " + id)
//                    Log.d("AnalyticsActivity","Path: " + allMessages)
//                    if (user != null) {
//                        allMessages.addValueEventListener(object : ValueEventListener{
//                            override fun onDataChange(snapshot: DataSnapshot) {
//                                val count = snapshot.childrenCount
//                                Log.d("AnalyticsActivity", "Count: " + count)
//                                //adapterCounts.add(UserCount(count))
//                                //recycler_all_counts.adapter = adapterCounts
//                                adapter.add(UserItem(user, count.toString()))
//                            }
//                            override fun onCancelled(error: DatabaseError) {
//                                TODO("Not yet implemented")
//                            }
//                        })
//
//                        //adapter.add(UserItem(user, counts))
//                    }
//                }
//
//                recycler_all_users.adapter = adapter
//                val recycler = recycler_all_users.adapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })

    }
//    class UserItem(val user: User_list, val count : String): Item<GroupieViewHolder>(){
//        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
//            //имя пользователя
//            viewHolder.itemView.findViewById<TextView>(R.id.user_name_analytics).text = user.name
//            viewHolder.itemView.findViewById<TextView>(R.id.count_of_messages).text = count.toString()
//
//            val image = viewHolder.itemView.findViewById<ImageView>(R.id.image_user_analytics)
//            //Картинка пользователя
//            Picasso.get()
//                    .load(user.image_id)
//                    .fit()
//                    .centerCrop()
//                    .into(image)
//
//        }
//
//        override fun getLayout(): Int {
//            return R.layout.list_item_analytics
//        }
//    }
}