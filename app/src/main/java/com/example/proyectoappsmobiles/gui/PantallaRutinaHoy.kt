package com.example.proyectoappsmobiles.gui

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.ui.theme.NaranjaOscuro
import com.example.proyectoappsmobiles.ui.theme.VerdeLima

private data class EjercicioRutina(
    val nombre: String,
    val tiempo: String,
    val completado: Boolean
)

@Composable
fun PantallaRutinaHoy(navController: NavController) {
    val context = LocalContext.current
    var filtro by remember { mutableIntStateOf(0) }
    val filtros = listOf("Todos", "Completadas", "Pendientes")

    val ejercicios = listOf(
        EjercicioRutina("Warming Up", "02:00", true),
        EjercicioRutina("Traditional Job", "00:20", false),
        EjercicioRutina("Traditional Job", "00:20", false),
        EjercicioRutina("Traditional Job", "00:20", false),
        EjercicioRutina("Traditional Job", "", false),
        EjercicioRutina("Traditional Job", "00:20", false)
    )

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("RUTINA HOY", fontSize = 22.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFEEEEEE))
                    .padding(4.dp)
            ) {
                filtros.forEachIndexed { indice, etiqueta ->
                    val activo = indice == filtro
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (activo) Naranja else Color.Transparent)
                            .clickable { filtro = indice }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            etiqueta,
                            color = if (activo) Color.White else Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                ejercicios.forEach { ejercicio ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFF455A64), Color(0xFF263238))
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.FitnessCenter, null, tint = Color.White.copy(alpha = 0.7f))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(ejercicio.nombre, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            if (ejercicio.tiempo.isNotEmpty()) {
                                Text(ejercicio.tiempo, color = Color.Gray, fontSize = 13.sp)
                            }
                        }
                        if (ejercicio.completado) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(VerdeLima, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(18.dp))
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(Color.Transparent, CircleShape)
                                    .border(2.dp, Color.LightGray, CircleShape)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    Toast.makeText(context, "Rutina completada", Toast.LENGTH_SHORT).show()
                    navController.navigate(Pantallas.RUTINA) {
                        popUpTo(Pantallas.RUTINA)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NaranjaOscuro),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Marcar Rutina como finalizada", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
