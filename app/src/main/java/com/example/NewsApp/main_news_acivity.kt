package com.example.NewsApp.com.example.NewsApp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.NewsApp.LatestMessageActivity
import com.example.NewsApp.R
import com.squareup.picasso.Picasso

class main_news_activity : AppCompatActivity() {
    companion object{
        val NEWS_NAME = "NEWS_NAME"
        val NEWS_ID = "NEWS_ID"
        val NEWS_IMAGE = "NEWS_IMAGE"
        val NEWS_TEXT = "NEWS_TEXT"
        val NEWS_DATE = "NEWS_DATE"
        val NEWS_AUTHOR = "NEWS_AUTHOR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_news_layout)

        val news_name = intent.getStringExtra(LatestMessageActivity.NEWS_NAME)
        val news_id = intent.getStringExtra(LatestMessageActivity.NEWS_ID)
        val news_image = intent.getStringExtra(LatestMessageActivity.NEWS_IMAGE)
        val news_text = intent.getStringExtra(LatestMessageActivity.NEWS_TEXT)
        val news_date = intent.getStringExtra(LatestMessageActivity.NEWS_DATE)
        val news_author = intent.getStringExtra(LatestMessageActivity.NEWS_AUTHOR)

        val name_news = findViewById<TextView>(R.id.full_label_news_info).setText(news_name)
        val date = findViewById<TextView>(R.id.full_date_news).setText(news_date)
        val full_news = findViewById<TextView>(R.id.full_news_info).setText(news_text)
        val image_news = findViewById<ImageView>(R.id.full_main_image_news)
        Picasso.get()
            .load(news_image)
            .fit()
            .centerCrop()
            .into(image_news)
    }
}


