package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.proyectoappsmobiles.R
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
import androidx.compose.material.icons.filled.AccessTime
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.AzulEnlace
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

@Composable
fun PantallaInicio(navController: NavController, viewModel: AppViewModel = viewModel()) {
    var nivelSeleccionado by remember { mutableIntStateOf(0) }
    val niveles = listOf("Principiante", "Intermedio", "Avanzado")

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
            Text(
                "Hola ${viewModel.usuarioActual?.nombre ?: "User"},",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            EncabezadoSeccion(
                titulo = "Listo para entrenar",
                textoLink = "ver reservas",
                onLinkClick = { navController.navigate(Pantallas.CALENDARIO) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Pantallas.MIS_CLASES) },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.yoga),
                        contentDescription = "Cinta",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Yoga", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AccessTime, null, tint = Color.Gray, modifier = Modifier.height(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("5:00 pm", color = Color.Gray, fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            EncabezadoSeccion(
                titulo = "Mi rutina",
                textoLink = "ver rutina",
                onLinkClick = { navController.navigate(Pantallas.RUTINA) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SelectorSegmentado(
                opciones = niveles,
                seleccionado = nivelSeleccionado,
                onSeleccion = { nivelSeleccionado = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.verticalGradient(listOf(Color(0xFF263238), Color(0xFF455A64)))
                    )
                    .clickable { navController.navigate(Pantallas.RUTINA) },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.abdominales),
                    contentDescription = "Rutina",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { navController.navigate(Pantallas.RUTINA) },
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            EncabezadoSeccion(
                titulo = "Membresía",
                textoLink = "ver más",
                onLinkClick = { navController.navigate(Pantallas.PLANES) }
            )
            Spacer(modifier = Modifier.height(8.dp))
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
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Mi Membresía", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Plan Premium", color = Color.Gray, fontSize = 13.sp)
                        Text("vence: 25 sep", color = Color.Gray, fontSize = 13.sp)
                    }
                    Box(
                        modifier = Modifier
                            .background(Naranja, RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text("Activa", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun EncabezadoSeccion(titulo: String, textoLink: String, onLinkClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(
            textoLink,
            color = AzulEnlace,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onLinkClick)
        )
    }
}

@Composable
fun SelectorSegmentado(
    opciones: List<String>,
    seleccionado: Int,
    onSeleccion: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFEEEEEE))
            .padding(4.dp)
    ) {
        opciones.forEachIndexed { indice, opcion ->
            val activo = indice == seleccionado
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (activo) Naranja else Color.Transparent)
                    .clickable { onSeleccion(indice) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    opcion,
                    color = if (activo) Color.White else Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}
