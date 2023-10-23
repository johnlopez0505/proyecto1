package com.john.proyecto1

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ActivityPrincipal : AppCompatActivity() {


    private lateinit var buttonCall: ImageButton
    private lateinit var buttonUrl : ImageButton
    private lateinit var intent : Intent
    companion object{
        const val query = "google"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        initEvent()
    }

    private fun initEvent() {
        buttonCall = findViewById(R.id.btn_call)

        buttonCall.setOnClickListener { view ->
            intent = Intent(this, activitySecund::class.java).apply {
                putExtra("name", "LLamar a Emergencias")
            }
            startActivity(intent)
        }
        buttonUrl = findViewById(R.id.btn_url)
        buttonUrl.setOnClickListener{view ->
            intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                intent.putExtra(SearchManager.QUERY,query)
            }
            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }
    }
}