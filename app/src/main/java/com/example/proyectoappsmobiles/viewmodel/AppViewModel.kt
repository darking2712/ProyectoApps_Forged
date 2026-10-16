package com.example.proyectoappsmobiles.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoappsmobiles.data.database.DatabaseProvider
import com.example.proyectoappsmobiles.data.entity.ClaseEntity
import com.example.proyectoappsmobiles.data.entity.EntrenadorEntity
import com.example.proyectoappsmobiles.data.entity.MembresiaEntity
import com.example.proyectoappsmobiles.data.entity.ReservaEntity
import com.example.proyectoappsmobiles.data.entity.UsuarioEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseProvider.getDatabase(application)
    private val usuarioDao = db.usuarioDao()
    private val entrenadorDao = db.entrenadorDao()
    private val claseDao = db.claseDao()
    private val reservaDao = db.reservaDao()
    private val membresiaDao = db.membresiaDao()

    val entrenador = entrenadorDao.obtenerPrimero()
    val clases = claseDao.obtenerTodas()
    val reservas = reservaDao.obtenerTodas()
    val membresia = membresiaDao.obtenerActual()

    var usuarioActual by mutableStateOf<UsuarioEntity?>(null)
        private set

    var reservaSeleccionada by mutableStateOf<ReservaEntity?>(null)

    init {
        viewModelScope.launch {
            if (usuarioDao.contar() == 0) {
                usuarioDao.insertar(
                    UsuarioEntity(email = "carlos@mail.com", nombre = "Carlos", password = "1234")
                )
            }
            if (entrenadorDao.contar() == 0) {
                entrenadorDao.insertar(
                    EntrenadorEntity(
                        nombre = "Carlos Arturo",
                        rol = "Entrenador Personal",
                        calificacion = 4.9,
                        resenas = 180,
                        especialidades = "Musculación,Resistencia",
                        sobreMi = "Soy entrenador certificado con más de 5 años de experiencia ayudando a personas a alcanzar su objetivo."
                    )
                )
            }
            if (claseDao.contar() == 0) {
                claseDao.insertar(
                    ClaseEntity(
                        nombre = "Spinning",
                        dia = "Lunes",
                        horario = "6:00 pm - 7:00 pm",
                        entrenador = "Carlos Arturo",
                        cuposOcupados = 8,
                        cuposTotal = 15,
                        duracionMin = 60,
                        descripcion = "Mejora tu resistencia y quema calorias"
                    )
                )
                claseDao.insertar(
                    ClaseEntity(
                        nombre = "Musculación",
                        dia = "Miércoles",
                        horario = "3:00 pm - 4:00 pm",
                        entrenador = "Carlos Arturo",
                        cuposOcupados = 5,
                        cuposTotal = 10,
                        duracionMin = 60,
                        descripcion = "Entrenamiento de fuerza personalizado"
                    )
                )
            }
            if (reservaDao.contar() == 0) {
                val spinning = claseDao.obtenerPorId(1)
                reservaDao.insertar(
                    ReservaEntity(
                        idClase = spinning?.id ?: 1,
                        titulo = "Spinning",
                        fecha = "mie, 18 jun",
                        hora = "6:00 pm",
                        entrenador = "Carlos Arturo",
                        duracion = "60 min",
                        estado = "Activa",
                        esGrupal = true
                    )
                )
            }
            if (membresiaDao.contar() == 0) {
                membresiaDao.insertar(
                    MembresiaEntity(
                        tipoPlan = "Plan premium",
                        duracion = "1 mes",
                        costo = "$25.000",
                        fechaInicio = "25 sep 2026",
                        fechaVencimiento = "25 oct 2026",
                        metodoPago = "****4312"
                    )
                )
            }
        }
    }

    fun registrar(nombre: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            if (nombre.isBlank() || email.isBlank() || password.isBlank()) {
                onResult(false)
            } else {
                usuarioDao.insertar(UsuarioEntity(email, nombre, password))
                onResult(true)
            }
        }
    }

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val usuario = usuarioDao.autenticar(email, password)
            usuarioActual = usuario
            onResult(usuario != null)
        }
    }

    fun recuperarPassword(email: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            onResult(usuarioDao.existeEmail(email) != null)
        }
    }

    fun reservarClaseGrupal(claseId: Int, fecha: String, hora: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val clase = claseDao.obtenerPorId(claseId)
            if (clase == null || clase.cuposOcupados >= clase.cuposTotal) {
                onResult(false)
                return@launch
            }
            val nueva = ReservaEntity(
                idClase = clase.id,
                titulo = clase.nombre,
                fecha = fecha,
                hora = hora,
                entrenador = clase.entrenador,
                duracion = "${clase.duracionMin} min",
                estado = "Activa",
                esGrupal = true
            )
            val id = reservaDao.insertar(nueva)
            claseDao.actualizar(clase.copy(cuposOcupados = clase.cuposOcupados + 1))
            reservaSeleccionada = nueva.copy(id = id.toInt())
            onResult(true)
        }
    }

    fun reservarSesionPersonal(fecha: String, hora: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val entrenadorNombre = entrenadorDao.obtenerPrimero().first()?.nombre ?: "Carlos Arturo"
            val nueva = ReservaEntity(
                idClase = null,
                titulo = "Sesión personal",
                fecha = fecha,
                hora = hora,
                entrenador = entrenadorNombre,
                duracion = "60 min",
                estado = "Activa",
                esGrupal = false
            )
            val id = reservaDao.insertar(nueva)
            reservaSeleccionada = nueva.copy(id = id.toInt())
            onResult(true)
        }
    }

    fun cancelarReserva(id: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val reserva = reservaDao.obtenerPorId(id)
            if (reserva != null) {
                reservaDao.eliminar(reserva)
                reservaSeleccionada = reserva
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }
}
