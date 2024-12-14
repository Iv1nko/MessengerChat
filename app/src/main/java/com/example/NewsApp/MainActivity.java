package com.example.NewsApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private static int SIGN_IN_CODE = 1;
    private RelativeLayout activity_main;
    private RelativeLayout first_page_chat;
    private RelativeLayout nav_menu;
    //Позволяет адаптировать данные из firebase в проект android studio
    private FirebaseListAdapter<Message_list> adapter;
    //Реализуем кнопку
    private Button sendBtn;
    Button signOutFromAccount;
    //Максимум вводимых символов
    private static final int max_enter_messege_leght = 150;
    //Для картинки
    //private ImageView mess_image;
    //////
    private ListView listView;

    private List<String> listData;
    private List<User> listTemp;
    //////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()) {
            if (requestCode == SIGN_IN_CODE) {
                if (resultCode == RESULT_OK) {
                    Snackbar.make(activity_main, "You are logged in", Snackbar.LENGTH_LONG).show();
                    //displayAllMesseges();
                } else {
                    Snackbar.make(activity_main, "You are not logged in", Snackbar.LENGTH_LONG).show();
                    finish();
                }
            }
        }
        else{
            Snackbar.make(activity_main, "Error registrate", Snackbar.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Код мессенджера
        activity_main = findViewById(R.id.activity_main);
        //Находим кнопку
        sendBtn = findViewById(R.id.button_send_message);
        // Переменные под firebase
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("User messages");
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        //Назначаем слушателя кнопки
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textField = findViewById(R.id.edit_text);
                //Проверка на пустую строку
                if(textField.getText().toString().isEmpty()){
                    Snackbar.make(activity_main, "Введите сообщение", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                //Проверка на превышение количества символов
                if(textField.getText().length() > max_enter_messege_leght){
                    Snackbar.make(activity_main, "Слишком длинное сообщение", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                /*FirebaseDatabase.getInstance().getReference("User messages").push().setValue(
                        new Message_list(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail(),
                                textField.getText().toString())
                );*/
                textField.setText("");
            }
        });

        //Пользователь еще не авторизован
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        else{
            Snackbar.make(activity_main, "Вы авторизованы", Snackbar.LENGTH_LONG).show();
            //Отображение всех сообщений
            //displayAllMesseges();
        }
    }

    /*private void displayAllMesseges() {
        ListView list_of_messeges = findViewById(R.id.list_of_messeges);
        adapter = new FirebaseListAdapter<Message_list>(this, Message_list.class, R.layout.list_item_another_mess, FirebaseDatabase.getInstance().getReference("User messages")) {
            @Override
            protected void populateView(View v, Message_list model, int position) {
                TextView mess_user, mess_time;
                //ImageView mess_image;

                BubbleTextView mess_text;
                //mess_image = v.findViewById(R.id.image_in_chat);
                mess_user = v.findViewById(R.id.user_name_newmessage);
                mess_time = v.findViewById(R.id.message_time);
                mess_text = v.findViewById(R.id.your_message_text);
                mess_user.setText(model.getUserName());
                mess_text.setText(model.getTextMessage());
                mess_time.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss", model.getMessagetime()));
            }
        };
        list_of_messeges.setAdapter(adapter);
    }*/
}