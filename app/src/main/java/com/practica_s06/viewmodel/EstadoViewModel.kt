package com.practica_s06.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.practica_s06.data.PracticaDatabase
import com.practica_s06.model.Estado
import com.practica_s06.repository.EstadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstadoViewModel(application: Application) : AndroidViewModel(application) {

    private val estadoRepository: EstadoRepository
    val getEstados: LiveData<List<Estado>>

    init {
        val estadoDao = PracticaDatabase.getDatabase(application).estadoDao()
        estadoRepository = EstadoRepository(estadoDao)
        getEstados = estadoRepository.getEstados
    }

    fun saveEstado(estado: Estado){
        viewModelScope.launch(Dispatchers.IO){
            estadoRepository.saveEstado(estado)
        }
    }

    fun deleteEstado(estado: Estado){
        viewModelScope.launch(Dispatchers.IO){
            estadoRepository.deleteEstado(estado)
        }
    }
}