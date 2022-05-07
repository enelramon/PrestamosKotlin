package com.sagrd.prestamoskotlin.views.ocupaciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sagrd.prestamoskotlin.R
import com.sagrd.prestamoskotlin.databinding.RowOcupacionBinding
import com.sagrd.prestamoskotlin.model.Ocupacion

class OcupacionesAdapter :
    ListAdapter<Ocupacion, OcupacionesAdapter.OcupacionViewHolder>(OcupacionDiffCallBack()) {

    override fun onBindViewHolder(holder: OcupacionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OcupacionViewHolder {
        val binding =
            RowOcupacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OcupacionViewHolder(binding)
    }

    inner class OcupacionViewHolder(private val binding: RowOcupacionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ocupacion) {
            binding.ocupacionIdTextView.text = item.ocupacionId.toString()
            binding.descripcionTextView.text = item.descripcion
            binding.ingresosTextView.text = item.Ingresos.toString();

            binding.rowOcupacionConstraintlayout.setOnClickListener {
                val action = ListaOcupacionesFragmentDirections.actionToOcupacionesFragment(item)

                binding.root.findNavController().navigate(action)
            }
        }
    }
}

class OcupacionDiffCallBack : DiffUtil.ItemCallback<Ocupacion>() {
    override fun areItemsTheSame(oldItem: Ocupacion, newItem: Ocupacion): Boolean {
        return oldItem.ocupacionId == newItem.ocupacionId
    }

    override fun areContentsTheSame(oldItem: Ocupacion, newItem: Ocupacion): Boolean {
        return oldItem == newItem
    }
}