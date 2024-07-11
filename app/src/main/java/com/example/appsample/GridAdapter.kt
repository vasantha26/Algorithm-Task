package com.example.appsample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter(private var numbers: List<Int>, private var hightLightNumber: Set<Any>) :
    RecyclerView.Adapter<GridAdapter.NumberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val number = numbers[position]
        holder.numberText.text = number.toString()

        if (hightLightNumber.contains(number)) {
            holder.numberText.setBackgroundColor(Color.YELLOW)
        } else {
            holder.numberText.setBackgroundColor(Color.TRANSPARENT)

        }
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    fun updateHighlightNumber(highlightsNumbers: Set<Int>) {
        hightLightNumber = highlightsNumbers
        notifyDataSetChanged()

    }

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var numberText: TextView = itemView.findViewById(R.id.numberText)
    }
}