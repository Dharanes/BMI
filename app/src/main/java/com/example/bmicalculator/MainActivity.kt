package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val subBtn = findViewById<Button>(R.id.calBtn)

        subBtn.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validateString(weight, height)){
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Dig = String.format("%.2f", bmi).toFloat()
                displayText(bmi2Dig)
            }
        }
    }
    private fun validateString(n1:String?,n2:String?):Boolean{
        return when{
            n1.isNullOrEmpty() and n2.isNullOrEmpty() ->{
                Toast.makeText(this,"Enter the values",Toast.LENGTH_LONG).show()
                return false
            }
            n1.isNullOrEmpty() ->{
                Toast.makeText(this,"Enter the value of weight",Toast.LENGTH_LONG).show()
                return false
            }
            n2.isNullOrEmpty() ->{
                Toast.makeText(this,"Enter the value of height",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayText(bmi:Float){
        val res = findViewById<TextView>(R.id.tvResult)
        res.text = bmi.toString()
        var mess = ""
        var color = 0
        when{
            bmi <18.5 ->{
                mess = "You are underweight"
                color = R.color.underweight
            }
            bmi in 18.5..24.9 ->{
                mess = "You are Normal"
                color = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                mess = "You are overweight"
                color = R.color.overweight
            }
            bmi >29.99 ->{
                mess = "You are Obese"
                color = R.color.obese
            }
        }
        var des = findViewById<TextView>(R.id.tvDes)
        des.setTextColor(ContextCompat.getColor(this,color))
        des.text = mess
        var info = findViewById<TextView>(R.id.tvInfo)
        info.text = "(Normal range 18.5 - 24.9)"
        val cv = findViewById<CardView>(R.id.cvResult)
        cv.visibility = VISIBLE
    }
}