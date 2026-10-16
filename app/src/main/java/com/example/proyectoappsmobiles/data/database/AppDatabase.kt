package com.example.proyectoappsmobiles.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectoappsmobiles.data.dao.ClaseDao
import com.example.proyectoappsmobiles.data.dao.EntrenadorDao
import com.example.proyectoappsmobiles.data.dao.MembresiaDao
import com.example.proyectoappsmobiles.data.dao.ReservaDao
import com.example.proyectoappsmobiles.data.dao.UsuarioDao
import com.example.proyectoappsmobiles.data.entity.ClaseEntity
import com.example.proyectoappsmobiles.data.entity.EntrenadorEntity
import com.example.proyectoappsmobiles.data.entity.MembresiaEntity
import com.example.proyectoappsmobiles.data.entity.ReservaEntity
import com.example.proyectoappsmobiles.data.entity.UsuarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        EntrenadorEntity::class,
        ClaseEntity::class,
        ReservaEntity::class,
        MembresiaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun entrenadorDao(): EntrenadorDao
    abstract fun claseDao(): ClaseDao
    abstract fun reservaDao(): ReservaDao
    abstract fun membresiaDao(): MembresiaDao
}
