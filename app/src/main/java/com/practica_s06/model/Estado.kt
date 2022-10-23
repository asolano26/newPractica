package com.practica_s06.model
import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="estado")
data class Estado(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name = "pais")
    val pais: String?,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "capital")
    val capital: String?,

    @ColumnInfo(name = "poblacion")
    val poblacion: Double?
) : Parcelable