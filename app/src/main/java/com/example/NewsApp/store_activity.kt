package com.example.NewsApp.com.example.NewsApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.NewsApp.BasketActivity
import com.example.NewsApp.R
import com.mikepenz.materialdrawer.util.ifNotNull
import kotlinx.android.synthetic.main.store_activity_layout.buttonGoToBasket
import kotlinx.android.synthetic.main.store_activity_layout.ibAccs
import kotlinx.android.synthetic.main.store_activity_layout.ibShirts
import kotlinx.android.synthetic.main.store_activity_layout.imageView1
import kotlinx.android.synthetic.main.store_activity_layout.imageView2
import kotlinx.android.synthetic.main.store_activity_layout.imageView3
import kotlinx.android.synthetic.main.store_activity_layout.imageView4
import kotlinx.android.synthetic.main.store_activity_layout.imageMinus
import kotlinx.android.synthetic.main.store_activity_layout.imageMinus2
import kotlinx.android.synthetic.main.store_activity_layout.imageMinus3
import kotlinx.android.synthetic.main.store_activity_layout.imageMinus4
import kotlinx.android.synthetic.main.store_activity_layout.imagePlus
import kotlinx.android.synthetic.main.store_activity_layout.imagePlus2
import kotlinx.android.synthetic.main.store_activity_layout.imagePlus3
import kotlinx.android.synthetic.main.store_activity_layout.imagePlus4
import kotlinx.android.synthetic.main.store_activity_layout.textAmountOfPoints
import kotlinx.android.synthetic.main.store_activity_layout.textView0
import kotlinx.android.synthetic.main.store_activity_layout.textView10
import kotlinx.android.synthetic.main.store_activity_layout.textView11
import kotlinx.android.synthetic.main.store_activity_layout.textView12
import kotlinx.android.synthetic.main.store_activity_layout.textView17
import kotlinx.android.synthetic.main.store_activity_layout.textView18
import kotlinx.android.synthetic.main.store_activity_layout.textView19
import kotlinx.android.synthetic.main.store_activity_layout.textView20

class store_activity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_activity_layout)
        supportActionBar?.title = "Store"
        var Points = getSharedPreferences("Points", MODE_PRIVATE).getInt("Points", 0)
        textAmountOfPoints.text = "Ваша скидка: $Points%"
        Log.d("aaaa", Points.toString())

        var switcher = true
        var counter1 = 0
        var counter2 = 0
        var counter3 = 0
        var counter4 = 0


        ibShirts.setOnClickListener{
            switcher = true
            imageView1.background = getDrawable(R.drawable.tshirt1)
            imageView2.background = getDrawable(R.drawable.tshirt2)
            imageView3.background = getDrawable(R.drawable.tshirt3)
            imageView4.background = getDrawable(R.drawable.tshirt4)
            textView0.text = getString(R.string.shirt1)
            textView10.text = getString(R.string.shirt2)
            textView11.text = getString(R.string.shirt3)
            textView12.text = getString(R.string.shirt4)
        }
        ibAccs.setOnClickListener{
            switcher = false
            imageView1.background = getDrawable(R.drawable.accs1)
            imageView2.background = getDrawable(R.drawable.accs2)
            imageView3.background = getDrawable(R.drawable.accs3)
            imageView4.background = getDrawable(R.drawable.accs4)
            textView0.text = getString(R.string.saccs1)
            textView10.text = getString(R.string.saccs2)
            textView11.text = getString(R.string.saccs3)
            textView12.text = getString(R.string.saccs4)
        }

        imageMinus.setOnClickListener{
            if (counter1 != 0) {
                counter1--
                textView17.text = counter1.toString()
            }
        }
        imageMinus2.setOnClickListener{
            if (counter2 != 0) {
                counter2--
                textView18.text = counter2.toString()
            }
        }
        imageMinus3.setOnClickListener{
            if (counter1 != 0) {
                counter1--
                textView19.text = counter3.toString()
            }
        }
        imageMinus4.setOnClickListener{
            if (counter1 != 0) {
                counter1--
                textView20.text = counter4.toString()
            }
        }

        imagePlus.setOnClickListener{
                counter1++
                textView17.text = counter1.toString()
        }
        imagePlus2.setOnClickListener{
                counter2++
                textView18.text = counter2.toString()
        }
        imagePlus3.setOnClickListener{
                counter3++
                textView19.text = counter3.toString()
        }
        imagePlus4.setOnClickListener{
                counter4++
                textView20.text = counter4.toString()
        }


        buttonGoToBasket.setOnClickListener {
           if(counter1 == 0 && counter2 == 0 && counter3 == 0 && counter4 == 0){
               Toast.makeText(this, "Добавьте товар в корзину", Toast.LENGTH_SHORT).show()
           } else {
               val i = Intent(applicationContext, BasketActivity::class.java)
               if (counter1 != 0) {
                   i.putExtra("productName1", textView0.text.toString())
                   i.putExtra("counter1", counter1)
                   if (switcher) {
                       i.putExtra("imagePurchase1", R.drawable.tshirt1)
                   } else {
                       i.putExtra("imagePurchase1", R.drawable.accs1)
                   }
               }
               if (counter2 != 0) {
                   i.putExtra("productName2", textView10.text.toString())
                   i.putExtra("counter2", counter2)
                   if (switcher) {
                       i.putExtra("imagePurchase2", R.drawable.tshirt2)
                   } else {
                       i.putExtra("imagePurchase2", R.drawable.accs2)
                   }
               }
               if (counter3 != 0) {
                   i.putExtra("productName3", textView11.text.toString())
                   i.putExtra("counter3", counter3)
                   if (switcher) {
                       i.putExtra("imagePurchase3", R.drawable.tshirt3)
                   } else {
                       i.putExtra("imagePurchase3", R.drawable.accs3)
                   }
               }
               if (counter4 != 0) {
                   i.putExtra("productName4", textView12.text.toString())
                   i.putExtra("counter4", counter4)
                   if (switcher) {
                       i.putExtra("imagePurchase4", R.drawable.tshirt4)
                   } else {
                       i.putExtra("imagePurchase4", R.drawable.accs4)
                   }
               }
               startActivity(i)
           }
        }
    }
}