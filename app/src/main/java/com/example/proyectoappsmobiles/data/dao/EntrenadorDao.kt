package com.example.proyectoappsmobiles.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoappsmobiles.data.entity.EntrenadorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntrenadorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(entrenador: EntrenadorEntity)

    @Query("SELECT * FROM entrenadores LIMIT 1")
    fun obtenerPrimero(): Flow<EntrenadorEntity?>

    @Query("SELECT COUNT(*) FROM entrenadores")
    suspend fun contar(): Int
}
