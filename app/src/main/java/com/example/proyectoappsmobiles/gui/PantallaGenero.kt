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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja

@Composable
fun PantallaGenero(navController: NavController) {
    var genero by remember { mutableStateOf("M") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text(
            "CUENTANOS SOBRE TI",
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "PARA BRINDARTE UNA MEJOR EXPERIENCIA CUENTANOS CUÁL ES TU GENERO",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OpcionGenero(
                simbolo = "\u2642",
                seleccionado = genero == "M",
                onClick = { genero = "M" }
            )
            OpcionGenero(
                simbolo = "\u2640",
                seleccionado = genero == "F",
                onClick = { genero = "F" }
            )
        }

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
                    navController.navigate(Pantallas.DETALLES_PERFIL)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun OpcionGenero(simbolo: String, seleccionado: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(140.dp)
            .background(
                color = if (seleccionado) Naranja else Color(0xFF333333),
                shape = CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = simbolo,
            fontSize = 72.sp,
            color = if (seleccionado) Color.Black else Color.White,
            textAlign = TextAlign.Center
        )
    }
}
