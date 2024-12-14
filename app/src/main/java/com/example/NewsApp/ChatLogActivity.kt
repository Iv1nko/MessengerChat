package com.example.NewsApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


//import kotlinx.android.synthetic.main.activity_main.*


class ChatLogActivity: AppCompatActivity() {

    companion object{
        val USER_NAME = "USER_NAME"
        val USER_ID = "USER_ID"
        val USER_IMAGE = "USER_IMAGE"
    }
    // Текс сообщения
    private val edit_chat_log
    get() = findViewById<EditText>(R.id.edit_text)
    val adapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        val recyclerView_chat_log = findViewById<RecyclerView>(R.id.recyclerview_chat_log)
        recyclerView_chat_log.adapter = adapter

//        val user_name = intent.getStringExtra(QuizActivity.USER_KEY)
//        supportActionBar?.title = user_name
//        ////
//        val ref = FirebaseAuth.getInstance().uid
//        val rootRef = FirebaseDatabase.getInstance().reference
//        val ordersRef = rootRef.child("connections")
////        val idStatus = intent.getStringExtra(QuizActivity.USER_ID)
//        ordersRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val id_online_user = snapshot.child(idStatus.toString()).getValue().toString()
//                Log.d("connections", "status : "+ idStatus)
//                Log.d("connections", "connections : "+ id_online_user)
//                if(id_online_user != false.toString()){
//                    supportActionBar?.subtitle = "Online"
//                }
//                else{
//                    val ordersRefOffline = rootRef.child("lastConnected")
//                    ordersRefOffline.addValueEventListener(object : ValueEventListener{
//                        override fun onDataChange(snapshot: DataSnapshot) {
//                            val id_offline_user = snapshot.child(idStatus.toString()).getValue().toString()
//                            Log.d("connections", "connections : " + id_offline_user)
//                            supportActionBar?.subtitle = id_offline_user
//                        }
//
//                        override fun onCancelled(error: DatabaseError) {
//                            TODO("Not yet implemented")
//                        }
//                    })
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })


        //setupDummyData()
        //listenForMessages()

        val send_button = findViewById<Button>(R.id.button_send_message)
        send_button.setOnClickListener {
            //performeToSendMessage()
            //adapter.add(ChatFromItem())
        }
        edit_chat_log.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                    Log.d("Enter Key", "Enter Key")
                return@OnKeyListener true
            }
            false
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chatmenu, menu)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateFade(this)
    }
    //Кнопка - информация о пользователе
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.audioCall_chat -> {
//                Toast.makeText(this,
//                    "Audio Call", Toast.LENGTH_SHORT).show()
//                val userId = intent.getStringExtra(QuizActivity.USER_ID)
//                //Log.d("CHAT_STATUS", userId.toString())
//
////                val intent = Intent(this@ChatLogActivity,infoUser::class.java)
////                intent.putExtra("id",userId)
////                startActivity(intent)
////                Animatoo.animateFade(this@ChatLogActivity)
//
//            }
//            R.id.videoCall_chat -> {
//                Toast.makeText(this,
//                    "Video Call", Toast.LENGTH_SHORT).show()
//                val userId = intent.getStringExtra(QuizActivity.USER_ID)
//                //Log.d("CHAT_STATUS", userId.toString())
//
////                val intent = Intent(this@ChatLogActivity,infoUser::class.java)
////                intent.putExtra("id",userId)
////                startActivity(intent)
////                Animatoo.animateFade(this@ChatLogActivity)
//
//            }
//            R.id.info_chat -> {
//                Toast.makeText(this,
//                    "Info", Toast.LENGTH_SHORT).show()
//                val userId = intent.getStringExtra(QuizActivity.USER_ID)
//                Log.d("CHAT_STATUS", userId.toString())
//
//                val intent = Intent(this@ChatLogActivity,infoUser::class.java)
//                intent.putExtra("id",userId)
//                startActivity(intent)
//                Animatoo.animateFade(this@ChatLogActivity)
//
//            }
//
//
//        }
//        return super.onOptionsItemSelected(item)
//    }
    //Диалоговое окно - Удаление
//    private fun alertDialog(referenceFrom: DatabaseReference, referenceTo: DatabaseReference, text: String) {
//        val builder = AlertDialog.Builder(this)
//        val subBuilder = AlertDialog.Builder(this)
//        val editText = EditText(this)
//        builder.setTitle("What we will do?").setCancelable(true)
//
//        builder.setPositiveButton("Delete", { dialogInterface: DialogInterface, i: Int ->
//            referenceTo.removeValue()
//            referenceFrom.removeValue().addOnSuccessListener {
//                Toast.makeText(this,"Message Deleted", Toast.LENGTH_SHORT).show()
//                adapter.clear()
//                listenForMessages()
//            }
//        })
//        builder.setNeutralButton("Cancel",{ dialogInterface: DialogInterface, i: Int ->
//            Log.d("ChatLogActivity", "Canceled")
//        })
//        builder.setNegativeButton("Edit", { dialogInterface: DialogInterface, i: Int ->
//            Toast.makeText(this,"Message Edit", Toast.LENGTH_SHORT).show()
//            subBuilder.setTitle("Edit Text")
//            subBuilder.setView(editText)
//            val textMess = referenceFrom.child("text")
//            Log.d("ChatLogActivity","text: " + textMess)
//            editText.setText(text)
//            subBuilder.setPositiveButton("Save", { dialogInterface: DialogInterface, i: Int ->
//                Log.d("ChatLogActivity","path: " + editText.text.toString())
//                referenceTo.child("text").setValue(editText.text.toString())
//                referenceFrom.child("text").setValue(editText.text.toString())
//                Toast.makeText(this," Edit", Toast.LENGTH_SHORT).show()
//                adapter.clear()
//                listenForMessages()
//            })
//            subBuilder.show()
//        })
//        builder.show()
//    }


//    private fun deleteMessageFrom(toString: DatabaseReference) {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Delete Message?").setCancelable(true)
//
//        builder.setPositiveButton("Delete", { dialogInterface: DialogInterface, i: Int ->
//            toString.removeValue().addOnSuccessListener {
//                Toast.makeText(this,"Message Deleted", Toast.LENGTH_SHORT).show()
//                adapter.clear()
//                listenForMessages()
//            }
//        })
//        builder.setNeutralButton("Cancel",{ dialogInterface: DialogInterface, i: Int ->
//            Log.d("ChatLogActivity", "Canceled")
//        })
//
//        builder.show()
//    }


    //Показываем все сообщения
//    private fun listenForMessages(){
//        //
//        //Какое окно сейчас открыто
//        var winStatus = intent.getStringExtra(QuizActivity.STATUS_WIN)
//        //
//        val from_id = FirebaseAuth.getInstance().uid
//        val to_id = intent.getStringExtra(QuizActivity.USER_ID)
//        var path = "user-messages"
//        if(winStatus.equals("General")){
//            path = "user-messages"
//        }
//        else if(winStatus.equals("Business")){
//            path = "business-user-messages"
//        }
//        val ref = FirebaseDatabase.getInstance().getReference("/$path/$from_id/$to_id")//.equalTo("General")
//        val recyclerView_chat_log = findViewById<RecyclerView>(R.id.recyclerview_chat_log)
//        ref.addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                val chatMessage = snapshot.getValue(ChatMessage::class.java)
//                val refChild = FirebaseAuth.getInstance().uid
//                val rootRef = FirebaseDatabase.getInstance().reference
//                var positionMessage : String = ""
//                // Вывод в recycler view сообщения
//                if (chatMessage != null) {
//                    // Сообщение от другого пользователя
//                    //from_id - читает из базы данных
//                    Log.d("Win_status","Windows before send is: $winStatus")
//
//                    updatelatestMessages(from_id!!, to_id!!, chatMessage!!, winStatus.toString())
//
//                    if (chatMessage.from_id != FirebaseAuth.getInstance().uid) {
//                        val userImage = intent.getStringExtra(QuizActivity.USER_IMAGE)
//                        adapter.add(ChatFromItem(chatMessage.text, userImage.toString(), chatMessage.time, chatMessage.id, chatMessage.from_id, chatMessage.to_id))
//                        adapter.setOnItemClickListener { item, view ->
//                            positionMessage = "left"
//                            if(positionMessage.equals("left")) {
//                                Log.d("ChatLogActivity", "Position: " + positionMessage)
//                                val messInfoFrom = item as ChatFromItem
//                                val deleteMesage = ref.child(messInfoFrom.id_mess)
//                                deleteMessageFrom(deleteMesage)
//                            }
//                        }
//                    }
//                    else
//                    {
//                        val ordersRef = rootRef.child("user").orderByChild("id").equalTo(refChild)
//                        val valueEventListener = object : ValueEventListener {
//                            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                                for (ds in dataSnapshot.children) {
//                                    val userImage = ds.child("image_id").getValue(String::class.java)
//                                    adapter.add(ChatToItem(chatMessage.text, userImage.toString(), chatMessage.time, chatMessage.id, chatMessage.from_id, chatMessage.to_id))
//                                    adapter.setOnItemClickListener { item, view ->
//                                        positionMessage = "right"
//                                        if(positionMessage.equals("right")) {
//                                            Log.d("ChatLogActivity", "Position: " + positionMessage)
//                                            val messInfo = item as ChatToItem
//                                            val referenceFrom = FirebaseDatabase.getInstance().getReference("/$path/$from_id/$to_id/${messInfo.id_mess}")
//                                            val toReference = FirebaseDatabase.getInstance().getReference("/$path/$to_id/$from_id/${messInfo.id_mess}")
//                                            alertDialog(referenceFrom, toReference, messInfo.text)
//                                        }
//                                    }
//                                }
//
//                            }
//                            override fun onCancelled(databaseError: DatabaseError) {
//                                Log.d("ChatLogActivity", "Error") //Don't ignore errors!
//                            }
//                        }
//                        ordersRef.addListenerForSingleValueEvent(valueEventListener)
//                    }
//                    recyclerView_chat_log.scrollToPosition(adapter.itemCount - 1)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("ChatLogActivity", "Error first")
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//
//            }
//
//            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//
//            }
//
//            override fun onChildRemoved(snapshot: DataSnapshot) {
//
//            }
//        })
//
//    }

    class MessInfo(val from_id: String, val id: String, val text: String, val time: String, val to_id: String ): Item<GroupieViewHolder>(), View.OnLongClickListener {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getLayout(): Int {
            TODO("Not yet implemented")
        }

        override fun onLongClick(v: View?): Boolean {
            TODO("Not yet implemented")
        }
    }

    class ChatMessage(val id: String, val author: String, val description: String, val displayname: String, val newsdate: String, val owner:String, val url:String, val updatedate:String, val name:String, val image_id:String){
        constructor(): this("", "", "", "", "", "", "","","","")
    }

    class News(val description: String, val displayname: String,
//               val id: String, val author: String, val newsdate: String, val owner:String, val url:String, val updatedate:String, val name:String,
               val image_id: Int){
//        constructor(): this("", "", "", "", "", "", "","","","")
    }

    class Cubs(val id: String, val name:String, val image_cub:Int, val description_cub:String){
//        constructor(): this("","" ,"", "")
    }
    class Team(val id: String, val name:String, val image: Int, val position:String, val number:String){
//        constructor(): this("","" ,"", "","")
    }

    class Date(val id: String, val image_date: Int, val description:String){
//        constructor(): this("","","")
    }



    //Функция отправки сообщений в FIREBASE
//    private fun performeToSendMessage(){
//        //Какое окно сейчас открыто
//        var winStatus = intent.getStringExtra(QuizActivity.STATUS_WIN)
//        Log.d("Win_status","Windows is: $winStatus")
//        // Recycler всех сообщений
//        val recyclerChatLog = findViewById<RecyclerView>(R.id.recyclerview_chat_log)
//        val text  = edit_chat_log.text.toString()
//        // Ищем id
//        val from_id = FirebaseAuth.getInstance().uid
//        val to_id = intent.getStringExtra(QuizActivity.USER_ID)
//
//        //Добавляем отображение категорий General
//        val categoryMessage = winStatus.toString()
//        val readMessage = "false"
//        //Проверка окна для отправки сообщения
//        if(from_id == null){
//            return
//        }
//        if(text != "") {
//            var path = "user-messages"
//            // Смотрим на таблицу как автор сообщения
//            if(winStatus.equals("General")){
//                path = "user-messages"
//            }
//            else if(winStatus.equals("Business")){
//                path = "business-user-messages"
//            }
//            val reference = FirebaseDatabase.getInstance().getReference("/$path/$from_id/$to_id").push()
//            val allMessages = FirebaseDatabase.getInstance().getReference("/messages/$from_id").push()
//            // Смотрим на таблицу как пользователь сообщения
//            val key1 = reference.key
//            val toReference = FirebaseDatabase.getInstance().getReference("/$path/$to_id/$from_id/$key1")
//            //
//            Log.d("chatLogInfo","reference = $reference")
//            Log.d("chatLogInfo","allMessages = $allMessages")
//            Log.d("chatLogInfo","toReference = $toReference")
//            //
//            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//            //SimpleDateFormat("yyyy-MM-dd.HH.mm.ss.SS")
//            val date = sdf.format(Date())
//
//            // Структура сообщения
//            val chatMessage = ChatMessage(reference.key!!, text, from_id, to_id!!, date, categoryMessage, readMessage)
//
//            Log.d("ChatMessage", "ChatMessage = " + chatMessage)
//            // Прокрутка вниз
//            reference.setValue(chatMessage)
//                    .addOnSuccessListener {
//                        edit_chat_log.text.clear()
//                        recyclerChatLog.scrollToPosition(adapter.itemCount - 1)
//                    }
//            toReference.setValue(chatMessage)
//            allMessages.setValue(key1)
//
//
//            updatelatestMessages(from_id,to_id,chatMessage, categoryMessage)
//
//
//
//        }
//        else{
//            Toast.makeText(this,"Empty Message", Toast.LENGTH_SHORT).show()
//        }
//    }
//    private fun updatelatestMessages(from_id: String, to_id: String, chatMessage: ChatMessage, category: String) {
//        var path = "latest-messages"
//        if (category.equals("General")){
//            path = "latest-messages"
//        }
//        else if(category.equals("Business")){
//            path = "business-messages"
//        }
//        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/$path/$from_id/$to_id")
//        latestMessageRef.setValue(chatMessage)
//        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/$path/$to_id/$from_id")
//        latestMessageToRef.setValue(chatMessage)
//        //
//        Log.d("latestMessageAc","ChatLogActiv path is $latestMessageRef")
//
//    }
//    private fun updateBusinesslatestMessages(from_id: String, to_id: String, chatMessage: ChatMessage) {
//        val businessMessageRef = FirebaseDatabase.getInstance().getReference("/business-messages/$from_id/$to_id")
//        businessMessageRef.setValue(chatMessage)
//        val businessMessageToRef = FirebaseDatabase.getInstance().getReference("/business-messages/$to_id/$from_id")
//        businessMessageToRef.setValue(chatMessage)
//    }
}
//
class AllMess(val text: String, val image: String, val time: String,val id_mess: String, val to_id: String, val from_id: String): Item<GroupieViewHolder>(), View.OnLongClickListener {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getLayout(): Int {
        TODO("Not yet implemented")
    }

    override fun onLongClick(v: View?): Boolean {
        TODO("Not yet implemented")
    }
}
//Заполнение сообщения отправителя - время, фото...
class ChatFromItem(val text: String, val image: String, val time: String,val id_mess: String, val to_id: String, val from_id: String): Item<GroupieViewHolder>(), View.OnLongClickListener {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.another_message_text).text = text
        val dsds = viewHolder.itemView.findViewById<TextView>(R.id.your_message_text)
        val imageUser = viewHolder.itemView.findViewById<ImageView>(R.id.image_user_chat_log_left)
        //Картинка пользователя - получателя
        Picasso.get()
                .load(image)
                .fit()
                .centerCrop()
                .into(imageUser)
        //
        viewHolder.itemView.findViewById<TextView>(R.id.message_time).text = time


    }

    override fun getLayout(): Int {
        return R.layout.list_item_another_mess
    }

    override fun onLongClick(v: View?): Boolean {
        Log.d("ChatLogActivity", "item is clicked Long click left")
        return true
    }
}

//Заполнение сообщения получателя
class ChatToItem(val text: String, val image: String, val time_to: String,val id_mess: String, val to_id: String, val from_id: String): Item<GroupieViewHolder>(), View.OnLongClickListener {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.your_message_text).text = text
        val dsds = viewHolder.itemView.findViewById<TextView>(R.id.your_message_text)
        val idMess = id_mess
        Log.d("ChatLogActivity","position " + position.toString())

        val imageUser = viewHolder.itemView.findViewById<ImageView>(R.id.image_user_chat_log_right)
        //Картинка пользователя
        Picasso.get()
                .load(image)
                .fit()
                .centerCrop()
                .into(imageUser)
        viewHolder.itemView.findViewById<TextView>(R.id.message_time).text = time_to
    }
    override fun getLayout(): Int {
        return R.layout.list_item_my_message
    }

    override fun onLongClick(v: View?): Boolean {
        Log.d("ChatLogActivity", "item is clicked Long click right")
        val uid = FirebaseAuth.getInstance()
        Log.d("ChatLogActivity","uid: " )
        //delete()
        return true
    }

}


