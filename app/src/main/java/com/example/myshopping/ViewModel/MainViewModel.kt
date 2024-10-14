package com.example.myshopping.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myshopping.Model.CategoryModel
import com.example.myshopping.Model.ItemModel
import com.example.myshopping.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.getValue
import java.sql.Ref

class MainViewModel():ViewModel() {

    private val firebasedatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _recommended = MutableLiveData<MutableList<ItemModel>>()

    val banner :LiveData<List<SliderModel>> = _banner
    val categories :LiveData<MutableList<CategoryModel>> = _category
    val recommended : MutableLiveData<MutableList<ItemModel>> = _recommended

    fun loadfiltered(id:String){
        val ref = firebasedatabase.getReference("Items")
        val query:Query = ref.orderByChild("categoryId").equalTo(id)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (childsnapshot in snapshot.children){
                    val list = childsnapshot.getValue(ItemModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _recommended.value =lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }

    fun loadRecommended(){
        val ref = firebasedatabase.getReference("Items")
        val query:Query = ref.orderByChild("showRecommended").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (childsnapshot in snapshot.children){
                    val list = childsnapshot.getValue(ItemModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _recommended.value =lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }

    fun loadCategory()
    {
        val ref = firebasedatabase.getReference("Category")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childsnapshot in snapshot.children){
                    val list = childsnapshot.getValue(CategoryModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _category.value =lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadBanner(){
        val ref = firebasedatabase.getReference("Banner")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childsnapshot in snapshot.children){
                    val list = childsnapshot.getValue(SliderModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _banner.value =lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}