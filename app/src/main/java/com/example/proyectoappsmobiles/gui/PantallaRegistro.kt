package com.example.proyectoappsmobiles.gui

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.ui.theme.RojoError
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

@Composable
fun PantallaRegistro(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }

    val emailValido = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val mostrarErrorEmail = email.isNotBlank() && !emailValido

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF3E2723), Color(0xFF5D4037), Color(0xFF211511))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Press con mancuernas", color = Color.White.copy(alpha = 0.35f), fontSize = 18.sp)
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path().apply {
                    moveTo(0f, size.height)
                    lineTo(0f, size.height * 0.8f)
                    lineTo(size.width, size.height * 0.55f)
                    lineTo(size.width, size.height)
                    close()
                }
                drawPath(path, Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                PestañaAuth(
                    texto = "Login",
                    activa = false,
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                )
                PestañaAuth(
                    texto = "Sign up",
                    activa = true,
                    onClick = { },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Hola!", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Ingresa tu información abajo o inicia sesión con otra cuenta",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            CampoSubrayado(
                valor = nombre,
                etiqueta = "Nombre completo",
                onValorChange = { nombre = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CampoSubrayado(
                valor = email,
                etiqueta = "Email",
                onValorChange = { email = it },
                keyboardType = KeyboardType.Email
            )
            if (mostrarErrorEmail) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Cancel, null, tint = RojoError, modifier = Modifier.height(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        "Haz intentado con un correo invalido intenta de nuevo",
                        color = RojoError,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CampoSubrayado(
                valor = password,
                etiqueta = "Password",
                onValorChange = { password = it },
                esPassword = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            CampoSubrayado(
                valor = passwordAgain,
                etiqueta = "Password again",
                onValorChange = { passwordAgain = it },
                esPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            BotonNaranja(
                texto = "Sign up ›",
                onClick = {
                    when {
                        nombre.isBlank() || email.isBlank() || password.isBlank() ->
                            Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                        !emailValido ->
                            Toast.makeText(context, "Correo inválido", Toast.LENGTH_SHORT).show()
                        password != passwordAgain ->
                            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                        else -> viewModel.registrar(nombre, email, password) { exito ->
                            if (exito) {
                                navController.navigate(Pantallas.VERIFICACION)
                            } else {
                                Toast.makeText(context, "Error al registrar", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    "¿Ya tienes cuenta? Iniciar Sesión",
                    color = Naranja,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(Pantallas.LOGIN) {
                            popUpTo(Pantallas.REGISTRO) { inclusive = true }
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
