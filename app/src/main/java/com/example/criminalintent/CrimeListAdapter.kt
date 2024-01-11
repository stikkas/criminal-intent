package com.example.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.criminalintent.databinding.ListItemCrimeBinding
import com.example.criminalintent.databinding.ListItemPolicycrimeBinding
import java.text.SimpleDateFormat
import java.util.Locale

private interface Holder {
    fun bind(crime: Crime)
}

class CrimeHolder(private val binding: ListItemCrimeBinding) :
    Holder, ViewHolder(binding.root) {
    override fun bind(crime: Crime) {
        with(binding) {
            crimeTitle.text = crime.title
            crimeDate.text = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
                .format(crime.date)
            root.setOnClickListener {
                Toast.makeText(root.context, "${crime.title} clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            crimeSolved.visibility = if (crime.isSolved) VISIBLE else GONE
        }

    }
}

class PoliceCrimeHolder(private val binding: ListItemPolicycrimeBinding) :
    Holder, ViewHolder(binding.root) {

    override fun bind(crime: Crime) {
        with(binding) {
            crimeTitle.text = crime.title
            crimeDate.text = crime.date.toString()
            root.setOnClickListener {
                Toast.makeText(root.context, "${crime.title} clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            contactButton.setOnClickListener {
                Toast.makeText(root.context, "Button clicked", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}

class CrimeListAdapter(private val crimes: List<Crime>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            CrimeHolder(ListItemCrimeBinding.inflate(inflater, parent, false))
        } else {
            PoliceCrimeHolder(ListItemPolicycrimeBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (crimes[position].requiresPolicy) {
            (holder as PoliceCrimeHolder)
        } else {
            (holder as CrimeHolder)
        }.bind(crimes[position])
    }

    override fun getItemCount(): Int = crimes.size

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolicy) 1 else 0
    }
}