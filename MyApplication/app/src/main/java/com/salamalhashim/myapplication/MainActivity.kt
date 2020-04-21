package com.salamalhashim.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Way 1
 /* buGetAge.setOnClickListener({
    val yearofBirth:Int=etUserYareOfBirth.text.toString().toInt()
      val currentYear=Calendar.getInstance().get(Calendar.YEAR)
      val age=currentYear-yearofBirth
      if (age<0){
      tvShwoAge.text="Your number is false"}
      else{
          tvShwoAge.text="Your age is $age years"
      }
  })*/
    }
    // Way 2
    /* fun buClickEvent(view:View){
        val yearofBirth:Int=etUserYareOfBirth.text.toString().toInt()
        val currentYear=Calendar.getInstance().get(Calendar.YEAR)
        val age=currentYear-yearofBirth
        if (age<0){
            tvShwoAge.text="Your number is false"}
        else{
            tvShwoAge.text="Your age is $age years"
        }*/
    }

