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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.ui.theme.AmarilloGradiente
import com.example.proyectoappsmobiles.ui.theme.GrisAzulado
import com.example.proyectoappsmobiles.ui.theme.Naranja

@Composable
fun PantallaEstadisticas(navController: NavController) {
    var tab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Semana", "Mes", "Año")
    val etiquetas = if (tab == 2) {
        listOf("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul")
    } else {
        listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")
    }
    val valores = listOf(0.5f, 0.75f, 0.4f, 0.9f, 0.6f, 0.8f, 0.35f)

    Scaffold(
        bottomBar = { MenuInferior(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Progreso", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFEEEEEE))
                    .padding(4.dp)
            ) {
                tabs.forEachIndexed { indice, etiqueta ->
                    val activo = indice == tab
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (activo) Naranja else Color.Transparent)
                            .clickable { tab = indice }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            etiqueta,
                            color = if (activo) Color.White else Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(GrisAzulado)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    valores.forEachIndexed { indice, valor ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(18.dp)
                                    .height((150 * valor).dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(AmarilloGradiente, Naranja)
                                        )
                                    )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                etiquetas.getOrElse(indice) { "" },
                                color = Color.White,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FitnessCenter, null, tint = Naranja)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Último entrenamiento", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Fuerza - pecho y triceps", color = Color.Gray, fontSize = 13.sp)
                    Text("45 minutos - 6 ejercicios", color = Color.Gray, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = { 0.35f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = Naranja
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("35% completado", fontSize = 12.sp, color = Naranja)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Historial reciente", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.FitnessCenter, null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Fuerza - Piernas", fontWeight = FontWeight.Bold)
                        Text("18 sep 2026 - 48 min", color = Color.Gray, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
