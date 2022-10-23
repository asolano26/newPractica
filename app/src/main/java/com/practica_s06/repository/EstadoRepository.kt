package com.practica_s06.repository

import androidx.lifecycle.LiveData
import com.practica_s06.data.EstadoDao
import com.practica_s06.model.Estado

class EstadoRepository(private val estadoDao: EstadoDao) {
    suspend fun saveEstado(estado: Estado){
        if(estado.id==0){ //es un estado nuevo
            estadoDao.addEstado(estado)
        }else{ //ya registrado
            estadoDao.updateEstado(estado)
        }
    }

    suspend fun deleteEstado(estado: Estado){
        if(estado.id!= 0){
            estadoDao.deleteEstado(estado)
        }
    }

    val getEstados : LiveData<List<Estado>> = estadoDao.getEstados()
}