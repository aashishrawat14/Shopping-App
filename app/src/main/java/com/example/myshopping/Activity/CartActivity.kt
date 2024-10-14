package com.example.myshopping.Activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshopping.Adapter.CartAdapter
import com.example.myshopping.R
import com.example.myshopping.databinding.ActivityCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

class CartActivity : BaseActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var managementCart :ManagmentCart
    private var tax:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculatorCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        binding.viewCart.adapter = CartAdapter(managementCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculatorCart()
            }

        })
        with(binding){
            emptyTxt.visibility =
                if (managementCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView3.visibility =
                if (managementCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setVariable() {
        binding.apply {
            backButton.setOnClickListener{
                finish()
            }

            method1.setOnClickListener{
                method1.setBackgroundResource(R.drawable.green_bg_selector)
                methodIc1.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.green))
                methodTItle1.setTextColor(resources.getColor(R.color.green))
                methodSubTitle1.setTextColor(resources.getColor(R.color.green))

                method2.setBackgroundResource(R.drawable.grey_bg_selector)
                methodIc2.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.black))
                methodTItle2.setTextColor(resources.getColor(R.color.black))
                methodSubTitle2.setTextColor(resources.getColor(R.color.grey))
            }

            method2.setOnClickListener{
                method2.setBackgroundResource(R.drawable.green_bg_selector)
                methodIc2.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.green))
                methodTItle2.setTextColor(resources.getColor(R.color.green))
                methodSubTitle2.setTextColor(resources.getColor(R.color.green))

                method1.setBackgroundResource(R.drawable.grey_bg_selector)
                methodIc1.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity,R.color.black))
                methodTItle1.setTextColor(resources.getColor(R.color.black))
                methodSubTitle1.setTextColor(resources.getColor(R.color.grey))
            }


        }
    }

    private fun calculatorCart(){
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managementCart.getTotalFee()+percentTax)*100)/100.0

        val total = Math.round((managementCart.getTotalFee() + tax + delivery)*100)/100
        val itemTotal = Math.round(managementCart.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text = "$${itemTotal}"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }
}