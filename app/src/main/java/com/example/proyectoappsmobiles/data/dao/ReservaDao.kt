package com.example.proyectoappsmobiles.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoappsmobiles.data.entity.ReservaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(reserva: ReservaEntity): Long

    @Query("SELECT * FROM reservas")
    fun obtenerTodas(): Flow<List<ReservaEntity>>

    @Query("SELECT * FROM reservas WHERE id = :id")
    suspend fun obtenerPorId(id: Int): ReservaEntity?

    @Delete
    suspend fun eliminar(reserva: ReservaEntity)

    @Query("SELECT COUNT(*) FROM reservas")
    suspend fun contar(): Int
}
