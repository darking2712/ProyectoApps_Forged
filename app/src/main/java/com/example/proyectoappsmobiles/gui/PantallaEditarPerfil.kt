package com.example.proyectoappsmobiles.gui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.ui.theme.Naranja

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarPerfil(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("Maria Jose Cardona") }
    var email by remember { mutableStateOf("Maria@gmail.com") }
    var telefono by remember { mutableStateOf("3135678901") }
    var fecha by remember { mutableStateOf("12/03/2000") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("EDITAR PERFIL", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(
                        Brush.verticalGradient(listOf(Color(0xFF9E9E9E), Color(0xFF616161))),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.FitnessCenter, null, tint = Color.White, modifier = Modifier.size(56.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            CampoEdicion("Nombre completo", nombre) { nombre = it }
            Spacer(modifier = Modifier.height(16.dp))
            CampoEdicion("Email", email) { email = it }
            Spacer(modifier = Modifier.height(16.dp))
            CampoEdicion("Teléfono", telefono) { telefono = it }
            Spacer(modifier = Modifier.height(16.dp))
            CampoEdicion("Fecha de nacimiento", fecha) { fecha = it }

            Spacer(modifier = Modifier.height(32.dp))

            BotonNaranja(
                texto = "Guardar Cambios",
                onClick = {
                    Toast.makeText(context, "Cambios guardados", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
private fun CampoEdicion(etiqueta: String, valor: String, onValorChange: (String) -> Unit) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(etiqueta) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Naranja,
            unfocusedLabelColor = Color.Gray,
            focusedContainerColor = Color(0xFF2A2A2A),
            unfocusedContainerColor = Color(0xFF2A2A2A),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Naranja
        )
    )
}
