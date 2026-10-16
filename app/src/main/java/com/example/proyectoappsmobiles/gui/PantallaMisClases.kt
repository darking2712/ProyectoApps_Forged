package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.ui.theme.VerdeExito
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

private data class ReservaCard(
    val titulo: String,
    val fecha: String,
    val hora: String,
    val entrenador: String
)

@Composable
fun PantallaMisClases(navController: NavController, viewModel: AppViewModel = viewModel()) {
    var tab by remember { mutableIntStateOf(2) }
    val tabs = listOf("Grupales", "Personal", "Mis reservas")

    Scaffold(
        bottomBar = { MenuInferior(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("MIS CLASES", fontSize = 22.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(16.dp))
            PestañasSecundarias(tabs = tabs, seleccionado = tab, onSeleccion = { tab = it })
            Spacer(modifier = Modifier.height(16.dp))
            MisClasesContenido(navController, viewModel)
        }
    }
}

@Composable
fun MisClasesContenido(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val reservas by viewModel.reservas.collectAsState(initial = emptyList())
    val items = listOf(
        ReservaCard("Spinning", "mié 18 sep", "6:00 - 7:00 pm", "Carlos Arturo"),
        ReservaCard("Yoga", "jue 19 sep", "7:00 - 8:00 pm", "Laura Gomez"),
        ReservaCard("Entrenamiento funcional", "sáb 21 sep", "6:00 - 7:00 pm", "Alejandro Correa")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        items.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        reservas.firstOrNull()?.let { viewModel.reservaSeleccionada = it }
                        navController.navigate(Pantallas.CANCELAR_RESERVA)
                    },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Brush.verticalGradient(listOf(Color(0xFF37474F), Color(0xFF546E7A)))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.FitnessCenter, null, tint = Color.White.copy(alpha = 0.7f))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(item.titulo, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .background(VerdeExito, RoundedCornerShape(20.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text("Activa", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Text(item.fecha, color = Color.Gray, fontSize = 12.sp)
                        Text(item.hora, color = Color.Gray, fontSize = 12.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Person, null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(item.entrenador, color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                    Text(">", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
