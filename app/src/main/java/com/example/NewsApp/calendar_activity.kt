package com.example.NewsApp.com.example.messengerchat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.NewsApp.ChatLogActivity
import com.example.NewsApp.DateItem
import com.example.NewsApp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class calendar_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        supportActionBar?.title = "Schedule"

        val recycler_latest_messages = findViewById<RecyclerView>(R.id.recycler_calendar)

        val adapter = GroupAdapter<GroupieViewHolder>()
        val recycler_new_mess = findViewById<RecyclerView>(R.id.recycler_calendar)
        recycler_new_mess.adapter = adapter
        adapter.add(DateItem(ChatLogActivity.Date("1", R.drawable.schedule1, "Ахмат vs Рубин")))
        adapter.add(DateItem(ChatLogActivity.Date("2", R.drawable.schedule2, "Рубин vs Спартак")))
        adapter.add(DateItem(ChatLogActivity.Date("3", R.drawable.schedule3, "Рубин vs Пари Нижний Новгород")))
        adapter.add(DateItem(ChatLogActivity.Date("4", R.drawable.schedule4, "Зенит vs Рубин")))

        recycler_latest_messages.adapter = adapter
        recycler_latest_messages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }


//    private fun listenForLatestCubs(){
//        Log.d("listenForLatestCubs","listenForLatestCubs is work")
//        //val recycler_latest_messages = findViewById<RecyclerView>(R.id.recyclerView_latestMessages)
//        val ref = FirebaseDatabase.getInstance().getReference("/date")
//
//        //
//        val adapter = GroupAdapter<GroupieViewHolder>()
//        val recycler_new_mess = findViewById<RecyclerView>(R.id.recycler_calendar)
//        recycler_new_mess.adapter = adapter
        //Log.d("listenForLatestCubs","listenForLatestCubs is one")
//        ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                p0.children.forEach {
//                    //Log.d("listenForLatestCubs","listenForLatestCubs is two")
//                    val cubs = it.getValue(ChatLogActivity.Date::class.java)
//                    if (cubs != null) {
//                        adapter.add(DateItem(cubs))
//
//
//                    }
//                    else{
//                        Log.d("listenForLatestCubs","cubs equal null")
//                    }
//                }
//                adapter.setOnItemClickListener { item, view ->
//                    Log.d("listenForLatestCubs","Click to News")
//
////                    val userItem = item as UserItem
////                    val intent = Intent(view.context, ChatLogActivity::class.java)
////                    intent.putExtra(QuizActivity.USER_KEY, userItem.user.name)
////                    intent.putExtra(QuizActivity.USER_ID, userItem.user.id)
////                    intent.putExtra(QuizActivity.USER_IMAGE, userItem.user.image_id)
////                    startActivity(intent)
//                }
//                //Log.d("listenForLatestCubs","adapter " + adapter.getItem(0).)
//                recycler_new_mess.adapter = adapter
//                val recycler = recycler_new_mess.adapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })
//    }
}