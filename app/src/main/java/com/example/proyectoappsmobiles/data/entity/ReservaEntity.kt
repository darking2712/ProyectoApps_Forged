package com.example.proyectoappsmobiles.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservas")
data class ReservaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idClase: Int?,
    val titulo: String,
    val fecha: String,
    val hora: String,
    val entrenador: String,
    val duracion: String,
    val estado: String,
    val esGrupal: Boolean
)
