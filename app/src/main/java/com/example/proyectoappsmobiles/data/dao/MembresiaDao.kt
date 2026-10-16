package com.example.proyectoappsmobiles.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoappsmobiles.data.entity.MembresiaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MembresiaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(membresia: MembresiaEntity)

    @Query("SELECT * FROM membresias ORDER BY id DESC LIMIT 1")
    fun obtenerActual(): Flow<MembresiaEntity?>

    @Query("SELECT COUNT(*) FROM membresias")
    suspend fun contar(): Int
}
