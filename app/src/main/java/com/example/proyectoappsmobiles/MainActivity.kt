package com.example.proyectoappsmobiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectoappsmobiles.gui.PantallaAsignarRutina
import com.example.proyectoappsmobiles.gui.PantallaCancelarReserva
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionCancelacion
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionPago
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionReserva
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionReservaGrupal
import com.example.proyectoappsmobiles.gui.PantallaDetalleClase
import com.example.proyectoappsmobiles.gui.PantallaDetalleEntrenador
import com.example.proyectoappsmobiles.gui.PantallaDetallesPerfil
import com.example.proyectoappsmobiles.gui.PantallaDisponibilidad
import com.example.proyectoappsmobiles.gui.PantallaEditarPerfil
import com.example.proyectoappsmobiles.gui.PantallaEstadisticas
import com.example.proyectoappsmobiles.gui.PantallaFinalizacion
import com.example.proyectoappsmobiles.gui.PantallaGenero
import com.example.proyectoappsmobiles.gui.PantallaHomeEntrenador
import com.example.proyectoappsmobiles.gui.PantallaInicio
import com.example.proyectoappsmobiles.gui.PantallaLogin
import com.example.proyectoappsmobiles.gui.PantallaMisClases
import com.example.proyectoappsmobiles.gui.PantallaOlvidePassword
import com.example.proyectoappsmobiles.gui.PantallaOnboarding
import com.example.proyectoappsmobiles.gui.PantallaPago
import com.example.proyectoappsmobiles.gui.PantallaPagos
import com.example.proyectoappsmobiles.gui.PantallaPerfil
import com.example.proyectoappsmobiles.gui.PantallaPlanes
import com.example.proyectoappsmobiles.gui.PantallaRegistro
import com.example.proyectoappsmobiles.gui.PantallaRenovar
import com.example.proyectoappsmobiles.gui.PantallaReservar
import com.example.proyectoappsmobiles.gui.PantallaRutina
import com.example.proyectoappsmobiles.gui.PantallaRutinaHoy
import com.example.proyectoappsmobiles.gui.PantallaSplash
import com.example.proyectoappsmobiles.gui.PantallaUnirseVideollamada
import com.example.proyectoappsmobiles.gui.PantallaVerificacion
import com.example.proyectoappsmobiles.gui.PantallaVerificacionCancelacion
import com.example.proyectoappsmobiles.gui.PantallaVideollamada
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.ProyectoAppsMobilesTheme
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoAppsMobilesTheme {
                AppNavegacion()
            }
        }
    }
}

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel= viewModel()
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Pantallas.SPLASH
        ) {
            composable(Pantallas.SPLASH) {
                PantallaSplash(navController)
            }
            composable(Pantallas.ONBOARDING) {
                PantallaOnboarding(navController)
            }
            composable(Pantallas.LOGIN) {
                PantallaLogin(navController, appViewModel)
            }
            composable(Pantallas.REGISTRO) {
                PantallaRegistro(navController)
            }
            composable(Pantallas.OLVIDE_PASSWORD) {
                PantallaOlvidePassword(navController)
            }
            composable(Pantallas.VERIFICACION) {
                PantallaVerificacion(navController)
            }
            composable(Pantallas.GENERO) {
                PantallaGenero(navController)
            }
            composable(Pantallas.DETALLES_PERFIL) {
                PantallaDetallesPerfil(navController)
            }
            composable(Pantallas.INICIO) {
                PantallaInicio(navController, appViewModel)
            }
            composable(Pantallas.HOME_ENTRENADOR) {
                PantallaHomeEntrenador(navController)
            }
            composable(Pantallas.ESTADISTICAS) {
                PantallaEstadisticas(navController)
            }
            composable(Pantallas.ENTRENADOR) {
                PantallaHomeEntrenador(navController)
            }
            composable(Pantallas.CALENDARIO) {
                PantallaMisClases(navController)
            }
            composable(Pantallas.PAGOS) {
                PantallaPagos(navController)
            }
            composable(Pantallas.PERFIL) {
                PantallaPerfil(navController, appViewModel)
            }
            composable(Pantallas.EDITAR_PERFIL) {
                PantallaEditarPerfil(navController)
            }
            composable(
                route = "${Pantallas.DETALLE_ENTRENADOR}/{entrenadorId}",
                arguments = listOf(navArgument("entrenadorId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("entrenadorId") ?: 1
                PantallaDetalleEntrenador(navController, entrenadorId = id)
            }
            composable(Pantallas.DISPONIBILIDAD) {
                PantallaDisponibilidad(navController)
            }
            composable(Pantallas.RESERVAR) {
                PantallaReservar(navController)
            }
            composable(Pantallas.MIS_CLASES) {
                PantallaMisClases(navController)
            }
            composable(
                route = "${Pantallas.DETALLE_CLASE}/{claseId}",
                arguments = listOf(navArgument("claseId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val claseId = backStackEntry.arguments?.getInt("claseId") ?: 1
                PantallaDetalleClase(navController = navController, claseId = claseId)
            }
            composable(Pantallas.CONFIRMACION_RESERVA) {
                PantallaConfirmacionReserva(navController)
            }
            composable(
                route = "${Pantallas.CONFIRMACION_RESERVA_GRUPAL}/{claseId}",
                arguments = listOf(navArgument("claseId") { type = NavType.IntType })
            ) { backStackEntry ->
                val claseId = backStackEntry.arguments?.getInt("claseId") ?: 0
                PantallaConfirmacionReservaGrupal(navController, claseId = claseId)
            }
            composable(Pantallas.CANCELAR_RESERVA) {
                PantallaCancelarReserva(navController)
            }
            composable(Pantallas.VERIFICACION_CANCELACION) {
                PantallaVerificacionCancelacion(navController)
            }
            composable(Pantallas.CONFIRMACION_CANCELACION) {
                PantallaConfirmacionCancelacion(navController)
            }
            composable(Pantallas.UNIRSE_VIDEOLLAMADA) {
                PantallaUnirseVideollamada(navController)
            }
            composable(Pantallas.VIDEOLLAMADA) {
                PantallaVideollamada(navController)
            }
            composable(Pantallas.FINALIZACION) {
                PantallaFinalizacion(navController)
            }
            composable(Pantallas.RUTINA) {
                PantallaRutina(navController)
            }
            composable(Pantallas.RUTINA_HOY) {
                PantallaRutinaHoy(navController)
            }
            composable(Pantallas.ASIGNAR_RUTINA) {
                PantallaAsignarRutina(navController)
            }
            composable(Pantallas.PLANES) {
                PantallaPlanes(navController)
            }
            composable(Pantallas.RENOVAR) {
                PantallaRenovar(navController)
            }
            composable(Pantallas.PAGO) {
                PantallaPago(navController)
            }
            composable(Pantallas.CONFIRMACION_PAGO) {
                PantallaConfirmacionPago(navController)
            }
        }
    }
}
