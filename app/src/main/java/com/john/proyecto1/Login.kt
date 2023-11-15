package com.john.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            validarCredenciales()
        }
    }

    private fun validarCredenciales() {
        val usuario    = editTextUsername.text.toString()
        val contraseña = editTextPassword.text.toString()

        val MYUSER = "john"  // tu usuario
        val MYPASS = "1234"  // tu contraseña

        if (usuario == MYUSER && contraseña == MYPASS) {
            // Credenciales válidas, iniciar Activity principal
            val intent = Intent(this, ActivityPrincipal::class.java).apply {
                putExtra("name", "Bienvenido: $usuario")  // Pasar el usuario como argumento al
                // Activity principal
            }
            startActivity(intent)
        } else {
            // Credenciales no válidas, mostrar Toast
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
}
