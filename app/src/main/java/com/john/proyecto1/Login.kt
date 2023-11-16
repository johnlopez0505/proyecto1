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
    private lateinit var buttonRegistrar : Button
    private lateinit var buttonLogin     : Button
    companion object{
        const val MYUSER = "john" // tu usuario
        const val MYPASS = "1234" // tu contraseña
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initEvent()
    }

    private fun initEvent() {
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegistrar  = findViewById(R.id.button_registrar)
        buttonLogin      = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            validarCredenciales()
        }
    }

    private fun validarCredenciales() {
        val user     = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()

        if (user == MYUSER && password == MYPASS) {
            // Credenciales válidas, iniciar Activity principal
            val intent = Intent(this, ActivityPrincipal::class.java).apply {
                putExtra("name", "Bienvenido: $user")  // Pasamos el usuario como argumento al
                // Activity principal
            }
            startActivity(intent)
        } else {
            // Credenciales no válidas, mostrar Toast
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
}
