package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.R
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.viewmodel.AppViewModel



fun obtenerImagen(nombreClase: String): Int{
    return when {
        nombreClase.contains("spinning", ignoreCase = true) -> R.drawable.spinning
        nombreClase.contains("yoga", ignoreCase = true) -> R.drawable.yoga
        nombreClase.contains("Entrenamiento Funcional", ignoreCase = true) -> R.drawable.funcional
        else -> R.drawable.spinning
    }
}

@Composable
fun PantallaConfirmacionReservaGrupal(navController: NavController,claseId: Int, viewModel: AppViewModel = viewModel()) {
    val clases by viewModel.clases.collectAsState(initial = emptyList())
    // Buscamos la clase directamente por el ID recibido
    val clase = clases.find { it.id == claseId }
    val nombreClase = clase?.nombre ?: ""
    val imagenRes = obtenerImagen(nombreClase)

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text("DETALLES DE LA CLASE", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            IconoExito()
            Spacer(modifier = Modifier.height(8.dp))
            Text("¡Reserva confirmada!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Tu clase ha sido agendada correctamente.",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imagenRes),
                        contentDescription = "Imagen de $nombreClase",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = nombreClase.ifEmpty { "Clase Grupal" },
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Horario: ${clase?.dia ?: ""} ${clase?.horario ?: ""}",
                            color = Color.DarkGray,
                            fontSize = 13.sp
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Entrenador",
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                "Entrenador: ${clase?.entrenador ?: "Por asignar"}",
                                color = Color.DarkGray,
                                fontSize = 13.sp
                            )
                        }

                        Text(
                            "Cupos: ${clase?.cuposOcupados ?: 0}/${clase?.cuposTotal ?: 0} cupos",
                            color = Naranja,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            BotonNaranja(
                texto = "Ver más reservas",
                onClick = {
                    navController.navigate(Pantallas.CALENDARIO) {
                        popUpTo(Pantallas.INICIO)
                    }
                }
            )
        }
    }
}