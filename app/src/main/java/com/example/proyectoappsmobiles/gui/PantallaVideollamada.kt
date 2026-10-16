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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.ui.theme.RojoError
import kotlinx.coroutines.delay

@Composable
fun PantallaVideollamada(navController: NavController) {
    var segundos by remember { mutableIntStateOf(32 * 60 + 15) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            segundos++
        }
    }

    val cronometro = "%02d:%02d:%02d".format(segundos / 3600, (segundos % 3600) / 60, segundos % 60)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text("ENTRENAMIENTO EN CURSO", color = Color.White, fontWeight = FontWeight.Black, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(RojoError, RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text("En vivo", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(cronometro, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.verticalGradient(listOf(Color(0xFF263238), Color(0xFF455A64)))
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(96.dp)
                    .background(Color.White.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.size(52.dp))
            }
            Text(
                "Diego Ramirez",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(88.dp, 116.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1A1A1A)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, null, tint = Color.White.copy(alpha = 0.6f))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ControlLlamada(Icons.Default.Mic, Color(0xFF2A2A2A)) { }
            ControlLlamada(Icons.Default.Videocam, Color(0xFF2A2A2A)) { }
            ControlLlamada(Icons.Default.CallEnd, RojoError) {
                navController.navigate(Pantallas.FINALIZACION) {
                    popUpTo(Pantallas.INICIO)
                }
            }
            ControlLlamada(Icons.AutoMirrored.Filled.Chat, Color(0xFF2A2A2A)) { }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Información de la sesión", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Diego Ramirez", color = Color.White, fontWeight = FontWeight.Bold)
        Text("Entrenador Personal", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
        Text("4.8 (150 reseñas)", color = Naranja, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.FitnessCenter, null, tint = Naranja, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                "Objetivo: Fuerza y resistencia - tren superior",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun ControlLlamada(icono: ImageVector, color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(color, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icono, null, tint = Color.White, modifier = Modifier.size(26.dp))
    }
}
