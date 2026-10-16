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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.proyectoappsmobiles.ui.theme.AzulEnlace
import com.example.proyectoappsmobiles.ui.theme.Naranja

@Composable
fun PantallaVerificacion(navController: NavController) {
    var codigo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(Color(0xFFF0F0F0), CircleShape)
                .clickable { navController.popBackStack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("Verificación", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Mira tu email, hemos encviado un código de verificación",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(6) { indice ->
                val digito = codigo.getOrNull(indice)
                val lleno = digito != null
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (lleno) {
                            Text(
                                text = digito.toString(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(Color.Gray, CircleShape)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(if (lleno) AzulEnlace else Color.LightGray)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = codigo,
            onValueChange = { if (it.length <= 6) codigo = it.filter { c -> c.isDigit() } },
            label = { Text("Código de verificación") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Naranja,
                cursorColor = Naranja
            )
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("No has recibido ningún código", color = Naranja, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        BotonNaranja(
            texto = "Verify",
            onClick = {
                navController.navigate(Pantallas.GENERO) {
                    popUpTo(Pantallas.REGISTRO) { inclusive = true }
                }
            }
        )
    }
}
