package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

@Composable
fun PantallaConfirmacionPago(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val membresia by viewModel.membresia.collectAsState(initial = null)

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("CONFIRMACIÓN PAGO", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            IconoExito()
            Spacer(modifier = Modifier.height(16.dp))
            Text("¡Pago Exitoso!", fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Tu membresía ha sido renovada correctamente",
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, Naranja)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DatoPago("Tipo de plan", membresia?.tipoPlan ?: "Plan premium")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    DatoPago("Duración y costo", "${membresia?.duracion ?: "1 mes"} / ${membresia?.costo ?: "$25.000"}")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    DatoPago("Fecha de Inicio", membresia?.fechaInicio ?: "25 sep 2026")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    DatoPago("Fecha de vencimiento", membresia?.fechaVencimiento ?: "25 oct 2026")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    DatoPago("Método de pago", membresia?.metodoPago ?: "****4312")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            BotonNaranja(
                texto = "Ir a mi membresía",
                onClick = {
                    navController.navigate(Pantallas.PAGOS) {
                        popUpTo(Pantallas.INICIO)
                    }
                }
            )
        }
    }
}

@Composable
private fun DatoPago(etiqueta: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
    ) {
        Text(etiqueta, color = Color.Gray, fontSize = 14.sp)
        Text(valor, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
