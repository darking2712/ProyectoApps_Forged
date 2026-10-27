package com.example.proyectoappsmobiles.gui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.R
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

fun obtenerImagenClase(nombreClase: String): Int{
        return when {
            nombreClase.contains("spinning", ignoreCase = true) -> R.drawable.spinning
            nombreClase.contains("yoga", ignoreCase = true) -> R.drawable.yoga
            nombreClase.contains("Entrenamiento Funcional", ignoreCase = true) -> R.drawable.funcional
            else -> R.drawable.spinning
            }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalleClase(
    navController: NavController,
    claseId: Int,
    viewModel: AppViewModel = viewModel()
) {
    val context = LocalContext.current
    val clases by viewModel.clases.collectAsState(initial = emptyList())
    val clase = clases.find { it.id == claseId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DETALLES DE LA CLASE", color = Color.White, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Naranja)
            )
        }
    ) { padding ->
        if (clase == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Clase no encontrada")
            }
            return@Scaffold
        }
        val imagenRes = obtenerImagenClase(clase.nombre)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imagenRes),
                contentDescription = "Imagen de ${clase.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { navController.navigate(Pantallas.RUTINA) },
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(clase.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Horario: ${clase.dia}: ${clase.horario}", color = Color.DarkGray, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, null, tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Entrenador: ${clase.entrenador}", color = Color.DarkGray, fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Cupos: ${clase.cuposOcupados}/${clase.cuposTotal} cupos",
                color = Naranja,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(clase.descripcion, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(24.dp))

            BotonNaranja(
                texto = "Reservar clase",
                onClick = {
                    viewModel.reservarClaseGrupal(clase.id, "Lun, 18 sep", "6:00 pm") { exito ->
                        if (exito) {
                            // Pasamos el ID de la clase como argumento en la navegación
                            navController.navigate("${Pantallas.CONFIRMACION_RESERVA_GRUPAL}/${clase.id}") {
                                popUpTo(Pantallas.INICIO)
                            }
                        } else {
                            Toast.makeText(context, "No hay cupos disponibles", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }
}
