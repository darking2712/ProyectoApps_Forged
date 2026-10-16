package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
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
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.AmarilloGradiente
import com.example.proyectoappsmobiles.ui.theme.Cian
import com.example.proyectoappsmobiles.ui.theme.Naranja

@Composable
fun PantallaRenovar(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text("RENOVAR", fontWeight = FontWeight.Black, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(Cian.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Cian.copy(alpha = 0.25f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Notifications,
                        null,
                        tint = AmarilloGradiente,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text("Tu membresía vence en", color = Color.Gray, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("7 Días", color = Naranja, fontSize = 56.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "No pierdad el acceso a tus beneficios",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            BotonNaranja(
                texto = "Renovar ahora",
                onClick = { navController.navigate(Pantallas.PAGO) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            BotonContorno(
                texto = "Más tarde",
                onClick = {
                    navController.navigate(Pantallas.PAGOS) {
                        popUpTo(Pantallas.INICIO)
                    }
                }
            )
        }
    }
}
