package com.example.appsample

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        var NONE = "None"
        var ODD_NUMBERS = "Odd Numbers"
        var EVEN_NUMBERS = "Even Numbers"
        var PRIME_NUMBERS = "Prime Numbers"
        var FIBONACCI_SEQUENCE_NUMBERS = "Fibonacci Sequence Numbers"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val numbers = (1..100).toList()
        val adapter = GridAdapter(numbers, setOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 10)

        val rules =
            listOf(NONE, ODD_NUMBERS, EVEN_NUMBERS, PRIME_NUMBERS, FIBONACCI_SEQUENCE_NUMBERS)
        val spinnerAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            rules
        )
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.ruleSpinner.adapter = spinnerAdapter

        binding.ruleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val highlightsNumber = when (rules[position]) {
                    ODD_NUMBERS -> numbers.filter { it % 2 != 0 }.toSet()
                    EVEN_NUMBERS -> numbers.filter { it % 2 == 0 }.toSet()
                    PRIME_NUMBERS -> numbers.filter { isPrime(it) }.toSet()
                    FIBONACCI_SEQUENCE_NUMBERS -> isFib(numbers).toSet()
                    else -> setOf()
                }
                (binding.recyclerView.adapter as GridAdapter).updateHighlightNumber(highlightsNumber)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


    }

    private fun isFib(num: List<Int>): List<Int> {

        var t1 = 0
        var t2 = 1

        val fibonacci = mutableListOf<Int>()
        for (i in 1..num.size) {
            fibonacci.add(t1)
            val sum = t1 + t2
            t1 = t2
            t2 = sum
        }
        return fibonacci

    }

    private fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        for (i in 2..num / 2) {
            if (num % i == 0)
                return false
        }

        return true
    }
}
