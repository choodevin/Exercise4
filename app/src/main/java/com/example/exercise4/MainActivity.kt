package com.example.exercise4

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGet.setOnClickListener{ clickDataPicker() }
        editText2.setOnClickListener { focusAndKeyboard() }
        btnCalculate.setOnClickListener { calculateUsableInvestment() }
    }
    private fun calculateUsableInvestment(){
        try{
        val age:Int = textViewAge.text.toString().toInt()
        var basic:Int = 0
        val currentSavings:Int = editText2.text.toString().toInt()
        var amountUsable:Double
        when(age){
            in 16..20 -> basic = 5000
            in 21..25 -> basic = 14000
            in 26..30 -> basic = 29000
            in 31..35 -> basic = 50000
            in 36..40 -> basic = 78000
            in 41..45 -> basic = 116000
            in 46..50 -> basic = 165000
            in 51..55 -> basic = 228000
        }
        amountUsable = (currentSavings - basic) * 0.3
        if(amountUsable < 0){
            amountUsable = 0.0
        }

        textViewResult.text = String.format("Age : %d \n" +
                "Basic Savings: RM%d \n" +
                "Current Savings: RM%d \n" +
                "Usable savings: RM%.2f",age,basic,currentSavings,amountUsable)

    }catch(ex:Exception){
            Toast.makeText(this,"Input error",Toast.LENGTH_LONG).show()
        }
    }

    private fun focusAndKeyboard(){
        editText2.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, HIDE_IMPLICIT_ONLY)
    }

    private fun clickDataPicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            Toast.makeText(this, """$dayOfMonth - ${monthOfYear + 1} - $year""", Toast.LENGTH_LONG).show()
            textViewBirthDate.text = """$dayOfMonth - ${monthOfYear + 1} - $year"""
            textViewAge.text = String.format("%s", c.get(Calendar.YEAR) - year)
        }, year, month, day)
        dpd.show()

    }
}
