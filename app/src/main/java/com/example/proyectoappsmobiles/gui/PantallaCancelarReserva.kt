package com.example.proyectoappsmobiles.gui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.ui.theme.RojoError
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCancelarReserva(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val context = LocalContext.current
    val reserva = viewModel.reservaSeleccionada

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CANCELAR RESERVA", color = Color.White, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Naranja)
            )
        },
        bottomBar = { MenuInferior(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (reserva == null) {
                Text("No hay reserva seleccionada", color = Color.Gray)
                return@Column
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column {
                    ImagenSpinning(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(reserva.titulo, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("${reserva.fecha} - ${reserva.hora}", color = Color.DarkGray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Person, null, tint = Color.Gray)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Entrenador: ${reserva.entrenador}", color = Color.DarkGray, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CalendarMonth, null, tint = Color.Gray)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Duración: ${reserva.duracion}", color = Color.DarkGray, fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Estado: ${reserva.estado}", color = Naranja, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BotonNaranja(
                texto = "Unirme a videollamada",
                onClick = {
                    Toast.makeText(context, "Abriendo videollamada...", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            BotonNaranja(
                texto = "Cancelar Reserva",
                color = RojoError,
                onClick = {
                    viewModel.cancelarReserva(reserva.id) { exito ->
                        if (exito) {
                            navController.navigate(Pantallas.CONFIRMACION_CANCELACION) {
                                popUpTo(Pantallas.INICIO)
                            }
                        } else {
                            Toast.makeText(context, "Error al cancelar", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }
}
