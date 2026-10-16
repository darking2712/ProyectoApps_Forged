package com.example.proyectoappsmobiles.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entrenadores")
data class EntrenadorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val rol: String,
    val calificacion: Double,
    val resenas: Int,
    val especialidades: String,
    val sobreMi: String
)
