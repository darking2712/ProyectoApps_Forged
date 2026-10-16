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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectoappsmobiles.gui.PantallaCalendario
import com.example.proyectoappsmobiles.gui.PantallaCancelarReserva
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionCancelacion
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionPago
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionReserva
import com.example.proyectoappsmobiles.gui.PantallaConfirmacionReservaGrupal
import com.example.proyectoappsmobiles.gui.PantallaDetalleClase
import com.example.proyectoappsmobiles.gui.PantallaDetalleEntrenador
import com.example.proyectoappsmobiles.gui.PantallaDisponibilidad
import com.example.proyectoappsmobiles.gui.PantallaEstadisticas
import com.example.proyectoappsmobiles.gui.PantallaInicio
import com.example.proyectoappsmobiles.gui.PantallaLogin
import com.example.proyectoappsmobiles.gui.PantallaOlvidePassword
import com.example.proyectoappsmobiles.gui.PantallaPagos
import com.example.proyectoappsmobiles.gui.PantallaPerfil
import com.example.proyectoappsmobiles.gui.PantallaRegistro
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.ProyectoAppsMobilesTheme

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
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Pantallas.LOGIN
        ) {
            composable(Pantallas.LOGIN) {
                PantallaLogin(navController)
            }
            composable(Pantallas.REGISTRO) {
                PantallaRegistro(navController)
            }
            composable(Pantallas.OLVIDE_PASSWORD) {
                PantallaOlvidePassword(navController)
            }
            composable(Pantallas.INICIO) {
                PantallaInicio(navController)
            }
            composable(Pantallas.ESTADISTICAS) {
                PantallaEstadisticas(navController)
            }
            composable(Pantallas.ENTRENADOR) {
                PantallaDetalleEntrenador(navController)
            }
            composable(Pantallas.CALENDARIO) {
                PantallaCalendario(navController)
            }
            composable(Pantallas.PAGOS) {
                PantallaPagos(navController)
            }
            composable(Pantallas.PERFIL) {
                PantallaPerfil(navController)
            }
            composable(Pantallas.DETALLE_ENTRENADOR) {
                PantallaDetalleEntrenador(navController)
            }
            composable(Pantallas.DISPONIBILIDAD) {
                PantallaDisponibilidad(navController)
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
            composable(Pantallas.CONFIRMACION_RESERVA_GRUPAL) {
                PantallaConfirmacionReservaGrupal(navController)
            }
            composable(Pantallas.CANCELAR_RESERVA) {
                PantallaCancelarReserva(navController)
            }
            composable(Pantallas.CONFIRMACION_CANCELACION) {
                PantallaConfirmacionCancelacion(navController)
            }
            composable(Pantallas.CONFIRMACION_PAGO) {
                PantallaConfirmacionPago(navController)
            }
        }
    }
}
