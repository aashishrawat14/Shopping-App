package com.example.myshopping.Adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler


import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.Activity.ListItemsActivity
import com.example.myshopping.Model.CategoryModel
import com.example.myshopping.R
import com.example.myshopping.databinding.ViewholderCategoryBinding


class CategoryAdapter(val item:MutableList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.Viewholder>(){

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class Viewholder(val binding:ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = item[position]
        holder.binding.titletxt.text= item.title

        Glide.with(holder.itemView.context).load(item.pickUrl).into(holder.binding.pic)

        if (selectedPosition==position){
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.green_button_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(
                    ContextCompat.
                    getColor(holder.itemView.context,R.color.white ))
            )
            holder.binding.titletxt.visibility=View.VISIBLE
            holder.binding.titletxt.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.white))


        }else{
            holder.binding.pic.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black ))
            )
            holder.binding.titletxt.visibility=View.GONE
            holder.binding.titletxt.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.black))
        }
        holder.binding.root.setOnClickListener{
            val position = position
            if (position!= RecyclerView.NO_POSITION){
                lastSelectedPosition = selectedPosition
                selectedPosition=position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(holder.itemView.context,ListItemsActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            },1000)
        }
    }



    override fun getItemCount(): Int  = item.size
}