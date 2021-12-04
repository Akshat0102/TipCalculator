package com.example.tipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding     //lateinit ensures initializing a variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)                   //root comes top and connects everything

        binding.calculateBtn.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {

        val cost = binding.costOfService.text.toString().toDoubleOrNull()

        if (cost == null) {
            val popUp =               //the Toast generates a pop-up message when button is clicked
                Toast.makeText(this, "Cost of Service can't be empty", Toast.LENGTH_SHORT).show()
            return
        }
        else if (cost == 0.0){
            val popUp =               //the Toast generates a pop-up message when button is clicked
                Toast.makeText(this, "Cost of Service can't be 0", Toast.LENGTH_SHORT).show()
            return
        }

        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {

            R.id.option_amazing -> 0.20
            R.id.option_good -> 0.18
            R.id.option_okay -> 0.15
            else -> 0.08
        }

        var tip = tipPercent * cost
        if (binding.tipRoundUp.isChecked) tip = ceil(tip)

        val formatTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.resultTip.text = getString(R.string.tip_amount, formatTip)
    }
}