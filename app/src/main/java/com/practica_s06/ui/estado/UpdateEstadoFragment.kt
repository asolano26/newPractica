package com.practica_s06.ui.estado

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.practica_s06.R
import com.practica_s06.databinding.FragmentAddEstadoBinding
import com.practica_s06.databinding.FragmentUpdateEstadoBinding
import com.practica_s06.model.Estado
import com.practica_s06.viewmodel.EstadoViewModel

class UpdateEstadoFragment : Fragment() {

    //objeto para obtener los argumentos pasados al gragmento
    private val args by navArgs<UpdateEstadoFragmentArgs>()

    //EL objeto para interacturar con la tabla
    private lateinit var  estadoViewModel: EstadoViewModel
    private var _binding: FragmentUpdateEstadoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel =
            ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentUpdateEstadoBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.estado.nombre)
        binding.etPais.setText(args.estado.pais)
        binding.etCapital.setText(args.estado.capital)
        binding.etPoblacion.setText(args.estado.poblacion.toString())
        binding.btUpdate.setOnClickListener{updatEstado()}
        binding.btDelete.setOnClickListener{deleteEstado()}

        return binding.root
    }

    private fun updatEstado() {
        val nombre = binding.etNombre.text.toString()

        if(nombre.isNotEmpty()){
            val pais = binding.etPais.text.toString()
            val capital = binding.etCapital.text.toString()
            val poblacion = binding.etPoblacion.text.toString()
            var poblacionDouble = 0.0
            if(poblacion.isNotEmpty()) {
                poblacionDouble = poblacion.toDouble()
            }
            val estado = Estado(args.estado.id,
                                pais,nombre,capital,poblacionDouble)

            estadoViewModel.saveEstado(estado)

            Toast.makeText(requireContext(),
                getString(R.string.msg_estado_updated),
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_estados)
        }else{
            Toast.makeText(requireContext(),
                getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteEstado() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_estado)
        alerta.setMessage(getString(R.string.msg_pregunta_deleted)+ " ${args.estado.nombre}?")
        alerta.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            estadoViewModel.deleteEstado((args.estado))
            Toast.makeText(requireContext(), R.string.msg_estado_deleted, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_estados)
        }
        alerta.setNegativeButton(getString(R.string.msg_no)){_,_ ->}
        alerta.create().show()
    }

}