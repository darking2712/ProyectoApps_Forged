package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja

data class PaginaOnboarding(
    val titulo: String,
    val color1: Color,
    val color2: Color,
    val etiqueta: String
)

@Composable
fun PantallaOnboarding(navController: NavController) {
    val paginas = listOf(
        PaginaOnboarding("EMPIEZA TU META", Color(0xFF004D57), Color(0xFF017A8A), "Levantamiento"),
        PaginaOnboarding("CREA UN PLAN DE EJERCICIO PARA MANTENERTE EN FORMAA", Color(0xFF1A2E2A), Color(0xFF2E5D54), "Kettlebell"),
        PaginaOnboarding("LA ACCIÓN ES LA LLAVE AL ÉXITO", Color(0xFF1A1A1A), Color(0xFF3E3E3E), "Kings Gym")
    )
    var actual by remember { mutableIntStateOf(0) }
    val pagina = paginas[actual]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.4f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(pagina.color1, pagina.color2))),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    pagina.etiqueta,
                    color = Color.White.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path().apply {
                    moveTo(0f, size.height)
                    lineTo(0f, size.height * 0.82f)
                    lineTo(size.width, size.height * 0.68f)
                    lineTo(size.width, size.height)
                    close()
                }
                drawPath(path, Color(0xFF141414))
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = pagina.titulo,
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(32.dp))

            IndicadoresPagina(total = paginas.size, actual = actual)

            Spacer(modifier = Modifier.height(24.dp))

            if (actual == paginas.lastIndex) {
                BotonNaranja(
                    texto = "EMPIEZA AHORA ›",
                    onClick = {
                        navController.navigate(Pantallas.LOGIN) {
                            popUpTo(Pantallas.ONBOARDING) { inclusive = true }
                        }
                    }
                )
            } else {
                BotonNaranja(
                    texto = "SIGUIENTE ›",
                    onClick = { actual++ },
                    color = Naranja
                )
            }
        }
    }
}
