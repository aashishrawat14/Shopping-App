package com.example.myshopping.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.Model.ItemModel
import com.example.myshopping.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

class CartAdapter(private var listItemSelected:ArrayList<ItemModel>,context:Context,var changeNumberItemsListener: ChangeNumberItemsListener):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(val binding:ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)

    private val managementCart= ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {

        val binding= ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachTime.text="$${item.showRecommended}"
        holder.binding.totalEachItem.text="$${Math.round(item.numInCart+item.price)}"
        holder.binding.numberItemTxt.text = item.numInCart.toString()

        Glide.with(holder.itemView.context).load(item.picUrl[0]).into(holder.binding.pic)

        holder.binding.plusCartBtn.setOnClickListener{
            managementCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }

            })
        }

        holder.binding.plusCartBtn.setOnClickListener{
            managementCart.minusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int=listItemSelected.size
}