package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Check
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
import com.example.proyectoappsmobiles.ui.theme.VerdeExito

private data class PlanCard(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val destacado: Boolean
)

@Composable
fun PantallaPlanes(navController: NavController) {
    var tab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Mensual", "Anual", "Mi membresía")

    val planes = listOf(
        PlanCard("Básico", "$15.000/mes", "Acceso a clases grupales", false),
        PlanCard("Premium", "$25.000/mes", "Clases grupales + personalizadas", true),
        PlanCard("VIP", "$40.000/mes", "Clases grupales + personalizadas y rutinas personalizadas", false)
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
            Text("PLANES DE MEMBRESÍA", fontSize = 20.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(16.dp))

            PestañasSecundarias(tabs = tabs, seleccionado = tab, onSeleccion = { tab = it })
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                when (tab) {
                    0 -> planes.forEach { plan ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            border = if (plan.destacado) BorderStroke(2.dp, Naranja) else null
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(plan.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                    Text(plan.precio, color = Naranja, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(plan.descripcion, color = Color.Gray, fontSize = 13.sp)
                                Spacer(modifier = Modifier.height(12.dp))
                                Button(
                                    onClick = { navController.navigate(Pantallas.PAGO) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (plan.destacado) Naranja else Color(0xFF2A2A2A)
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Seleccionar", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                    1 -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            border = BorderStroke(2.dp, Naranja)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Plan Anual", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                Text("$299.00/año", color = Naranja, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Spacer(modifier = Modifier.height(12.dp))
                                listOf(
                                    "Acceso ilimitado a los beneficios",
                                    "Clases grupales incluidas",
                                    "Entrenamiento personalizado",
                                    "Descuentos en suplementación",
                                    "Acceso a clases especiales"
                                ).forEach { beneficio ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Default.Check,
                                            null,
                                            tint = VerdeExito,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(beneficio, fontSize = 14.sp)
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = { navController.navigate(Pantallas.PAGO) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Naranja),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Comprarr ahora", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFF4E342E), Color(0xFF3E2723))
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Hombre haciendo press", color = Color.White.copy(alpha = 0.5f))
                        }
                    }
                    else -> {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate(Pantallas.PAGOS) },
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(3.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Check, null, tint = VerdeExito)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text("Plan: Premium", fontWeight = FontWeight.Bold)
                                    Text("Activa - vence: 25 sep 2026", color = Color.Gray, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
