package com.example.NewsApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info_user.*

class infoUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)
        val id = intent.getStringExtra("id").toString()
        Log.d("InfoActivity", id.toString())

        val userText = findViewById<TextView>(R.id.info_username)
        val status_user = findViewById<TextView>(R.id.info_status)

        val reference = FirebaseDatabase.getInstance().getReference("/user/$id")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("name").getValue().toString()
                val image = snapshot.child("image_id").getValue().toString()
                val status = snapshot.child("status").getValue().toString()

                Picasso.get().load(image).fit().centerCrop().into(info_imageView)
                userText.setText(name)
                status_user.setText(status)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateFade(this)
    }

}

