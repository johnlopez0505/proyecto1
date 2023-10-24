package com.john.proyecto1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class ActivityPrincipal : AppCompatActivity() {

    private lateinit var buttonCall  : ImageButton
    private lateinit var buttonUrl   : ImageButton
    private lateinit var buttonAlarm : ImageButton
    private lateinit var buttonEmail : ImageButton
    private lateinit var intent : Intent
    companion object{
        const val url = "https://www.google.com/"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        initEvent()
    }

    private fun initEvent() {
        buttonCall = findViewById(R.id.btn_call)
        buttonUrl = findViewById(R.id.btn_url)
        buttonAlarm = findViewById(R.id.btn_alarma)
        buttonEmail = findViewById(R.id.btn_email)
        val message = "despertar"
        val hour = 7
        val minutes = 30

        buttonCall.setOnClickListener {
            intent = Intent(this, activitySecund::class.java).apply {
                putExtra("name", "LLamar a Emergencias")
            }
            startActivity(intent)
        }

        buttonUrl.setOnClickListener{
            intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            startActivity(intent)
        }

        buttonAlarm.setOnClickListener{
            intent = Intent(AlarmClock.ACTION_SET_ALARM).apply{
                putExtra(AlarmClock.EXTRA_MESSAGE,message)
                putExtra(AlarmClock.EXTRA_HOUR,hour)
                putExtra(AlarmClock.EXTRA_MINUTES,minutes)
            }
            startActivity(intent)
        }


        val subject = "saludo"
        val content = "Hola clase PMP 23/24 "
        buttonEmail.setOnClickListener{
            intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","clase2Dam@educand.es",null)).apply {
                putExtra(Intent.EXTRA_SUBJECT,subject)
                putExtra(Intent.EXTRA_TEXT,content)
            }
            startActivity(intent)
        }

    }
}