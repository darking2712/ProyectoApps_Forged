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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.AzulEnlace

@Composable
fun PantallaDetallesPerfil(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text("DANOS ALGUNOS DETALLES FINALES", fontSize = 22.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "ESTA INFORMACIÓN NOS AYUDARA A DARTE ENTRENAMIENTOS MÁS ESPECIALIZADOS",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        FilaDetalle("FECHA DE CUMPLEAÑOS")
        FilaDetalle("PESO")
        FilaDetalle("ESTATURA")

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0xFFE0E0E0), CircleShape)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.Black)
            }
            BotonNaranja(
                texto = "Next ›",
                onClick = {
                    navController.navigate(Pantallas.INICIO) {
                        popUpTo(Pantallas.GENERO) { inclusive = true }
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun FilaDetalle(etiqueta: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(etiqueta, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text("ESCOGE", color = AzulEnlace, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
    }
}
