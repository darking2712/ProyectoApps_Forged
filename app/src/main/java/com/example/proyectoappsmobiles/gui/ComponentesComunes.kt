package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.proyectoappsmobiles.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.ui.theme.RojoError
import com.example.proyectoappsmobiles.ui.theme.VerdeExito

data class ItemMenu(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector
)

val itemsMenu = listOf(
    ItemMenu(Pantallas.INICIO, "Inicio", Icons.Default.Home),
    ItemMenu(Pantallas.ESTADISTICAS, "Estadísticas", Icons.Default.BarChart),
    ItemMenu(Pantallas.ENTRENADOR, "Entrenador", Icons.Default.FitnessCenter),
    ItemMenu(Pantallas.RESERVAR, "Agendar", Icons.Default.CalendarMonth),
    ItemMenu(Pantallas.PAGOS, "Pagos", Icons.Default.CreditCard),
    ItemMenu(Pantallas.PERFIL, "Perfil", Icons.Default.Person)
)

@Composable
fun MenuInferior(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    if (itemsMenu.any { it.ruta == rutaActual }) {
        NavigationBar(
            containerColor = Color.White,
            contentColor = Naranja
        ) {
            itemsMenu.forEach { item ->
                NavigationBarItem(
                    selected = rutaActual == item.ruta,
                    onClick = {
                        if (rutaActual != item.ruta) {
                            navController.navigate(item.ruta) {
                                popUpTo(Pantallas.INICIO) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(item.icono, contentDescription = item.titulo)
                    },
                    label = {
                        Text(item.titulo, fontSize = 10.sp)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Naranja,
                        selectedTextColor = Naranja,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color(0xFFFFF3E0)
                    )
                )
            }
        }
    }
}

@Composable
fun BotonNaranja(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Naranja
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    ) {
        Text(texto, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun IconoExito(modifier: Modifier = Modifier, color: Color = VerdeExito) {
    Box(
        modifier = modifier
            .size(100.dp)
            .background(color, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
fun ImagenSpinning(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                listOf(Color(0xFF37474F), Color(0xFF546E7A), Color(0xFF263238))
            )
        ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Spinning",
                color = Color.White.copy(alpha = 0.7f),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ImagenEntrenador(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                listOf(Color(0xFF4E342E), Color(0xFF6D4C41), Color(0xFF3E2723))
            )
        ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = "Carlos Arturo",
                color = Color.White.copy(alpha = 0.7f),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BotonContorno(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, color),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    ) {
        Text(texto, color = color, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LogoActivity(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.forget),
        contentDescription = "Logo Forged",
        modifier = modifier.size(120.dp),
        contentScale = ContentScale.Fit
    )

            Text("FORGED", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("FITNESS APP", color = Color.White.copy(alpha = 0.7f), fontSize = 8.sp)
        }


@Composable
fun IndicadoresPagina(total: Int, actual: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(total) { indice ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(
                        width = if (indice == actual) 24.dp else 8.dp,
                        height = 8.dp
                    )
                    .background(
                        color = if (indice == actual) Naranja else Color.Gray.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun TiraDias(
    diaSeleccionado: String,
    onSeleccion: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val dias = listOf(
        "Lun" to "16", "Mar" to "17", "Mié" to "18", "Jue" to "19",
        "Vie" to "20", "Sáb" to "21", "Dom" to "22"
    )
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        dias.forEach { (dia, numero) ->
            val seleccionado = numero == diaSeleccionado
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        color = if (seleccionado) Naranja else Color.Transparent,
                        shape = CircleShape
                    )
                    .clickable { onSeleccion(numero) }
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Text(
                    dia,
                    fontSize = 11.sp,
                    color = if (seleccionado) Color.White else Color.Gray
                )
                Text(
                    numero,
                    fontWeight = FontWeight.Bold,
                    color = if (seleccionado) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun ImagenFondoGym(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                listOf(Color(0xFF1A1A1A), Color(0xFF2C2C2C), Color(0xFF1A1A2E))
            )
        ),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            imageVector = Icons.Default.FitnessCenter,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.08f),
            modifier = Modifier.size(280.dp)
        )
    }
}
