package com.example.proyectoappsmobiles.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoappsmobiles.data.entity.ClaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(clase: ClaseEntity)

    @Query("SELECT * FROM clases")
    fun obtenerTodas(): Flow<List<ClaseEntity>>

    @Query("SELECT * FROM clases WHERE id = :id")
    suspend fun obtenerPorId(id: Int): ClaseEntity?

    @Update
    suspend fun actualizar(clase: ClaseEntity)

    @Query("SELECT COUNT(*) FROM clases")
    suspend fun contar(): Int
}
