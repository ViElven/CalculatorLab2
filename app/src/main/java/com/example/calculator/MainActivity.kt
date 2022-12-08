package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        null_b.setOnClickListener{enterValF("0")}
        one_b.setOnClickListener{enterValF("1")}
        two_b.setOnClickListener{enterValF("2")}
        three_b.setOnClickListener{enterValF("3")}
        four_b.setOnClickListener{enterValF("4")}
        five_b.setOnClickListener{enterValF("5")}
        six_b.setOnClickListener{enterValF("6")}
        seven_b.setOnClickListener{enterValF("7")}
        eight_b.setOnClickListener{enterValF("8")}
        nine_b.setOnClickListener{enterValF("9")}
        minus_b.setOnClickListener{enterValF("-")}
        plus_b.setOnClickListener{enterValF("+")}
        division_b.setOnClickListener{enterValF("/")}
        mult_b.setOnClickListener{enterValF("*")}
        ls_b.setOnClickListener{enterValF(")")}
        rs_b.setOnClickListener{enterValF("(")}
        dot_b.setOnClickListener{enterValF(".")}


        AllClear_b.setOnClickListener{
            math_op.text=""
            math_input.text=""
        }

        ClearOne_b.setOnClickListener{
            val str = math_op.text.toString()
            if(str.isNotEmpty()){
                math_op.text = str.substring(0, str.length-1)
                math_input.text=""

            }
        }

        equal_b.setOnClickListener{
            try {
                val ex = ExpressionBuilder(math_op.text.toString()).build()
                val result = ex.evaluate()

                val fractionres = result.toLong()
                if(result==fractionres.toDouble())
                    math_input.text = fractionres.toString()
                else
                    math_input.text = result.toString()

            } catch (e:Exception){
                Log.d("Помилка", "${e.message}")

            }
        }

    }

    var count = ""

    fun enterValF(str: String){
        if( math_input.text != ""){
            math_op.text = math_input.text
            math_input.text = ""
        }
        math_op.append(str)
    }


    fun tratsition(view: View){
        val transIntent = Intent(this, SecondActivity::class.java)
        val countString = math_input.text.toString()
        if (countString!=""){
        count = countString}
        else {count = "Немає відповіді"}
        transIntent.putExtra(SecondActivity.total_count, count)
        startActivity(transIntent)
    }
}