package com.example.myshopping.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myshopping.Adapter.ListItemsAdapter
import com.example.myshopping.R
import com.example.myshopping.ViewModel.MainViewModel
import com.example.myshopping.databinding.ActivityDetailBinding
import com.example.myshopping.databinding.ActivityListItemsBinding

class ListItemsActivity : BaseActivity() {

    private lateinit var binding: ActivityListItemsBinding
    private val viewModel = MainViewModel()
    private var id:String = ""
    private var title:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            progressBarList.visibility = View.VISIBLE
            viewModel.recommended.observe(this@ListItemsActivity, Observer {
                viewList.layoutManager=GridLayoutManager(this@ListItemsActivity,2)
                viewList.adapter=ListItemsAdapter(it)
                progressBarList.visibility=View.GONE
            })
            viewModel.loadfiltered(id)
        }
    }

    private fun getBundle() {
        id = intent.getStringExtra("id")!!
        title= intent.getStringExtra("title")!!

        binding.categoryTxt.text = title
    }
}