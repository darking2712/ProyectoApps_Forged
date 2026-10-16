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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

private data class RutinaCard(val titulo: String, val detalle: String, val subtitulo: String)

@Composable
fun PantallaRutina(navController: NavController) {
    var diaSeleccionado by remember { mutableStateOf("18") }
    val rutinas = listOf(
        RutinaCard("FULL BODY", "4 series - 12 repeticiones", ""),
        RutinaCard("LOWER BODY", "4 series - 12 repeticiones", ""),
        RutinaCard("LOWER BODY", "7x4 Challenge", "30 Days")
    )

    Scaffold(
        bottomBar = { MenuInferior(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("MI RUTINA", fontSize = 22.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(12.dp))
            TiraDias(diaSeleccionado = diaSeleccionado, onSeleccion = { diaSeleccionado = it })
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Pantallas.RUTINA_HOY) },
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Naranja, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.FitnessCenter, null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Rutina de hoy", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Fuerza - Nivel Intermedio", color = Color.Gray, fontSize = 13.sp)
                            Text("45 min - 6 ejercicios", color = Color.Gray, fontSize = 13.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                rutinas.forEach { rutina ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable { navController.navigate(Pantallas.RUTINA_HOY) },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(3.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(Color(0xFF546E7A), Color(0xFF37474F))
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Banco", color = Color.White.copy(alpha = 0.6f), fontSize = 11.sp)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(rutina.titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(rutina.detalle, color = Color.Gray, fontSize = 13.sp)
                                if (rutina.subtitulo.isNotEmpty()) {
                                    Text(rutina.subtitulo, color = Naranja, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            Text(">", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
