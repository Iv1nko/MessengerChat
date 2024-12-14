package com.example.NewsApp.com.example.messengerchat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.NewsApp.ChatLogActivity
import com.example.NewsApp.R
import com.example.NewsApp.TeamItem
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class team_list_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_list_activity)

        listenForLatestCubs()
    }


    private fun listenForLatestCubs(){
        Log.d("listenForLatestCubs","listenForLatestCubs is work")
        //val recycler_latest_messages = findViewById<RecyclerView>(R.id.recyclerView_latestMessages)
        val ref = FirebaseDatabase.getInstance().getReference("/commad")
        supportActionBar?.title = "Main Composition"

        //
        val adapter = GroupAdapter<GroupieViewHolder>()
        val recycler_new_mess = findViewById<RecyclerView>(R.id.recycler_team)
        recycler_new_mess.adapter = adapter
        adapter.add(TeamItem(ChatLogActivity.Team("1", "Мирлинд Даку", R.drawable.player1, "Нападающий", "10")))
        adapter.add(TeamItem(ChatLogActivity.Team("2", "Джоэль Фамейе", R.drawable.player2, "Нападающий", "20")))
        adapter.add(TeamItem(ChatLogActivity.Team("3", "Угочукву Иву", R.drawable.player3, "Нападающий", "6")))
        adapter.add(TeamItem(ChatLogActivity.Team("4", "Никола Чумич", R.drawable.player4, "Защитник", "24")))
        adapter.add(TeamItem(ChatLogActivity.Team("5", "Руслан Безруков", R.drawable.player5, "Защитник", "23")))


        //Log.d("listenForLatestCubs","listenForLatestCubs is one")

//        ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                p0.children.forEach {
//                    //Log.d("listenForLatestCubs","listenForLatestCubs is two")
//                    val cubs = it.getValue(ChatLogActivity.Team::class.java)
//                    if (cubs != null) {
//                        adapter.add(TeamItem(cubs))
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