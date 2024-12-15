package com.example.NewsApp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.basket_layout.buttonBuy
import kotlinx.android.synthetic.main.basket_layout.buttonBuyWithDiscount
import kotlinx.android.synthetic.main.basket_layout.list_purchase


class BasketActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_layout)
        val args = intent.extras
        val sharedPref = getSharedPreferences("Points", MODE_PRIVATE)
        val editor = sharedPref.edit()
        var price = 0
        supportActionBar?.title = "Basket"
        var points = sharedPref.getInt("Points", 0)
        if (points == 0) {
            buttonBuyWithDiscount.visibility = GONE
        }
        var discount = "0.${points}F".toFloat()

        val purchaseList = ArrayList<Purchase>()
        args?.getString("productName1")?.let {
            purchaseList.add(Purchase(it, args.getInt("counter1"), args.getInt("imagePurchase1"), 1500))
            Log.d("aaaa", it)
        }
        args?.getString("productName2")?.let {
            purchaseList.add(Purchase(it, args.getInt("counter2"), args.getInt("imagePurchase2"), 1000))
            Log.d("aaaa", it)
        }
        args?.getString("productName3")?.let {
            purchaseList.add(Purchase(it, args.getInt("counter3"), args.getInt("imagePurchase3"), 4000))
            Log.d("aaaa", it)
        }
        args?.getString("productName4")?.let {
            purchaseList.add(Purchase(it, args.getInt("counter4"), args.getInt("imagePurchase4"),900))
            Log.d("aaaa", it)
        }
        list_purchase.adapter = BasketAdapter(this, purchaseList)

        for (i in purchaseList){
            price += i.price * i.quantity
        }
        buttonBuyWithDiscount.text = "Купить со скидкой за ${String.format("%(.0f", price - price * discount)} Рублей"
        buttonBuyWithDiscount.setOnClickListener {
            if (purchaseList.size == 4) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName}, ${purchaseList.get(1).quantity} шт. ${purchaseList.get(1).productName}, ${purchaseList.get(2).quantity} шт. ${purchaseList.get(2).productName}, ${purchaseList.get(3).quantity} шт. ${purchaseList.get(3).productName} на сумму ${String.format("%(.0f", price - price * discount)} рублей")
            }
            if (purchaseList.size == 3) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName}, ${purchaseList.get(1).quantity} шт. ${purchaseList.get(1).productName}, ${purchaseList.get(2).quantity} шт. ${purchaseList.get(2).productName} на сумму ${String.format("%(.0f", price - price * discount)} рублей")
            }
            if (purchaseList.size == 2) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName}, ${purchaseList.get(1).quantity} шт. ${purchaseList.get(1).productName} на сумму ${String.format("%(.0f", price - price * discount)} рублей")
            }
            if (purchaseList.size == 1) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName} на сумму ${String.format("%(.0f", price - price * discount)} рублей")
            }
            points = 0
            editor.putInt("Points", points).commit()
        }



        buttonBuy.text = "Купить за $price Рублей"
        buttonBuy.setOnClickListener {
            if (purchaseList.size == 4) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName}, ${purchaseList.get(1).quantity} шт. ${purchaseList.get(1).productName}, ${purchaseList.get(2).quantity} шт. ${purchaseList.get(2).productName}, ${purchaseList.get(3).quantity} шт. ${purchaseList.get(3).productName} на сумму $price рублей")
            }
            if (purchaseList.size == 3) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName}, ${purchaseList.get(1).quantity} шт. ${purchaseList.get(1).productName}, ${purchaseList.get(2).quantity} шт. ${purchaseList.get(2).productName} на сумму $price рублей")
            }
            if (purchaseList.size == 2) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName}, ${purchaseList.get(1).quantity} шт. ${purchaseList.get(1).productName} на сумму $price рублей")
            }
            if (purchaseList.size == 1) {
                sendEmail("Хочу заказать одежду: ${purchaseList.get(0).quantity} шт. ${purchaseList.get(0).productName} на сумму $price рублей")
            }
        }
    }

    fun sendEmail(text: String?) {
        val i = Intent(Intent.ACTION_SENDTO )
        i.data = Uri.parse("mailto:")
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("khusnetdinov2021@bk.ru"))
        i.putExtra(Intent.EXTRA_SUBJECT, "Покупка вещей")
        i.putExtra(Intent.EXTRA_TEXT, text)
        try {
            startActivity(Intent.createChooser(i, "Send Email"))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}

data class Purchase(val productName: String, var quantity: Int, val image: Int, val price: Int)

class BasketAdapter(val context: Context, val productsList: ArrayList<Purchase>): BaseAdapter() {
    override fun getCount(): Int {
        return productsList.size
    }

    override fun getItem(position: Int): Any {
        return productsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView
        if (myConvertView == null) {
            myConvertView = LayoutInflater.from(context).inflate(R.layout.basket_item, parent, false)
        }
        val textPurchase = myConvertView?.findViewById<TextView>(R.id.textPurchase)
        val textCountOfPurchase = myConvertView?.findViewById<TextView>(R.id.textCountOfPurchase)
        val imagePurchase = myConvertView?.findViewById<ImageView>(R.id.imagePurchase)

//        val ibBasket = myConvertView?.findViewById<ImageButton>(R.id.ibBasket)
//        ibBasket?.setOnClickListener {
//            productsList.removeAt(position)
//            notifyDataSetChanged()
//            if (productsList.size == 0) {
//                val i = Intent(myConvertView?.context, store_activity::class.java)
//                myConvertView?.context?.startActivity(i)
//            }
//        }

        textPurchase?.text = productsList[position].productName
        textCountOfPurchase?.text = "${productsList[position].quantity} Шт."
        imagePurchase?.setImageResource(productsList[position].image)

        return myConvertView!!
    }

}