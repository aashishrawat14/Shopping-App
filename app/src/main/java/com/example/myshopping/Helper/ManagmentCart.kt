package com.example.project1762.Helper

import android.content.Context
import android.widget.Toast
import com.example.myshopping.Helper.TinyDB
import com.example.myshopping.Model.ItemModel


class ManagmentCart(val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertFood(item: ItemModel) {
        var listFood = getListCart()
        val existAlready = listFood.any { it.title == item.title }
        val index = listFood.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listFood[index].numInCart = item.numInCart
        } else {
            listFood.add(item)
        }
        tinyDB.putListObject("CartList", listFood)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<ItemModel> {
        return tinyDB.getListObject("CartList") ?: arrayListOf()
    }

    fun minusItem(listFood: ArrayList<ItemModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (listFood[position].numInCart == 1) {
            listFood.removeAt(position)
        } else {
            listFood[position].numInCart--
        }
        tinyDB.putListObject("CartList", listFood)
        listener.onChanged()
    }

    fun plusItem(listFood: ArrayList<ItemModel>, position: Int, listener: ChangeNumberItemsListener) {
        listFood[position].numInCart++
        tinyDB.putListObject("CartList", listFood)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val listFood = getListCart()
        var fee = 0.0
        for (item in listFood) {
            fee += item.price * item.numInCart
        }
        return fee
    }
}