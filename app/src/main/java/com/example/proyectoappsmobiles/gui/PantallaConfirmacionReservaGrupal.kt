package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

@Composable
fun PantallaConfirmacionReservaGrupal(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val reserva = viewModel.reservaSeleccionada
    val clases by viewModel.clases.collectAsState(initial = emptyList())
    val clase = reserva?.idClase?.let { id -> clases.find { it.id == id } }

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
                    ImagenSpinning(
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(clase?.nombre ?: reserva?.titulo ?: "Spinning", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Horario: ${clase?.horario ?: "6:00 pm - 7:00 pm"}", color = Color.DarkGray, fontSize = 13.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Person, null, tint = Color.Gray)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                "Entrenador: ${clase?.entrenador ?: reserva?.entrenador ?: "Carlos Arturo"}",
                                color = Color.DarkGray,
                                fontSize = 13.sp
                            )
                        }
                        Text(
                            "Cupos: ${clase?.cuposOcupados ?: 9}/${clase?.cuposTotal ?: 15} cupos",
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
