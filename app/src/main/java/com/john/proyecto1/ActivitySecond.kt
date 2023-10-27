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

class ActivitySecond : AppCompatActivity() {
    private lateinit var buttonCall2: ImageButton
    private lateinit var txtName: TextView
    companion object{
        const val PHONE = "623260768"
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
        if (Build.VERSION. SDK_INT >= Build.VERSION_CODES. M){
            if (PermissionPhone()){
                call()
            }
            else{
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }else{
            call()
        }
    }

    private fun call() {
        val intent = Intent(Intent. ACTION_CALL).apply {
            data = Uri.parse( "tel:$PHONE")
        }
        startActivity(intent)
    }


    private fun PermissionPhone(): Boolean = ContextCompat.checkSelfPermission( this,
        Manifest.permission.CALL_PHONE) == PackageManager. PERMISSION_GRANTED



    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.
        RequestPermission()) {  isGranted ->
        if (isGranted) {
            call()
        } else {
            Toast.makeText(
                this, "Necesitas habilitar los permisos", Toast.LENGTH_LONG).show()
        }
    }
}


