package com.example.proyectoappsmobiles.gui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja

@Composable
fun PantallaPago(navController: NavController) {
    var metodo by remember { mutableIntStateOf(0) }
    var tarjeta by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var titular by remember { mutableStateOf("") }
    var guardar by remember { mutableStateOf(true) }
    val metodos = listOf("Tarjeta", "PayPal", "Tarjeta")

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text("PAGO SEGURO", fontWeight = FontWeight.Black, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Completa tu pago de forma segura", color = Color.Gray, fontSize = 13.sp)

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Plan premium", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("$.......", color = Naranja, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Método de pago", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            metodos.forEachIndexed { indice, nombre ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { metodo = indice }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CasillaPago(seleccionado = metodo == indice) { metodo = indice }
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(nombre)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CampoPago("Número de tarjeta", tarjeta) { tarjeta = it }
            Spacer(modifier = Modifier.height(12.dp))
            CampoPago("Fecha de expedición", fecha) { fecha = it }
            Spacer(modifier = Modifier.height(12.dp))
            CampoPago("CVV", cvv) { cvv = it }
            Spacer(modifier = Modifier.height(12.dp))
            CampoPago("Nombre en la tarjeta", titular) { titular = it }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { guardar = !guardar }
            ) {
                CasillaPago(seleccionado = guardar) { guardar = !guardar }
                Spacer(modifier = Modifier.size(8.dp))
                Text("Guardar tarjeta para próximos pagos", fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            BotonNaranja(
                texto = "Pagar $........",
                onClick = {
                    navController.navigate(Pantallas.CONFIRMACION_PAGO) {
                        popUpTo(Pantallas.PAGO) { inclusive = true }
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CasillaPago(seleccionado: Boolean, onClick: () -> Unit) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        border = BorderStroke(2.dp, if (seleccionado) Naranja else Color.LightGray),
        shape = RoundedCornerShape(6.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        modifier = Modifier.size(26.dp)
    ) {
        if (seleccionado) {
            Icon(Icons.Default.Check, null, tint = Naranja, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun CampoPago(etiqueta: String, valor: String, onValorChange: (String) -> Unit) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(etiqueta) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Naranja,
            unfocusedBorderColor = Naranja,
            focusedLabelColor = Naranja,
            unfocusedLabelColor = Color.Gray,
            cursorColor = Naranja
        )
    )
}
