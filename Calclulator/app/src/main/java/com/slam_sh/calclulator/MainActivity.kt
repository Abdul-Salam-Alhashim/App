package com.slam_sh.calclulator

//import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.buttons.*
import kotlinx.android.synthetic.main.input_layout.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Numbers
        btu0.setOnClickListener { appendOnCilck(true,"0") }
        btu1.setOnClickListener { appendOnCilck(true,"1") }
        btu2.setOnClickListener { appendOnCilck(true,"2") }
        btu3.setOnClickListener { appendOnCilck(true,"3") }
        btu4.setOnClickListener { appendOnCilck(true,"4") }
        btu5.setOnClickListener { appendOnCilck(true,"5") }
        btu6.setOnClickListener { appendOnCilck(true,"6") }
        btu7.setOnClickListener { appendOnCilck(true,"7") }
        btu8.setOnClickListener { appendOnCilck(true,"8") }
        btu9.setOnClickListener { appendOnCilck(true,"9") }
        btuDot.setOnClickListener { appendOnCilck(true,".") }
        //Operators
        btuPlus.setOnClickListener { appendOnCilck(true,"+") }
        btuMinus.setOnClickListener { appendOnCilck(true,"-") }
        btuMul.setOnClickListener { appendOnCilck(true,"*") }
        btuDiv.setOnClickListener { appendOnCilck(true,"/") }
        btuOpen.setOnClickListener { appendOnCilck(true,"(") }
        btuClose.setOnClickListener { appendOnCilck(true,")") }


        btuClear.setOnClickListener {
            clear()
        }

        btuEquals.setOnClickListener {
            calculate()
        }
        btuBack.setOnClickListener {
            var string= tvInput.text.toString()
            if (string.isNotEmpty()){
                tvInput.text=string.substring(0,string.length-1)
            }
            tvOutput.text=""
        }
    }




    //now create methods
      private  fun  appendOnCilck(clear: Boolean,string: String){
        if (clear){
            tvOutput.text=""
            tvInput.append(string)
        }else{
            tvInput.append(tvInput.text)
            tvInput.append(string)
            tvOutput.text=""
        }
        }

    private fun  clear(){
            tvInput.text=""
        tvOutput.text=""

    }
    private  fun  calculate(){
        try {
            val input= ExpressionBuilder(tvInput.text.toString()).build()
            val output = input.evaluate()
            val longOutput=output.toLong()
            if (output==longOutput.toDouble()){
                tvOutput.text=longOutput.toString()
            }else{
                tvOutput.text=output.toString()
            }
        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }
}










