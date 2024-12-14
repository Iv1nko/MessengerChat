package com.example.NewsApp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

import com.example.NewsApp.ChatLogActivity.Team

class cubs_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cubs_activity)


        listenForLatestCubs()
    }

    //val recyclerCubs = findViewById<RecyclerView>(R.id.RecyclerCubs)


    private fun listenForLatestCubs(){

//        Log.d("listenForLatestCubs","listenForLatestCubs is work")
        //val recycler_latest_messages = findViewById<RecyclerView>(R.id.recyclerView_latestMessages)
//        val ref = FirebaseDatabase.getInstance().getReference("/cubs")

        //
        supportActionBar?.title = "Trophies"
        val adapter = GroupAdapter<GroupieViewHolder>()
        val recycler_new_mess = findViewById<RecyclerView>(R.id.recyclerView_cubs)
        recycler_new_mess.adapter = adapter
        adapter.add(CubItem(ChatLogActivity.Cubs("1", "Суперкубок России 2010, 2012", R.drawable.trophy1, "")))
        adapter.add(CubItem(ChatLogActivity.Cubs("2", "Чемпион России 2008, 2009", R.drawable.trophy2, "")))
        adapter.add(CubItem(ChatLogActivity.Cubs("3", "Кубок России 2011, 2012", R.drawable.trophy3, "")))
        adapter.add(CubItem(ChatLogActivity.Cubs("4", "Первая лига 2002, 2023", R.drawable.trophy4, "")))
        //Log.d("listenForLatestCubs","listenForLatestCubs is one")
//        ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                p0.children.forEach {
//                    //Log.d("listenForLatestCubs","listenForLatestCubs is two")
//                    val cubs = it.getValue(ChatLogActivity.Cubs::class.java)
//                    if (cubs != null) {
//                        adapter.add(CubItem(cubs))
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
//        ref.addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                if(snapshot.exists()){
//                    for(newsSnapshot in snapshot.children){
//                        val newsModel = newsSnapshot.getValue(News::class.java)
//                        //newsModel!!.key = newsSnapshot.key
//                        Log.d("previousChildName", "newsModel " + newsModel)
//                    }
//                }
//            }
//
//            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//
//            }
//
//            override fun onChildRemoved(snapshot: DataSnapshot) {
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })

    }
}