package com.john.proyecto1

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.Manifest;
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class activitySecund : AppCompatActivity() {
    private lateinit var buttonCall2: ImageButton
    private lateinit var txtName: TextView
    companion object{
        const val PHONE = "623260769"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secund)
        initEvent()
        showData()
    }

    private fun showData() {
        txtName = findViewById(R.id.marcar)
        val name = getIntent().getStringExtra("name")
        txtName.text = name
        Toast.makeText(this, "Datos mostrados con éxito", Toast.LENGTH_LONG).show()
    }

    private fun initEvent() {
        buttonCall2 = findViewById(R.id.btn_llamar)
        buttonCall2.setOnClickListener {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        if (Build.VERSION. SDK_INT >= Build.VERSION_CODES. M){ //Tenemos que ver si pedimos permisos
            if (PermissionPhone()){ //Hay que solicitar permisos. Si ya concedimos los permisos
                call() //Realizamos la llamada.
            }
            else{ //Tenemos que solicitar al usuario los permisos.
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }else{
            call() //No es necesario solicitar permisos, ya que es una API < 23
        }
    }

    private fun call() {
        val intent = Intent(Intent. ACTION_CALL).apply {
            data = Uri.parse( "tel:$PHONE") //No me complico, es el mismo teléfono.
        }
        startActivity(intent)
    }

    //Devuelve true si el usuario permitió los permisos de llamada.
    private fun PermissionPhone(): Boolean = ContextCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE) == PackageManager. PERMISSION_GRANTED



    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {  isGranted ->
        if (isGranted) {
            call()
        } else {
            Toast.makeText(
                this, "Necesitas habilitar los permisos", Toast.LENGTH_LONG).show()
        }
    }
}


