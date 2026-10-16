package com.example.proyectoappsmobiles.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "membresias")
data class MembresiaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipoPlan: String,
    val duracion: String,
    val costo: String,
    val fechaInicio: String,
    val fechaVencimiento: String,
    val metodoPago: String
)
