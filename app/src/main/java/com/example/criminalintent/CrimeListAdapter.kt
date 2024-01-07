package com.example.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ListItemCrimeBinding

class CrimeHolder(private val binding: ListItemCrimeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        with(binding) {
            crimeTitle.text = crime.title
            crimeDate.text = crime.date.toString()
            root.setOnClickListener {
                Toast.makeText(root.context, "${crime.title} clicked", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}

class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        holder.bind(crimes[position])
    }

    override fun getItemCount(): Int = crimes.size
}