package com.practica_s06.ui.estado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practica_s06.R
import com.practica_s06.adapter.EstadoAdapter
import com.practica_s06.databinding.FragmentEstadoBinding
import com.practica_s06.viewmodel.EstadoViewModel

class EstadoFragment : Fragment() {

    private var _binding: FragmentEstadoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val estadoViewModel =
            ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentEstadoBinding.inflate(inflater, container, false)

        binding.addEstadoFabButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_estados_to_addEstadoFragment)
        }

        //SE GENERA EL RECYCLER VIEW PARA VER LA INFO
        val estadoAdapter = EstadoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = estadoAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        estadoViewModel.getEstados.observe(viewLifecycleOwner){
                estados -> estadoAdapter.setListaEstados(estados)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}