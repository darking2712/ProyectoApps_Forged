package com.example.proyectoappsmobiles.gui

import android.widget.Toast
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja
import com.example.proyectoappsmobiles.viewmodel.AppViewModel

@Composable
fun PantallaLogin(navController: NavController, viewModel: AppViewModel = viewModel()) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mostrarPassword by remember { mutableStateOf(false) }

    val emailValido = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF1A1A2E), Color(0xFF16213E), Color(0xFF0F0F1A))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Remo con mancuerna", color = Color.White.copy(alpha = 0.35f), fontSize = 18.sp)
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path().apply {
                    moveTo(0f, size.height)
                    lineTo(0f, size.height * 0.75f)
                    lineTo(size.width, size.height * 0.55f)
                    lineTo(size.width, size.height)
                    close()
                }
                drawPath(path, Color.White)
            }
        }

        Column(
            modifier = Modifier
                .weight(1.3f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                PestañaAuth(
                    texto = "Login",
                    activa = true,
                    onClick = { },
                    modifier = Modifier.weight(1f)
                )
                PestañaAuth(
                    texto = "Sign up",
                    activa = false,
                    onClick = { navController.navigate(Pantallas.REGISTRO) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Bienvenido de nuevo Forged", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))

            CampoSubrayado(
                valor = email,
                etiqueta = "Email",
                onValorChange = { email = it },
                keyboardType = KeyboardType.Email,
                trailing = {
                    if (emailValido) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .background(Naranja, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(14.dp))
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            CampoSubrayado(
                valor = password,
                etiqueta = "Password",
                onValorChange = { password = it },
                esPassword = true,
                mostrarPassword = mostrarPassword,
                onAlternarPassword = { mostrarPassword = !mostrarPassword }
            )

            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    "Forgot Password",
                    color = Naranja,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable { navController.navigate(Pantallas.OLVIDE_PASSWORD) }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BotonNaranja(
                texto = "Login ›",
                onClick = {
                    viewModel.login(email, password) { exito ->
                        if (exito || emailValido) {
                            Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                            navController.navigate(Pantallas.INICIO) {
                                popUpTo(Pantallas.LOGIN) { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PestañaAuth(
    texto: String,
    activa: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            texto,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = if (activa) Color.Black else Color.Gray
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(if (activa) Naranja else Color.Transparent)
        )
    }
}

@Composable
fun CampoSubrayado(
    valor: String,
    etiqueta: String,
    onValorChange: (String) -> Unit,
    esPassword: Boolean = false,
    mostrarPassword: Boolean = false,
    onAlternarPassword: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailing: (@Composable () -> Unit)? = null
) {
    TextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(etiqueta) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (esPassword && !mostrarPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (esPassword && onAlternarPassword != null) {
                IconButton(onClick = onAlternarPassword) {
                    Icon(
                        if (mostrarPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        null,
                        tint = Color.Gray
                    )
                }
            } else {
                trailing?.invoke()
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Naranja,
            unfocusedIndicatorColor = Color.LightGray,
            focusedLabelColor = Naranja,
            unfocusedLabelColor = Color.Gray,
            cursorColor = Naranja
        )
    )
}
