package com.practica_s06.data
import androidx.lifecycle.LiveData
import androidx.room.*
import com.practica_s06.model.Estado


@Dao
interface EstadoDao {
    //las funciones de bajo nivel para hacer un crud
    //cread read update delete

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEstado(estado: Estado)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateEstado(estado: Estado)

    @Delete
    suspend fun deleteEstado(estado: Estado)

    @Query("SELECT * FROM ESTADO")
    fun getEstados() : LiveData<List<Estado>>
}