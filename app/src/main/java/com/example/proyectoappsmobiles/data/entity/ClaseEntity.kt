package com.example.proyectoappsmobiles.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clases")
data class ClaseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val dia: String,
    val horario: String,
    val entrenador: String,
    val cuposOcupados: Int,
    val cuposTotal: Int,
    val duracionMin: Int,
    val descripcion: String
)
