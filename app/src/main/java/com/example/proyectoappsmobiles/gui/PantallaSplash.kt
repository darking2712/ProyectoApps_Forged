package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.viewmodel.AppViewModel
import kotlinx.coroutines.delay

@Composable
fun PantallaSplash(navController: NavController, viewModel: AppViewModel) {
    var tiempoCumplido by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1800)
        tiempoCumplido = true
    }

    if (tiempoCumplido && viewModel.sesionRestaurada) {
        val destino = if (viewModel.usuarioActual != null) {
            Pantallas.INICIO
        } else {
            Pantallas.ONBOARDING
        }

        LaunchedEffect(destino) {
            navController.navigate(destino) {
                popUpTo(Pantallas.SPLASH) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2A2A2A)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogoActivity()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "FORGED",
            color = Color.White,
            fontSize = 44.sp,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.SansSerif
        )
    }
}
