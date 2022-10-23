package com.practica_s06.ui.estado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.practica_s06.R
import com.practica_s06.databinding.FragmentAddEstadoBinding
import com.practica_s06.model.Estado
import com.practica_s06.viewmodel.EstadoViewModel

class AddEstadoFragment : Fragment() {

    //EL objeto para interacturar con la tabla
    private lateinit var  estadoViewModel: EstadoViewModel
    private var _binding: FragmentAddEstadoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel =
            ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentAddEstadoBinding.inflate(inflater, container, false)

        binding.btAdd.setOnClickListener{addEstado()}

        return binding.root
    }

    private fun addEstado() {
        val nombre = binding.etNombre.text.toString()
        if(nombre.isNotEmpty()){
            val pais = binding.etPais.text.toString()
            val capital = binding.etCapital.text.toString()
            val poblacion = binding.etPoblacion.text.toString().toDouble()
            val estado = Estado(0,pais,nombre,capital,poblacion)

            estadoViewModel.saveEstado(estado)

            Toast.makeText(requireContext(),
                getString(R.string.msg_estador_added),
                Toast.LENGTH_SHORT).show()
            //findNavController().navigate(R.id.action_addEstadoFragment_to_nav_estados)
        }else{
            Toast.makeText(requireContext(),
                getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

}