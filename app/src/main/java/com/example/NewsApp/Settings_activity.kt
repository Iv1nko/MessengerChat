package com.example.NewsApp.com.example.messengerchat

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.net.toUri
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.NewsApp.LatestMessageActivity
import com.example.NewsApp.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_latest_message.navView
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.nav_header.view.menu_user_email
import kotlinx.android.synthetic.main.nav_header.view.menu_username
import kotlinx.android.synthetic.main.password_dialog.view.*
import kotlinx.android.synthetic.main.username_dialog.view.*


class Settings_activity : AppCompatActivity() {

    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar!!.title = "Settings"


        val appSettingsPrefs : SharedPreferences = getSharedPreferences("AppSettingsPrefs", 0)
        val sharedPrefsEdit : SharedPreferences.Editor = appSettingsPrefs.edit()
        val isNightModeOn : Boolean = appSettingsPrefs.getBoolean("NightMode", false)
        val arguments = intent.extras


        setting_change_photo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type= "image/*"
            startActivityForResult(intent, 0)
        }
        switch_theme.setOnClickListener(View.OnClickListener {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode", false)
                sharedPrefsEdit.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", true)
                sharedPrefsEdit.apply()
            }
        })
        buttonAccept.setOnClickListener {
            val i = Intent(applicationContext, LatestMessageActivity::class.java)
            var switcher = false
            if (imageUri != null) {
                switcher = true
                i.putExtra("image", imageUri.toString())
            }
            if (edSettingsEmail.text.toString() != "") {
                switcher = true
                Log.d("aaaa", "1")
                i.putExtra("email", edSettingsEmail.text.toString())
            }
            if (edSettingsUsername.text.toString() != "") {
                Log.d("aaaa", "2")
                switcher = true
                i.putExtra("username", edSettingsUsername.text.toString())
            }
            if (switcher) {
                startActivity(i)
                Animatoo.animateFade(this)
            } else Toast.makeText(this, "Введите информацию", Toast.LENGTH_SHORT).show()
        }

        arguments?.getString("image")?.let {
            settingsImageUser.setImageURI(it.toUri())
        }
        arguments?.getString("email")?.let {
            settingsEmail.text = it
        }
        arguments?.getString("username")?.let {
            settingsUsername.text = it
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var selectedPhotoUri: Uri? = null
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null)
        {
            selectedPhotoUri  = data.data
            settingsImageUser.setImageURI(selectedPhotoUri)
            imageUri = selectedPhotoUri
            Log.d("aaaa", imageUri.toString())
        }
    }


//    private fun fillActivity() {
//        var selectedPhotoUri: Uri? = null
//        var ref = FirebaseAuth.getInstance().uid
//        var rootRef = FirebaseDatabase.getInstance().reference
//        var ordersRef = rootRef.child("/user/$ref")
//        val ordersRefFill = rootRef.child("user").orderByChild("id").equalTo(ref.toString())
//        val valueEventListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (ds in dataSnapshot.children) {
//                    val userName = ds.child("name").getValue().toString()
//                    val userEmail = ds.child("email").getValue().toString()
//                    val userImage = ds.child("image_id").getValue().toString()
//                    val userStatus = ds.child("status").getValue().toString()
//
//                    Picasso.get().load(userImage).fit().centerCrop().into(setting_imageUser)
//
//                    setting_email.text = userEmail
////                    settings_email.setText(userEmail)
////                    settings_email.setCursorVisible(false)
////                    settings_email.setOnClickListener { settings_email.setCursorVisible(true)
//                    }
//                    settings_username.setCursorVisible(false)
//                    settings_username.setOnClickListener { settings_username.setCursorVisible(true)
//                    }
//
//                    setting_username.text = userName
//                    //settings_username.setText(userName)
//
//                    settings_username.setText(userStatus)
//
//                    settings_btn_change_email.setOnClickListener {
//                        Log.d("Settings_activity", "Click")
//                    }
//                }
//            }
//            override fun onCancelled(databaseError: DatabaseError) {
//                 //Don't ignore errors!
//            }
//        }
//        ordersRefFill.addListenerForSingleValueEvent(valueEventListener)
//    }



//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.nav_drawer_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.settings_menu_change_name -> {
//                Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show()
//                val mDialogView = LayoutInflater.from(this).inflate(R.layout.username_dialog, null)
//                //AlertDialogBuilder
//                val mBuilder = AlertDialog.Builder(this)
//                        .setView(mDialogView)
//                        .setTitle("Change Name")
//                //show dialog
//                val mAlertDialog = mBuilder.show()
//                //login button click of custom layout
//                mDialogView.dialogLoginBtn.setOnClickListener {
//                    //dismiss dialog
//                    mAlertDialog.dismiss()
//
//                    //set the input text in TextView
//                }
//                mDialogView.dialogCancelBtn.setOnClickListener {
//                    var selectedPhotoUri: Uri? = null
//                    var ref = FirebaseAuth.getInstance().uid
//                    var rootRef = FirebaseDatabase.getInstance().reference
//                    var ordersRef = rootRef.child("/user/$ref")
//                    //dismiss dialog
//                    mAlertDialog.dismiss()
//                    //get text from EditTexts of custom layout
//                    val name = mDialogView.dialogNameEt.text.toString()
//                    ordersRef.child("name").setValue(name)
//                    setting_username.text = name
//                }
//
//            }
//            R.id.settings_menu_change_password -> {
//                Toast.makeText(this, "Item2", Toast.LENGTH_SHORT).show()
//                val mDialogView = LayoutInflater.from(this).inflate(R.layout.password_dialog, null)
//                //AlertDialogBuilder
//                val mBuilder = AlertDialog.Builder(this)
//                        .setView(mDialogView)
//                        .setTitle("Change Password")
//                //show dialog
//                val mAlertDialog = mBuilder.show()
//                //login button click of custom layout
//                mDialogView.dialogPassLoginBtn.setOnClickListener {
//                    var selectedPhotoUri: Uri? = null
//                    var ref = FirebaseAuth.getInstance().uid
//                    var rootRef = FirebaseDatabase.getInstance().reference
//                    var ordersRef = rootRef.child("/user/$ref")
//                    //dismiss dialog
//                    mAlertDialog.dismiss()
//                    //get text from EditTexts of custom layout
//                    val pass = mDialogView.dialogPassEt.text.toString()
//                    val passNew = mDialogView.dialogPassNewEt.text.toString()
//                    //set the input text in TextView
//                    val valueEventListener = object : ValueEventListener {
//                        override fun onDataChange(snapshot: DataSnapshot) {
//                            val hash = snapshot.child("password").getValue().toString()
//                            Log.d("hash", "hash: " + hash)
//                            val result: BCrypt.Result = BCrypt.verifyer().verify(pass.toCharArray(), hash)
//                            if (result.verified) {
//                                if (passNew != "") {
//                                    val passwordHash = BCrypt.withDefaults().hashToString(12, passNew.toCharArray())
//                                    ordersRef.child("password").setValue(passwordHash)
//                                    Toast.makeText(baseContext, "Password Change", Toast.LENGTH_SHORT).show()
//                                } else {
//                                    Toast.makeText(baseContext, "Enter New Password", Toast.LENGTH_SHORT).show()
//                                }
//                                //
//                            }
//                        }
//                        override fun onCancelled(error: DatabaseError) {
//                            TODO("Not yet implemented")
//                        }
//                    }
//                    ordersRef.addListenerForSingleValueEvent(valueEventListener)
//                }
//                mDialogView.dialogPassCancelBtn.setOnClickListener {
//                    //dismiss dialog
//                    mAlertDialog.dismiss()
//                }
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


}