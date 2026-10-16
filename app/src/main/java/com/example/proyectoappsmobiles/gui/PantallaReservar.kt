package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja

private data class ClaseCard(
    val nombre: String,
    val horario: String,
    val entrenador: String,
    val cupos: String,
    val id: Int
)

private data class EntrenadorCard(
    val nombre: String,
    val rol: String,
    val rating: String
)

@Composable
fun PantallaReservar(navController: NavController) {
    var tab by remember { mutableIntStateOf(0) }
    var diaSeleccionado by remember { mutableStateOf("18") }
    val tabs = listOf("Grupales", "Personal", "Mis reservas")

    val grupos = listOf(
        ClaseCard("Spinning", "6:00 - 7:00 pm", "Carlos Arturo", "8/15 cupos", 1),
        ClaseCard("Yoga", "7:00 - 8:00 pm", "Laura Gomez", "12/15 cupos", 2),
        ClaseCard("Entrenamiento funcional", "6:00 - 7:00 pm", "Alejandro Correa", "9/15 cupos", 3)
    )
    val personales = listOf(
        EntrenadorCard("Carlos Arturo", "Entrenador personal", "4.9 (180 reseñas)"),
        EntrenadorCard("Laura Gomez", "Entrenadora Personal", "4.8 (150 reseñas)"),
        EntrenadorCard("Diego Ramirez", "Entrenador Personal", "4.8 (150 reseñas)")
    )

    Scaffold(
        bottomBar = { MenuInferior(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text("RESERVAR", fontSize = 22.sp, fontWeight = FontWeight.Black)

            Spacer(modifier = Modifier.height(12.dp))
            TiraDias(diaSeleccionado = diaSeleccionado, onSeleccion = { diaSeleccionado = it })

            Spacer(modifier = Modifier.height(16.dp))
            PestañasSecundarias(tabs = tabs, seleccionado = tab, onSeleccion = { tab = it })
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                when (tab) {
                    0 -> grupos.forEach { clase ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(3.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(Color(0xFF37474F), Color(0xFF546E7A))
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.FitnessCenter, null, tint = Color.White.copy(alpha = 0.7f))
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(clase.nombre, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                    Text(clase.horario, color = Color.Gray, fontSize = 12.sp)
                                    Text(clase.entrenador, color = Color.Gray, fontSize = 12.sp)
                                    Text(clase.cupos, color = Naranja, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                                Button(
                                    onClick = {
                                        navController.navigate("${Pantallas.DETALLE_CLASE}/${clase.id}")
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Naranja),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Reservar", color = Color.White, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                    1 -> personales.forEach { entrenador ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(3.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(Color(0xFF6D4C41), Color(0xFF3E2723))
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Person, null, tint = Color.White.copy(alpha = 0.8f))
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(entrenador.nombre, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                    Text(entrenador.rol, color = Color.Gray, fontSize = 12.sp)
                                    Text("⭐ ${entrenador.rating}", color = Naranja, fontSize = 12.sp)
                                }
                                Button(
                                    onClick = { navController.navigate(Pantallas.DETALLE_ENTRENADOR) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Naranja),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Reservar", color = Color.White, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                    else -> {
                        MisClasesContenido(navController)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun PestañasSecundarias(
    tabs: List<String>,
    seleccionado: Int,
    onSeleccion: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFEEEEEE))
            .padding(4.dp)
    ) {
        tabs.forEachIndexed { indice, tab ->
            val activo = indice == seleccionado
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (activo) Naranja else Color.Transparent)
                    .clickable { onSeleccion(indice) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    tab,
                    color = if (activo) Color.White else Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}
