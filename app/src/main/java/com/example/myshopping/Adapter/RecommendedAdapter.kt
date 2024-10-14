package com.example.myshopping.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.Activity.DetailActivity
import com.example.myshopping.Model.ItemModel
import com.example.myshopping.databinding.ViewholderRecommendationBinding

class RecommendedAdapter(val items:MutableList<ItemModel>):RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {
    class ViewHolder(var binding:ViewholderRecommendationBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedAdapter.ViewHolder {
        val binding = ViewholderRecommendationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedAdapter.ViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding){
            titletxt.text=item.title
            pricetxt.text = "$${item.price}"
            ratingtxt.text=item.rating.toString()

            Glide.with(holder.itemView.context).
            load(item.picUrl[0]).
            into(pic)

            root.setOnClickListener{
                val intent = Intent(holder.itemView.context,DetailActivity::class.java).apply {
                    putExtra("object",item)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}