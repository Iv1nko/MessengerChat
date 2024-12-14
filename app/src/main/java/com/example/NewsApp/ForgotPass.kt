package com.example.NewsApp.com.example.messengerchat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.NewsApp.LoginActivity
import com.example.NewsApp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

private var mAuth: FirebaseAuth? = null
private var rootRef: DatabaseReference? = null
private  var mDataBase:DatabaseReference? = null
private  var ordersRef:DatabaseReference? = null

class ForgotPass: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.title = "Password Recovery"
        setContentView(R.layout.forgot_pass)
        Log.d("ForgotPass Class", "ForgotPass Started")
        Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
    }

    fun SendEmaiVerForgotPass(view: View) {
        mAuth = FirebaseAuth.getInstance()
        rootRef = FirebaseDatabase.getInstance().reference
        val email = findViewById<TextView>(R.id.emailForgotPass).text.toString()
        val newPass = findViewById<TextView>(R.id.newPass_one).text.toString()

        mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener { task -> if (task.isComplete)
        {
            Log.d("ForgotPass", "Email send successful")

//            rootRef!!.child("user").orderByChild("email").equalTo(email)
//                .addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        if (snapshot.exists()) {
//                            for (data in snapshot.children) {
//                                val hash = data.child("password").value.toString()
//                                Log.d("ForgotPass", "Hash = $hash")
//                                BackForgot()
//                            //    val result = BCrypt.verifyer().verify(newPass.toCharArray(), hash)
////                                if (result.verified) {
////                                    Log.d("LoginActivity", "result $result")
////                                    //signInWithEmailAndPassword()
////                                } else {
////                                    Toast.makeText(
////                                        baseContext,
////                                        "Incorrect password",
////                                        Toast.LENGTH_SHORT
////                                    ).show()
////                                }
//                            }
//                        } else {
//                            Toast.makeText(baseContext, "Incorrect email", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
//                    }
//                })
        }
        else {
           Log.d("ForgotPass", " Email not sending")
        } }




        // отравка сообщения воостановления пароля
//        mAuth!!.sendPasswordResetEmail(emailPass).addOnCompleteListener { task -> if (task.isComplete)
//        {
//            Log.d("ForgotPass", "Email send successful")
//        }
//        else {
//           Log.d("ForgotPass", " Email not sending")
//        } }
//        Log.d("ForgotPass", "User $user")


//        val user = FirebaseAuth.getInstance().currentUser!!
//        user!!.sendEmailVerification().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                if (!emailPass.getText().toString().isEmpty())
//                {
//                    Toast.makeText(
//                        applicationContext,
//                        "Сheck your Email for verification message",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    verification_true()
//                    val r = Runnable { waitVerification() }
//                    val h = Handler()
//                    h.postDelayed(r, 1000)
//                } else {
//                    Toast.makeText(
//                        applicationContext,
//                        "Please enter Email",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            } else {
//                Toast.makeText(applicationContext, "Send Email failed", Toast.LENGTH_SHORT).show()
//            }
//        }

    }

    private fun BackForgot() {
        var i = Intent(this@ForgotPass, LoginActivity::class.java)
        startActivity(i)
    }
}


