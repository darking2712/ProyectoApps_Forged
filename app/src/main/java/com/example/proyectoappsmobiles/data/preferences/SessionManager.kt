package com.example.proyectoappsmobiles.data.preferences

import android.content.Context

/**
 * Guarda en SharedPreferences el email del usuario con sesión iniciada.
 * No se almacena la contraseña: el usuario se recupera desde Room por su email.
 */
class SessionManager(context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun guardarSesion(email: String) {
        prefs.edit().putString(KEY_EMAIL, email).apply()
    }

    fun obtenerEmailSesion(): String? = prefs.getString(KEY_EMAIL, null)

    fun haySesion(): Boolean = !obtenerEmailSesion().isNullOrBlank()

    fun cerrarSesion() {
        prefs.edit().remove(KEY_EMAIL).apply()
    }

    private companion object {
        const val PREFS_NAME = "sesion_usuario"
        const val KEY_EMAIL = "email_usuario"
    }
}
