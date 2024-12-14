package com.example.NewsApp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.NewsApp.ChatLogActivity.News
import com.example.NewsApp.com.example.NewsApp.store_activity
import com.example.NewsApp.com.example.messengerchat.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_latest_message.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.menu_user_email
import kotlinx.android.synthetic.main.nav_header.view.menu_userimage
import kotlinx.android.synthetic.main.nav_header.view.menu_username
import java.text.SimpleDateFormat
import java.util.*


class LatestMessageActivity : AppCompatActivity() {
    companion object{
        val NEWS_NAME = "NEWS_NAME"
        val NEWS_ID = "NEWS_ID"
        val NEWS_IMAGE = "NEWS_IMAGE"
        val NEWS_TEXT = "NEWS_TEXT"
        val NEWS_DATE = "NEWS_TEXT"
        val NEWS_AUTHOR = "NEWS_TEXT"
    }

    var statusWin = "General"

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_message)


        val arguments = intent.extras
        val sharedPref: SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        arguments?.getString("image")?.let {
            editor.putString("image", it).commit()
            navView.getHeaderView(0).menu_userimage.setImageURI(Uri.parse(it))
            Log.d("aaaa", it)
        }
        arguments?.getString("email")?.let {
            editor.putString("email", it).commit()
            navView.getHeaderView(0).menu_user_email.text = it
        }
        arguments?.getString("username")?.let {
            editor.putString("username", it).commit()
            navView.getHeaderView(0).menu_username.text = it
        }
        if (arguments?.getString("image") == null) {
            navView.getHeaderView(0).menu_userimage.setImageURI(Uri.parse(sharedPref.getString("image", "")))
        }
        if (arguments?.getString("email") == null) {
            navView.getHeaderView(0).menu_user_email.text = sharedPref.getString("email", "user email")
        }
        if (arguments?.getString("username") == null) {
            navView.getHeaderView(0).menu_username.text = sharedPref.getString("username", "username")
        }
        /*switchCompat?.findViewById<SwitchMaterial>(R.id.switch_layout)
        switchCompat?.setOnCheckedChangeListener { buttonView, isChecked ->
            if(switchCompat?.isChecked == true){
                Log.d("LatestMessageActivity", "Switch")
            }
        }*/

        //
       //verifyUserIsLogin()



        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
//                R.id.menu_new_message -> {
//                    Log.d("Check_butt","settings")
////                    Toast.makeText(this,
////                            "New Message", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(applicationContext, QuizActivity::class.java))
//                    Animatoo.animateFade(this)
//                }
                R.id.menu_sign_out -> {
                    Toast.makeText(this,
                            "Sign Out", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()

                    val i = Intent(this@LatestMessageActivity, LoginActivity::class.java)
                    val db = FirebaseDatabase.getInstance()
                    val connectionReference: DatabaseReference = db.getReference().child("connections")
                    val lastConnected: DatabaseReference = db.getReference().child("lastConnected").child(ref.toString())
                    val infoConnected: DatabaseReference = db.getReference("info/connected")
                    infoConnected.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val con = connectionReference.child(ref.toString())
                            con.setValue(false)
                            lastConnected.setValue(date.toString())
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)

                    Animatoo.animateFade(this)
                }
                R.id.menu_settings -> {
//                    Toast.makeText(this,
//                            "Settings", Toast.LENGTH_SHORT).show()
                    val i = Intent(applicationContext, Settings_activity::class.java)
                    i.putExtra("username", navView.getHeaderView(0).menu_username.text)
                    i.putExtra("email", navView.getHeaderView(0).menu_user_email.text)
                    arguments?.getString("image")?.let { i.putExtra("image", it) }
                    if (arguments == null) { sharedPref.getString("image", "") }
                    startActivity(i)
                    Animatoo.animateFade(this)
                }
                R.id.menu_analytics -> {
//                    Toast.makeText(this,
//                            "Analytics", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(applicationContext, AnalyticsActivity::class.java))
//                    Animatoo.animateFade(this)
//                    Toast.makeText(this,
//                        "Analytics", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, calendar_activity::class.java))
                    Animatoo.animateFade(this)
                }
                R.id.cubs_layout -> {
//                    Toast.makeText(this,
//                        "cubs_activity", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, cubs_activity::class.java))
                    Animatoo.animateFade(this)
                }
                R.id.matchNum ->{
//                    Toast.makeText(this,
//                        "team_activity", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, team_list_activity::class.java))
                    Animatoo.animateFade(this)
                }
                R.id.aboutClub ->{
//                    Toast.makeText(this,
//                        "calendar_club_activity", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, AnalyticsActivity::class.java))
                    Animatoo.animateFade(this)
                }
                R.id.store->{
//                    Toast.makeText(this,
//                        "store_club_activity", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, store_activity::class.java))
                    Animatoo.animateFade(this)
                }
                R.id.menu_quiz->{
                    startActivity(Intent(applicationContext, QuizActivity::class.java))
                    Animatoo.animateFade(this)
                }
            }
            true
        }


        val recycler_latest_messages = findViewById<RecyclerView>(R.id.recyclerView_latestMessages)

        recycler_latest_messages.adapter = adapter
        recycler_latest_messages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        //set item click listener on your adapter
        adapter.setOnItemClickListener { item, view ->
            val intent = Intent(this, ChatLogActivity::class.java)
            val row = item as LatestMessageRow

//            intent.putExtra(QuizActivity.USER_IMAGE, row.chatPartnerUser?.image_id)
//            intent.putExtra(QuizActivity.USER_KEY, row.chatPartnerUser?.displayName)
//            intent.putExtra(QuizActivity.USER_ID, row.chatPartnerUser?.text)
//            intent.putExtra(QuizActivity.STATUS_WIN,statusWin)
            //Переход к другому окну
            //startActivity(intent)
            Animatoo.animateFade(this)
        }
        //setupDummyRows()
        //listenForLatestMessages()
        listenForLatestNews()
    }

    //
    //val btnStatus = findViewById<TextView>(R.id.statusWindow)
    //val coloris = btnStatus.background



    var ref = FirebaseAuth.getInstance().uid
    var sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    var date = sdf.format(Date())

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /*val toggleservice: MenuItem? = menu?.findItem(R.id.swith_sss)
        val actionView :SwitchMaterial = toggleservice?.actionView as SwitchMaterial
        actionView.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("LatestMessageActivity","Change")
        }*/
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
//        menu_fill()
//        menu_user_email.text = userEmail
//        menu_username.text = userName
//        TODO("SWWS")
//        menu_userimage.setima

        //проверка на онлайн
//        val db = FirebaseDatabase.getInstance()
//        val connectionReference: DatabaseReference = db.getReference().child("connections")
//        val lastConnected: DatabaseReference = db.getReference().child("lastConnected").child(ref.toString())
//        val infoConnected: DatabaseReference = db.getReference("info/connected")
//
//        infoConnected.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val con = connectionReference.child(ref.toString())
//                con.setValue(date.toString())
//                con.onDisconnect().setValue(false)
//                lastConnected.onDisconnect().setValue(date.toString())
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
    }

    override fun onStop() {
        super.onStop()
    }

    private fun verifyUserIsLogin() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            Log.d("Registrate", "Sign Out Success")
            val i = Intent(this@LatestMessageActivity, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }
    }

    //val latestMessagesMap = HashMap<String, ChatLogActivity.ChatMessage>()
    val latestMessagesMap = HashMap<String, ChatLogActivity.ChatMessage>()

    private fun refreshRecyclerViewMessages(){
        adapter.clear()
        latestMessagesMap.values.forEach {
            adapter.add(LatestMessageRow(it))
        }
    }
    // Показ новостей
    private fun listenForLatestNews(){
        //val recycler_latest_messages = findViewById<RecyclerView>(R.id.recyclerView_latestMessages)
//        val ref = FirebaseDatabase.getInstance().getReference("/news")

        //
        val adapter = GroupAdapter<GroupieViewHolder>()
        val recycler_new_cubs = findViewById<RecyclerView>(R.id.recyclerView_latestMessages)
        recycler_new_cubs.adapter = adapter
        adapter.add(NewsItem(News(getString(R.string.news1), getString(R.string.head_of_news1), R.drawable.news1)))
        adapter.add(NewsItem(News("", getString(R.string.head_of_news2), R.drawable.news2)))
        adapter.add(NewsItem(News(getString(R.string.news3), getString(R.string.head_of_news3), R.drawable.news3)))
        adapter.add(NewsItem(News("", getString(R.string.head_of_news4), R.drawable.news4)))

//        ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                p0.children.forEach {
//                    val user = it.getValue(News::class.java)
//                    if (user != null) {
//                        adapter.add(NewsItem(user))
//                    }
//                }
//                adapter.setOnItemClickListener { item, view ->
//                    Log.d("adaptersetOnItemClick","Click to News")
//
//                    val userItem = item as NewsItem
//                    val intent = Intent(view.context, main_news_activity::class.java)
//                    intent.putExtra(main_news_activity.NEWS_NAME, userItem.user.name)
//                    intent.putExtra(main_news_activity.NEWS_ID, userItem.user.id)
//                    intent.putExtra(main_news_activity.NEWS_IMAGE, userItem.user.image_id)
//                    intent.putExtra(main_news_activity.NEWS_TEXT, userItem.user.description)
//                    intent.putExtra(main_news_activity.NEWS_DATE, userItem.user.newsdate)
//                    intent.putExtra(main_news_activity.NEWS_AUTHOR, userItem.user.author)
//                    startActivity(intent)
//                }
//                recycler_new_cubs.adapter = adapter
//                val recycler = recycler_new_cubs.adapter
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

//    private fun listenForLatestMessages(){
//
//        val recycler_latest_messages = findViewById<RecyclerView>(R.id.recyclerView_latestMessages)
//        val fromId = FirebaseAuth.getInstance().uid
//        var path = "news"
//
////        if(statusWin.equals("General")){
////            recycler_latest_messages.setBackgroundColor(Color.parseColor("#B4FF9F"))
////            path = "latest-messages"
////        }
////        else if(statusWin.equals("Business")){
////            path = "business-messages"
////            recycler_latest_messages.setBackgroundColor(Color.parseColor("#F9FFA4"))
////        }
//        val ref = FirebaseDatabase.getInstance().getReference("news")
//        ref.addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                Log.d("previousChildName", "snapshot " + snapshot.value)
//                val chatMessage = snapshot.getValue(ChatLogActivity.ChatMessage::class.java)
//                        ?: return
//                Log.d("previousChildName", "newsCard " + chatMessage.author)
//
//                latestMessagesMap[snapshot.key!!] = chatMessage
//                //refreshRecyclerViewMessages()
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                val chatMessage = snapshot.getValue(ChatLogActivity.ChatMessage::class.java)
//                        ?: return
//                latestMessagesMap[snapshot.key!!] = chatMessage
//                refreshRecyclerViewMessages()
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
//    }
//
//    private fun menu_fill(){
//        val ref = FirebaseAuth.getInstance().uid
//        val rootRef = FirebaseDatabase.getInstance().reference
//        val ordersRef = rootRef.child("user").orderByChild("id").equalTo(ref.toString())
//        val valueEventListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (ds in dataSnapshot.children) {
//                    val userName = ds.child("name").getValue().toString()
//                    val userEmail = ds.child("email").getValue().toString()
//                    val userImage = ds.child("image_id").getValue().toString()
//                    //Log.d("LatestMessageActivity", "userName $userName")
//                    //Log.d("LatestMessageActivity", "userEmail $userEmail")
//                    //Log.d("LatestMessageActivity", "userImage $userImage")
//
//                    Picasso.get().load(userImage).fit().centerCrop().into(menu_userimage)
//                    menu_user_email.text = userEmail
//                    menu_username.text = userName
//                }
//            }
//            override fun onCancelled(databaseError: DatabaseError) {
//       //Don't ignore errors!
//            }
//        }
//        ordersRef.addListenerForSingleValueEvent(valueEventListener)
//    }

    val adapter = GroupAdapter<GroupieViewHolder>()

}


