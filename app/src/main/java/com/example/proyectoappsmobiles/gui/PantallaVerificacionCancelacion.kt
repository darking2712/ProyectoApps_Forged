package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.proyectoappsmobiles.ui.theme.RojoError
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

@Composable
fun PantallaVerificacionCancelacion(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val reserva = viewModel.reservaSeleccionada

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text("VERIFICACIÓN", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(24.dp))
            Icon(
                Icons.Default.Warning,
                null,
                tint = RojoError,
                modifier = Modifier.size(72.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "¿Estás seguro de que deseas cancelar esta reserva?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImagenSpinning(
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(reserva?.titulo ?: "Spinning", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("${reserva?.fecha ?: "Mie, 18 jun"} - ${reserva?.hora ?: "6:00 pm"}", color = Color.Gray, fontSize = 13.sp)
                        Text("Entrenador: ${reserva?.entrenador ?: "Carlos Arturo"}", color = Color.Gray, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BotonNaranja(
                    texto = "Sí, Cancelar",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        reserva?.let { r ->
                            viewModel.cancelarReserva(r.id) { }
                        }
                        navController.navigate(Pantallas.CONFIRMACION_CANCELACION) {
                            popUpTo(Pantallas.INICIO) { inclusive = false }
                        }
                    }
                )
                BotonContorno(
                    texto = "No, Mantener",
                    modifier = Modifier.weight(1f),
                    onClick = { navController.popBackStack() }
                )
            }
        }
    }
}
