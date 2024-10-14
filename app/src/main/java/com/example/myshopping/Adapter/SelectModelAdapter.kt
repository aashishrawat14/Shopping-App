package com.example.myshopping.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myshopping.R
import com.example.myshopping.databinding.ViewholderModelBinding

class SelectModelAdapter(var items:MutableList<String>):RecyclerView.Adapter<SelectModelAdapter.Viewholder>() {

    private var selectedposition =-1
    private var lastSelectedPosition =-1
    private lateinit var context:Context

    inner class Viewholder (val binding:ViewholderModelBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectModelAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderModelBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SelectModelAdapter.Viewholder, position: Int) {
        holder.binding.modelTxt.text = items[position]

        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedposition
            selectedposition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedposition)
        }
        if (selectedposition==position){
            holder.binding.modelLayout.setBackgroundResource(R.drawable.green_bg_selector)
            holder.binding.modelTxt.setTextColor(context.resources.getColor(R.color.green))
        }else{
            holder.binding.modelLayout.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.modelTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int =items.size
}