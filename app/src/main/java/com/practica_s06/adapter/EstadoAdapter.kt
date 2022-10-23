package com.practica_s06.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.practica_s06.databinding.EstadoFilaBinding
import com.practica_s06.model.Estado
import com.practica_s06.ui.estado.EstadoFragmentDirections

class EstadoAdapter : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>() {
    inner class EstadoViewHolder(private val itemBinding: EstadoFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun dibuja(estado: Estado) {
            itemBinding.tvNombre.text = estado.nombre
            itemBinding.tvCapital.text = estado.capital
            itemBinding.tvPais.text = estado.pais
            itemBinding.vistaFila.setOnClickListener{
                val action = EstadoFragmentDirections
                    .actionNavEstadosToUpdateEstadoFragment(estado)
                itemView.findNavController().navigate(action)
            }
        }
    }
    private var listaEstados = emptyList<Estado>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val itemBinding = EstadoFilaBinding.
        inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return EstadoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val lugar = listaEstados[position]
        holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return listaEstados.size
    }

    fun setListaEstados(estados: List<Estado>){
        this.listaEstados = estados
        notifyDataSetChanged()
    }
}