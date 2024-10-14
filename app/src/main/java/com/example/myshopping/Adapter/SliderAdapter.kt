package com.example.myshopping.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.myshopping.Model.SliderModel
import com.example.myshopping.databinding.SlideBarContainerBinding
import com.google.android.material.slider.Slider


class SliderAdapter(private var sliderItems:List<SliderModel>,private var viewPager2: ViewPager2):
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){
        private lateinit var context: android.content.Context
        private val runnable = Runnable {
            sliderItems = sliderItems
            notifyDataSetChanged()
        }
    class SliderViewHolder(private val binding:SlideBarContainerBinding):
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("CheckResult")
            fun setImage(sliderItems:SliderModel, context: Context){
                Glide.with(context).load(sliderItems.url).
                apply{RequestOptions().transform(CenterInside())}.into(binding.imageSlide)
            }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {
        context =parent.context
        val binding = SlideBarContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SliderViewHolder(binding)
    }



    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position],context)
        if (position ==sliderItems.lastIndex - 1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int =sliderItems.size
}