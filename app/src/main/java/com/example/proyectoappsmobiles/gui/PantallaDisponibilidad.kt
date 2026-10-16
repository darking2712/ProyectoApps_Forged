package com.example.proyectoappsmobiles.gui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

data class DiaSemana(val dia: String, val numero: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDisponibilidad(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val context = LocalContext.current
    var diaSeleccionado by remember { mutableStateOf("18") }
    var horaSeleccionada by remember { mutableStateOf("3:00") }

    val dias = listOf(
        DiaSemana("Lun", "16"),
        DiaSemana("Mar", "17"),
        DiaSemana("Mié", "18"),
        DiaSemana("Jue", "19"),
        DiaSemana("Vie", "20"),
        DiaSemana("Sáb", "21"),
        DiaSemana("Dom", "22")
    )

    val horarios = listOf("09:00", "10:00", "11:00", "12:00", "01:00", "2:00", "3:00", "4:00", "5:00")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("SEPTIEMBRE 2026", color = Color.White, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, null, tint = Color.White)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Tune, null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Naranja)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                dias.forEach { dia ->
                    val seleccionado = dia.numero == diaSeleccionado
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(
                                color = if (seleccionado) Naranja else Color.Transparent,
                                shape = RoundedCornerShape(50)
                            )
                            .clickable { diaSeleccionado = dia.numero }
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                    ) {
                        Text(
                            dia.dia,
                            fontSize = 11.sp,
                            color = if (seleccionado) Color.White else Color.Gray
                        )
                        Text(
                            dia.numero,
                            fontWeight = FontWeight.Bold,
                            color = if (seleccionado) Color.White else Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Horarios Disponibles", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(horarios) { hora ->
                    val seleccionado = hora == horaSeleccionada
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(
                                color = if (seleccionado) Naranja else Color.White,
                                shape = RoundedCornerShape(50)
                            )
                            .border(
                                BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(50)
                            )
                            .clickable { horaSeleccionada = hora },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            hora,
                            fontWeight = FontWeight.Bold,
                            color = if (seleccionado) Color.White else Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            BotonNaranja(
                texto = "Agendar",
                onClick = {
                    val fecha = "18-sep-2026"
                    val horaFormateada = if (horaSeleccionada.startsWith("0")) {
                        horaSeleccionada
                    } else {
                        "$horaSeleccionada pm"
                    }
                    viewModel.reservarSesionPersonal(fecha, horaFormateada) { exito ->
                        if (exito) {
                            navController.navigate(Pantallas.CONFIRMACION_RESERVA) {
                                popUpTo(Pantallas.DISPONIBILIDAD) { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Error al agendar", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }
}
