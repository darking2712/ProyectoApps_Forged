package com.example.proyectoappsmobiles.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoappsmobiles.data.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :password")
    suspend fun autenticar(email: String, password: String): UsuarioEntity?

    @Query("SELECT * FROM usuarios WHERE email = :email")
    suspend fun existeEmail(email: String): UsuarioEntity?

    @Query("SELECT COUNT(*) FROM usuarios")
    suspend fun contar(): Int
}
