package com.example.NewsApp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.database.DatabaseReference
import com.firebase.ui.database.FirebaseListAdapter
import com.example.NewsApp.Message_list
import android.os.Bundle
import com.example.NewsApp.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.content.Intent
import android.content.SharedPreferences
import com.example.NewsApp.com.example.messengerchat.ForgotPass
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import at.favre.lib.crypto.bcrypt.BCrypt
import com.google.firebase.database.DatabaseError
import com.example.NewsApp.LatestMessageActivity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.first_page_chat.enter_user_name
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*
import kotlin.Throws

class LoginActivity : AppCompatActivity() {
    private var EdUserName: EditText? = null
    private var EdUserPassword: EditText? = null
    private var EdUserEmail: EditText? = null
    private var mAuth: FirebaseAuth? = null
    private var bSignUp: Button? = null
    private var bSignInHaveAccount: Button? = null
    private var bVerSign: Button? = null
    private var bBack: Button? = null
    private var forgot_pass_ver_email: Button? = null
    private var bSignIn: TextView? = null
    private var alredy_user_forgot_pass: TextView? = null

    //Переменные для загрузки картинок
    private var profilePic: ImageView? = null
    private var verificationTruePic: ImageView? = null
    private val imageUser: ImageView? = null
    private val ref: FirebaseStorage? = null
    private var mStorageRef: StorageReference? = null
    private var rootRef: DatabaseReference? = null
    private var mDataBase: DatabaseReference? = null
    private val ordersRef: DatabaseReference? = null
    private var uploadUri: Uri? = null

    //Перадача параметров
    private val listView: ListView? = null

    //Ссылка на изображение
    var userName = "USER_NAME"

    //
    private val adapter: FirebaseListAdapter<Message_list>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_page_chat)
        FirebaseApp.initializeApp(this);



        init()
    }

    override fun onStart() {
        super.onStart()
        val cUser = mAuth!!.currentUser
        //Проверка зарегистрирован ли пользователь
        if (cUser != null && cUser.isEmailVerified) {
            openChat()
        } else {
            notSigned()
        }
    }

    // Инициализация всех элементов
    private fun init() {
        mStorageRef = FirebaseStorage.getInstance().getReference("ImageDB")
        mDataBase = FirebaseDatabase.getInstance().getReference("/user")
        val uid = FirebaseAuth.getInstance().uid
        //FirebaseDatabase.getInstance().getReference("/users/"+ uid);
        EdUserName = findViewById(R.id.enter_user_name)
        EdUserEmail = findViewById(R.id.enter_user_email)
        EdUserPassword = findViewById(R.id.enter_user_password)
        mAuth = FirebaseAuth.getInstance()
        bSignUp = findViewById(R.id.button_registrate)
        bSignIn = findViewById(R.id.alredy_user_have_account)
        profilePic = findViewById(R.id.image_user)
        verificationTruePic = findViewById(R.id.image_true_verification)
        bVerSign = findViewById(R.id.bVerSign)
        bSignInHaveAccount = findViewById(R.id.sign_in_have_account)
        bBack = findViewById(R.id.back_sign_in)
        alredy_user_forgot_pass = findViewById(R.id.alredy_user_forgot_pass)
        forgot_pass_ver_email = findViewById(R.id.forgot_pass_ver_email)
    }

    // Регистрация пользователя
    fun onClickSignUp(view: View?) {
        //Uri imageUri = uploadUri;
        // Проверка пользователя на подтверждение почты
        if (!EdUserEmail!!.text.toString().isEmpty() && !EdUserPassword!!.text.toString()
                .isEmpty() && !EdUserName!!.text.toString().isEmpty()
        ) {
            mAuth!!.createUserWithEmailAndPassword(
                EdUserEmail!!.text.toString(),
                EdUserPassword!!.text.toString()
            ).addOnCompleteListener { task ->
                val user = mAuth!!.currentUser
                if (task.isSuccessful) {
                    sendEmailVer()
                    Log.d("Log", "Email sending")
                    assert(user != null)
                    if (user!!.isEmailVerified) {
                        Log.d("Log", "User.IsEmailVerified = TRUE")

                        //uploadImage();
                        //SaveUser();


                        //openChat();
                    } else {
                        Log.d("Log", "User.IsEmailVerified = FALSE")
                        Toast.makeText(applicationContext, "Check your Mail", Toast.LENGTH_SHORT)
                            .show()

                        //uploadImage();
                        //SaveUser();
                    }
                } else {
                    notSigned()
                    Toast.makeText(applicationContext, "Error registration", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            Toast.makeText(this, "Please enter UserName, Email and Password", Toast.LENGTH_SHORT)
                .show()
        }
    }

    //Отправка сообщения
    // Восстановление пароля
    fun onClickEnterPassword(view: View?) {
//        EdUserPassword.setVisibility(View.GONE);
//        EdUserPassword.setText("0");
//        bSignInHaveAccount.setVisibility(View.GONE);
//        forgot_pass_ver_email.setVisibility(View.VISIBLE);
//
//        if (!EdUserEmail.getText().toString().isEmpty() && !EdUserPassword.getText().toString().isEmpty() && !EdUserName.getText().toString().isEmpty()) {
//            mAuth.createUserWithEmailAndPassword(EdUserEmail.getText().toString(), EdUserPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    if (task.isSuccessful()){
//                        sendEmailVer();
//                        if(user.isEmailVerified()) {
//                            uploadImage();
//                            openChat();
//                        }
//                    }
//                    else {
//                        notSigned();
//                        Toast.makeText(getApplicationContext(), "Error registration", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//        else{
//            Toast.makeText(this, "Please enter UserName, Email and Password", Toast.LENGTH_SHORT).show();
//        }
        openEnterPassWin()
    }

    //
    private fun openEnterPassWin() {
        val i = Intent(this@LoginActivity, ForgotPass::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        finish()
    }

    // Загрузка второго окна(Регистрация прошла успешно)
    fun onClickSignIn(view: View?) {
        showSigned()
    }

    fun onClickBack(view: View?) {
        notSigned()
    }

    //Функция кнопки "Войти по сущетсвующему аккаунту"
    fun onClickSignInHaveAccount(view: View?) {
        val Email = EdUserEmail!!.text.toString()
        val Password = EdUserPassword!!.text.toString()
        Log.d("testSignIN", "email $Email pass $Password")
        if (!Email.isEmpty() && !Password.isEmpty()) {
            signInWithEmailAndPassword()

//            rootRef = FirebaseDatabase.getInstance().reference
//            rootRef!!.child("user").orderByChild("email").equalTo(Email).addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        Log.d("testSignIN", "222")
//                        if (snapshot.exists()) {
//                            for (data in snapshot.children) {
//                                val hash = data.child("password").value.toString()
//                                val result = BCrypt.verifyer().verify(Password.toCharArray(), hash)
//                                if (result.verified) {
//                                    Log.d("testSignIN", "Email user: $result")
//                                    signInWithEmailAndPassword()
//                                } else {
//                                    Toast.makeText(baseContext, "Incorrect password", Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }
//                        } else {
//                            Toast.makeText(baseContext, "Incorrect email", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        showSigned()
//                    }
//                })


        } else {
            Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show()
        }
    }

    // Фукнция входа с помощью сущетсвующего аккаунта
    private fun signInWithEmailAndPassword() {
        Log.d("testSignIN", "signinwithemailandpassword")
        mAuth?.signInWithEmailAndPassword(
            EdUserEmail!!.text.toString(),
            EdUserPassword!!.text.toString()
        )
            ?.addOnCompleteListener(this) { task ->
//                FirebaseAuth.getInstance().currentUser!!.sendEmailVerification()
                // Вход с помощью авторизации
                if (FirebaseAuth.getInstance().currentUser!!.isEmailVerified) {
                    if (task.isSuccessful) {
                        Log.d("testSignIN", "Succes! User verify")
                        Toast.makeText(
                            applicationContext,
                            "User SignIn successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        openChat_Sign_in()
                    } else {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.d("testSignIN", "Error! User did not verify")
                    Toast.makeText(
                        applicationContext,
                        "User did not verify Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun SaveUser(UploadUri: Uri?) {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/user/$uid")
        val Name = EdUserName!!.text.toString()
        val Email = EdUserEmail!!.text.toString()
        val Password = EdUserPassword!!.text.toString()
        if (Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Field must be fill", Toast.LENGTH_SHORT).show()
        } else {
            val passwordHash = BCrypt.withDefaults().hashToString(12, Password.toCharArray())
            if (uid != null) {
                if (UploadUri == null) {
                    Log.d("Registrate", "Image URI is NULL$UploadUri")
                    val imageString = ""
                    val newUser =
                        User(uid, Name, Email, passwordHash, imageString, "")
                    ref.setValue(newUser)
                } else {
                    Log.d("Registrate", "Image URI$UploadUri")
                    val newUser = User(
                        uid,
                        Name,
                        Email,
                        passwordHash,
                        UploadUri.toString(),
                        ""
                    )
                    ref.setValue(newUser).addOnCompleteListener(this) {
                        Log.d(
                            "Registrate",
                            "User is saved"
                        )
                    }
                }
            } else {
                Log.d("Registrate", "User is NOT saved")
                Toast.makeText(this, "Error UID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Верификация пользователя
    fun onClickVerComplete(view: View?) {
        Log.d("LOG", "onClickVerComplete")
        if (!EdUserEmail!!.text.toString().isEmpty() && !EdUserPassword!!.text.toString()
                .isEmpty()
        ) {
            mAuth!!.signInWithEmailAndPassword(
                EdUserEmail!!.text.toString(),
                EdUserPassword!!.text.toString()
            ).addOnCompleteListener(this) {
                // Убрал верификацию пользователя
                if (Objects.requireNonNull(FirebaseAuth.getInstance().currentUser)!!.isEmailVerified) {
                    uploadImage()
                    //openChat();
                } else {
                    Log.d("LOG", "Not ver EMAIL")
                }
            }
        } else {
            Toast.makeText(this, "Please enter Email, Password", Toast.LENGTH_SHORT).show()
        }
    }

    // Вход с существующим аккаунтом
    private fun openChat_Sign_in() {
        val i = Intent(this@LoginActivity, LatestMessageActivity::class.java)
        val sharedPref: SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        if (EdUserName!!.text.isNotEmpty() || sharedPref.getString("username", "][q;") != "][q;") {
            if(sharedPref.getString("username", "][q;") == "][q;") {
                editor.putString("username", EdUserName!!.text.toString()).commit()
                i.putExtra("image", sharedPref.getString("image", ""))
                i.putExtra("username", EdUserName!!.text.toString())
                i.putExtra("email", mAuth?.currentUser?.email)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                Log.d("aaaa", "1")
                startActivity(i)
                finish()
            } else {
                editor.putString("username", EdUserName!!.text.toString()).commit()
                i.putExtra("image", sharedPref.getString("image", ""))
                i.putExtra("username", EdUserName!!.text.toString())
                i.putExtra("email", EdUserEmail!!.text.toString())
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                Log.d("aaaa", "2")
                startActivity(i)
                finish()
            }
        } else Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show()
    }

    private fun openChat() {
        val i = Intent(this@LoginActivity, LatestMessageActivity::class.java)
        val sharedPref: SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        if (EdUserName!!.text.isNotEmpty() || sharedPref.getString("username", "][q;") != "][q;") {
            if(sharedPref.getString("username", "][q;") == "][q;") {
                editor.putString("username", EdUserName!!.text.toString()).commit()
                i.putExtra("username", EdUserName!!.text.toString())
                i.putExtra("email", mAuth?.currentUser?.email)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                Log.d("aaaa", "3")
                startActivity(i)
                finish()
            } else {
                i.putExtra("username", sharedPref.getString("username", "username909"))
                i.putExtra("email", mAuth?.currentUser?.email)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                Log.d("aaaa", "4")
                startActivity(i)
                finish()
            }
        } else Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show()
    }

    // Выход из учетной записи
    fun onClickSignOut(view: View?) {
        FirebaseAuth.getInstance().signOut()
        notSigned()
    }

    // Добавление фото
    fun onClickChooseImage(view: View?) {
        image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && data != null && data.data != null) {
            profilePic!!.setImageURI(data.data)
        }
    }

    private val image: Unit
        private get() {
            val intentChooser = Intent()
            intentChooser.type = "image/*"
            intentChooser.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intentChooser, 1)
        }

    // Загрузка картинки на FireBase
    private fun uploadImage() {
        val bitmap = (profilePic!!.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArray = baos.toByteArray()
        val mRef = mStorageRef!!.child(System.currentTimeMillis().toString() + "myImage")
        val up = mRef.putBytes(byteArray)
        val task = up.continueWithTask { mRef.downloadUrl }
            .addOnCompleteListener { task ->
                uploadUri = task.result
                Log.d("Registrate", "image is uploading")
                val sharedPref: SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("image", uploadUri.toString()).commit()
                SaveUser(uploadUri)
                openChat()
            }
    }

    // Переход между классами(открытие 3го окна)
    /*
    public void onClickStart(View view) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        uploadImage();
        startActivity(i);
    }*/
    // Переключение на окно ввода почты
    fun onClickForgotPass(view: View?) {
        val i = Intent(this@LoginActivity, ForgotPass::class.java)
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i)
    }

    // Скрытие элементов регистрации
    private fun showSigned() {
        //
        profilePic!!.visibility = View.GONE
        bSignIn!!.visibility = View.GONE
        bSignUp!!.visibility = View.GONE
        bVerSign!!.visibility = View.GONE
        EdUserName!!.visibility = View.VISIBLE
        EdUserPassword!!.visibility = View.VISIBLE
        EdUserEmail!!.visibility = View.VISIBLE
        bBack!!.visibility = View.VISIBLE
        bSignInHaveAccount!!.visibility = View.VISIBLE
        alredy_user_forgot_pass!!.visibility = View.GONE
    }

    private fun verification_true() {
        verificationTruePic!!.visibility = View.VISIBLE
        profilePic!!.visibility = View.GONE
        //
        EdUserName!!.visibility = View.GONE
        EdUserPassword!!.visibility = View.GONE
        EdUserEmail!!.visibility = View.GONE
        bSignIn!!.visibility = View.GONE
        bSignUp!!.visibility = View.GONE
        bBack!!.visibility = View.GONE
        bSignInHaveAccount!!.visibility = View.GONE
        alredy_user_forgot_pass!!.visibility = View.GONE
    }

    private fun waitVerification() {
        verificationTruePic!!.visibility = View.GONE
        profilePic!!.visibility = View.GONE
        EdUserName!!.visibility = View.VISIBLE
        EdUserPassword!!.visibility = View.VISIBLE
        EdUserEmail!!.visibility = View.VISIBLE
        bVerSign!!.visibility = View.VISIBLE
        //
        bSignIn!!.visibility = View.GONE
        bSignUp!!.visibility = View.GONE
        bBack!!.visibility = View.GONE
        bSignInHaveAccount!!.visibility = View.GONE
        forgot_pass_ver_email!!.visibility = View.GONE
        alredy_user_forgot_pass!!.visibility = View.GONE
    }

    // скрытие элементов подтверждения входа
    private fun notSigned() {
        //bSignOut.setVisibility(View.GONE);
        verificationTruePic?.visibility = View.GONE
        bBack?.visibility = View.GONE
        bSignInHaveAccount?.visibility = View.GONE
        //
        profilePic?.visibility = View.VISIBLE
        EdUserName?.visibility = View.VISIBLE
        EdUserPassword?.visibility = View.VISIBLE
        EdUserEmail?.visibility = View.VISIBLE
        bSignIn?.visibility = View.VISIBLE
        bSignUp?.visibility = View.VISIBLE
        alredy_user_forgot_pass?.visibility = View.VISIBLE
        forgot_pass_ver_email?.visibility = View.GONE
    }

    private fun sendEmailVer() {
        Log.d("LOG", "void SendEmail work")
        val user = FirebaseAuth.getInstance().currentUser!!
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (!EdUserEmail!!.text.toString().isEmpty() && !EdUserPassword!!.text.toString()
                        .isEmpty() && !EdUserName!!.text.toString().isEmpty()
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Сheck your Email for verification message",
                        Toast.LENGTH_SHORT
                    ).show()
                    verification_true()
                    val r = Runnable { waitVerification() }
                    val h = Handler()
                    h.postDelayed(r, 1000)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please enter UserName, Email, Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(applicationContext, "Send Email failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}