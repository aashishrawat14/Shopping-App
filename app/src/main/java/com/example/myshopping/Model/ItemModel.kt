package com.example.myshopping.Model

import android.os.Parcel
import android.os.Parcelable

data class ItemModel(
    var title:String="" ,
    var description:String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var model:ArrayList<String> = ArrayList(),
    var price:Double = 0.0,
    var rating:Double = 0.0,
    var numInCart:Int = 0,
    var showRecommended:Boolean = false,
    var categoryId:String = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeStringList(picUrl)
        parcel.writeStringList(model)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeInt(numInCart)
        parcel.writeByte(if (showRecommended) 1 else 0)
        parcel.writeString(categoryId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemModel> {
        override fun createFromParcel(parcel: Parcel): ItemModel {
            return ItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemModel?> {
            return arrayOfNulls(size)
        }
    }
}
