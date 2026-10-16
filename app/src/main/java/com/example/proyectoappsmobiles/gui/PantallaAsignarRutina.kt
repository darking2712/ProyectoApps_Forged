package com.example.proyectoappsmobiles.gui

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoappsmobiles.navigation.Pantallas
import com.example.proyectoappsmobiles.ui.theme.Naranja

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAsignarRutina(navController: NavController) {
    val context = LocalContext.current
    var usuario by remember { mutableStateOf("Maria jose Cardona") }
    var expandido by remember { mutableStateOf(false) }
    var tipoRutina by remember { mutableStateOf("Fuerza") }
    val tipos = listOf("Fuerza", "Hipertrofia", "Resistencia")
    val dias = listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sáb", "Dom")
    val diasSeleccionados = remember { mutableStateOf(setOf("Lun", "Mar", "Vie")) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("ASIGNAR RUTINA", fontSize = 22.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(20.dp))

            ExposedDropdownMenuBox(
                expanded = expandido,
                onExpandedChange = { expandido = !expandido }
            ) {
                OutlinedTextField(
                    value = usuario,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Selecciona usuario") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(androidx.compose.material3.ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                )
                DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
                    DropdownMenuItem(
                        text = { Text("Maria jose Cardona") },
                        onClick = {
                            usuario = "Maria jose Cardona"
                            expandido = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Ana Torres") },
                        onClick = {
                            usuario = "Ana Torres"
                            expandido = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Tipo de rutina", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            tipos.forEach { tipo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { tipoRutina = tipo }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = tipoRutina == tipo,
                        onClick = { tipoRutina = tipo },
                        colors = RadioButtonDefaults.colors(selectedColor = Naranja)
                    )
                    Text(tipo)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Días de entrenamiento", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                dias.forEach { dia ->
                    val activo = diasSeleccionados.value.contains(dia)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (activo) Naranja else Color(0xFFE0E0E0),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                diasSeleccionados.value = if (activo) {
                                    diasSeleccionados.value - dia
                                } else {
                                    diasSeleccionados.value + dia
                                }
                            }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            dia,
                            color = if (activo) Color.White else Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            BotonNaranja(
                texto = "Guardar Cambios",
                onClick = {
                    Toast.makeText(context, "Rutina asignada a $usuario", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            )
        }
    }
}
