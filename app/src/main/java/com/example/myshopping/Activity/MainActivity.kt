package com.example.myshopping.Activity

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.myshopping.Adapter.CategoryAdapter
import com.example.myshopping.Adapter.RecommendedAdapter
import com.example.myshopping.Adapter.SliderAdapter
import com.example.myshopping.Model.SliderModel
import com.example.myshopping.R
import com.example.myshopping.ViewModel.MainViewModel
import com.example.myshopping.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initRecommended()
        initBottomMenu()

    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
    }

    private fun initRecommended() {
        binding.progressBarRecommendation.visibility=View.VISIBLE
        viewModel.recommended.observe(this) {
            binding.viewRecommendation.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.viewRecommendation.adapter = RecommendedAdapter(it)
            binding.progressBarRecommendation.visibility = View.GONE
        }
        viewModel.loadRecommended()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.categories.observe(this) {
            binding.viewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        }
        viewModel.loadCategory()

    }


    private fun banners(image:List<SliderModel>){
        binding.viewPager2.adapter =SliderAdapter(image,binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)

        if (image.size >1 ){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }
    }

    private fun initBanner(){
        binding.progressBarSlide.visibility= View.VISIBLE
        viewModel.banner.observe(this) {
            banners(it)
            binding.progressBarSlide.visibility = View.GONE
        }
        viewModel.loadBanner()
    }
}