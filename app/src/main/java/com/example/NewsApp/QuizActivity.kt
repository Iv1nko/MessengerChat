package com.example.NewsApp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_quiz.buttonCheck
import kotlinx.android.synthetic.main.activity_quiz.buttonNextQuestion
import kotlinx.android.synthetic.main.activity_quiz.imageQuestion
import kotlinx.android.synthetic.main.activity_quiz.rb1
import kotlinx.android.synthetic.main.activity_quiz.rb2
import kotlinx.android.synthetic.main.activity_quiz.rb3
import kotlinx.android.synthetic.main.activity_quiz.rb4
import kotlinx.android.synthetic.main.activity_quiz.textPoints
import kotlinx.android.synthetic.main.activity_quiz.textQuestion
import java.util.Calendar


class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        supportActionBar!!.title = "Quiz"
        val sharedPref: SharedPreferences = getSharedPreferences("Points", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        var points = sharedPref.getInt("Points", 0)
        var timer = sharedPref.getLong("timer", 0)
        var counter = 1


        imageQuestion.setImageResource(R.drawable.quiz1)
        textQuestion.text = getString(R.string.question1)
        rb1.text = "Adidas"
        rb2.text = "Reebok"
        rb3.text = "Uhlsport"
        rb4.text = "Umbro"


        buttonCheck.setOnClickListener {
            when (counter) {
                1 -> {
                    if (Calendar.getInstance().timeInMillis - timer > 86400000) {
                        if (rb2.isChecked) {
                            buttonNextQuestion.visibility = VISIBLE
                            buttonCheck.visibility = INVISIBLE
                            if (points + 5 > 25) {
                                Toast.makeText(
                                    this,
                                    "Вы достигли максимальной скидки",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                textPoints.visibility = VISIBLE
                                Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show()
                                points += 5
                                editor.putInt("Points", points).commit()
                                Log.d("aaaa", points.toString())
                            }

                        } else {
                            timer = Calendar.getInstance().timeInMillis
                            editor.putLong("timer", timer)
                            Toast.makeText(this, "Неверный ответ", Toast.LENGTH_SHORT).show()
                        }
                    } else Toast.makeText(
                        this,
                        "Следующая попытка будет доступна через 24 часа",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                2 -> {
                    if (Calendar.getInstance().timeInMillis - timer > 86400000) {
                        if (rb3.isChecked) {
                            buttonNextQuestion.visibility = VISIBLE
                            buttonCheck.visibility = INVISIBLE
                            if (points + 5 > 25) {
                                Toast.makeText(
                                    this,
                                    "Вы достигли максимальной скидки",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                textPoints.visibility = VISIBLE
                                Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show()
                                points += 5
                                editor.putInt("Points", points).commit()
                                Log.d("aaaa", points.toString())
                            }

                        } else {
                            timer = Calendar.getInstance().timeInMillis
                            editor.putLong("timer", timer)
                            Toast.makeText(this, "Неверный ответ", Toast.LENGTH_SHORT).show()
                        }
                    } else Toast.makeText(
                        this,
                        "Следующая попытка будет доступна через 24 часа",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                3 -> {
                    if (Calendar.getInstance().timeInMillis - timer > 86400000) {
                        if (rb1.isChecked) {
                            buttonNextQuestion.visibility = VISIBLE
                            buttonCheck.visibility = INVISIBLE
                            if (points + 5 > 25) {
                                Toast.makeText(
                                    this,
                                    "Вы достигли максимальной скидки",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                textPoints.visibility = VISIBLE
                                Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show()
                                points += 5
                                timer = Calendar.getInstance().timeInMillis
                                editor.putLong("timer", timer)
                                editor.putInt("Points", points).commit()
                                Log.d("aaaa", points.toString())
                            }

                        } else {
                            timer = Calendar.getInstance().timeInMillis
                            editor.putLong("timer", timer)
                            Toast.makeText(this, "Неверный ответ", Toast.LENGTH_SHORT).show()
                        }
                    } else Toast.makeText(
                        this,
                        "Следующая попытка будет доступна через 24 часа",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }



            buttonNextQuestion.setOnClickListener {
                if (counter == 3) {
                    counter = 1
                } else counter++
                buttonNextQuestion.visibility = INVISIBLE
                buttonCheck.visibility = VISIBLE
                textPoints.visibility = INVISIBLE
                when (counter) {
                    1 -> {
                        imageQuestion.setImageResource(R.drawable.quiz1)
                        textQuestion.text = getString(R.string.question1)
                        rb1.text = "Adidas"
                        rb2.text = "Reebok"
//               *
                        rb3.text = "Uhlsport"
                        rb4.text = "Umbro"
                    }

                    2 -> {
                        imageQuestion.setImageResource(R.drawable.quiz2)
                        textQuestion.text = getString(R.string.question2)
                        rb1.text = "В 1998 году"
                        rb2.text = "В 2000 году"
                        rb3.text = "В 2001 году"
//               *
                        rb4.text = "В 2003 году"
                    }

                    3 -> {
                        imageQuestion.setImageResource(R.drawable.quiz3)
                        textQuestion.text = getString(R.string.question3)
                        rb1.text = "Гёкдениз Карадениз"
                        //               *
                        rb2.text = "Александр Рязанцев"
                        rb3.text = "Кристиан Нобоа"
                        rb4.text = "Александро Домингес"
                    }
                }
            }

        }


//    private fun fetchUsers(searchText: String) {
//        val adapter = GroupAdapter<GroupieViewHolder>()
//        val recycler_new_mess = findViewById<RecyclerView>(R.id.recycler_newmessage)
//        if (searchText.isEmpty()) {
//            adapter.clear()
//            recycler_new_mess.adapter = adapter
//        } else {
//            val ref = FirebaseDatabase.getInstance().getReference("/news")
//            val firebaseSearchQuery = ref.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff")
//            firebaseSearchQuery.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(p0: DataSnapshot) {
//                    p0.children.forEach {
//                        val user = it.getValue(User_list::class.java)
//                        if (user != null) {
//                            adapter.add(UserItem(user))
//                        }
//                    }
//                    adapter.setOnItemClickListener { item, view ->
//                        val userItem = item as NewsItem
//                        val intent = Intent(view.context, main_news_activity::class.java)
//                        intent.putExtra(USER_KEY, userItem.user.displayname)
////                        intent.putExtra(USER_ID, userItem.user.id)
//                        intent.putExtra(USER_IMAGE, userItem.user.image_id)
//                        startActivity(intent)
//                    }
//                    recycler_new_mess.adapter = adapter
//                    val recycler = recycler_new_mess.adapter
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                }
//            })
//        }
//    }
    }

    class UserItem(val user: User_list) : Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            //имя пользователя
            viewHolder.itemView.findViewById<TextView>(R.id.user_name_newmessage).text = user.name
            val image = viewHolder.itemView.findViewById<ImageView>(R.id.image_user_newmessage)
            //Картинка пользователя
            Picasso.get()
                .load(user.image_id)
                .fit()
                .centerCrop()
                .into(image)
        }

        override fun getLayout(): Int {
            return R.layout.list_item_user
        }
    }

    class NewsItem(val user: ChatLogActivity.News) : Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            //имя пользователя
            viewHolder.itemView.findViewById<TextView>(R.id.listenForLatestNews).text =
                user.displayname
            viewHolder.itemView.findViewById<TextView>(R.id.discriptionNews).text = user.description
            viewHolder.itemView.findViewById<ImageView>(R.id.image_news)
                .setImageResource(user.image_id)


//        viewHolder.itemView.findViewById<TextView>(R.id.datenewsDescription).text = user.newsdate
//        val image = viewHolder.itemView.findViewById<ImageView>(R.id.image_news)
            //Картинка пользователя
//        Picasso.get()
//            .load(user.image_id)
//            .fit()
//            .centerCrop()
//            .into(image)
        }

        override fun getLayout(): Int {
            return R.layout.list_item_news
        }
    }

    class CubItem(val user: ChatLogActivity.Cubs) : Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            //имя пользователя
            viewHolder.itemView.findViewById<TextView>(R.id.nameCub).text = user.name
            viewHolder.itemView.findViewById<CircleImageView>(R.id.image_cub)
                .setImageResource(user.image_cub)
            //Картинка пользователя
//        Picasso.get()
//            .load(user.image_cub)
//            .fit()
//            .centerCrop()
//            .into(image)
        }

        override fun getLayout(): Int {
            return R.layout.list_item_cub
        }
    }

    class DateItem(private val date: ChatLogActivity.Date) : Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            //имя пользователя
            viewHolder.itemView.findViewById<TextView>(R.id.date_id).text = date.id
            viewHolder.itemView.findViewById<TextView>(R.id.date_text_description).text =
                date.description
            viewHolder.itemView.findViewById<ImageView>(R.id.image_date)
                .setImageResource(date.image_date)

//        val image = viewHolder.itemView.findViewById<ImageView>(R.id.image_date)
            //Картинка пользователя
//        if (date.image_date != ""){
//            Picasso.get()
//                .load(date.image_date)
//                .fit()
//                .centerCrop()
//                .into(image)
//        }
            //Log.d("listenForLatestCubs","DateItem = " + date.id + " : " + date.description + " : " + date.image_date)
        }

        override fun getLayout(): Int {
            return R.layout.list_date
        }
    }

    class TeamItem(private val date: ChatLogActivity.Team) : Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            //имя пользователя
            viewHolder.itemView.findViewById<TextView>(R.id.team_first_label).text = date.number
            viewHolder.itemView.findViewById<TextView>(R.id.team_second_label).text = date.name
            viewHolder.itemView.findViewById<TextView>(R.id.team_thirty_label).text = date.position
            viewHolder.itemView.findViewById<CircleImageView>(R.id.team_image)
                .setImageResource(date.image)

            val image = viewHolder.itemView.findViewById<ImageView>(R.id.team_image)
            //Картинка пользователя
//        if (date.image_date != ""){
//            Picasso.get()
//                .load(date.image_date)
//                .fit()
//                .centerCrop()
//                .into(image)
//        }
            //Log.d("listenForLatestCubs","DateItem = " + date.id + " : " + date.description + " : " + date.image_date)
        }

        override fun getLayout(): Int {
            return R.layout.list_item_team
        }
    }