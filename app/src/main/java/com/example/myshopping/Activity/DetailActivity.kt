package com.example.myshopping.Activity

import android.accessibilityservice.GestureDescription.StrokeDescription
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myshopping.Adapter.PicAdapter
import com.example.myshopping.Adapter.SelectModelAdapter
import com.example.myshopping.Model.ItemModel
import com.example.myshopping.R
import com.example.myshopping.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemModel
    private var numberOrder=1
    private lateinit var managementCart:ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart=ManagmentCart(this)

        getBundle()
        initList()

    }

    private fun initList() {
        val modelList=ArrayList<String>()
        for (models in item.model){
            modelList.add(models)

        }

        binding.modelList.adapter=SelectModelAdapter(modelList)
        binding.modelList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val picList = ArrayList<String>()
        for (imageUrl in item.picUrl){
            picList.add(imageUrl)
        }

        Glide.with(this).load(picList[0]).into(binding.img)

        binding.picList.adapter=PicAdapter(picList){selectedImageUrl ->
            Glide.with(this).load(selectedImageUrl).into(binding.img)
        }

        binding.picList.layoutManager =LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getBundle() {
        item= intent.getParcelableExtra("object")!!

        binding.titletxt.text=item.title
        binding.descriptiontxt.text =item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingtxt.text="${item.rating}Rating"
        binding.addToCartBtn.setOnClickListener{
            item.numInCart=numberOrder
            managementCart.insertFood(item)
        }
        binding.backButton.setOnClickListener{finish()}
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }

    }
}