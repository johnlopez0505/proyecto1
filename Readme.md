# Explicación de la App 

## Clase ActivityPrincipal

La clase `ActivityPrincipal` es una actividad de Android que actúa como la pantalla principal de la aplicación. La 
actividad se inicia con una serie de botones que realizan diferentes acciones cuando se hace clic en ellos. A 
continuación, se explica el funcionamiento de esta clase:

```kotlin
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
            intent = Intent(this, ActivitySecond::class.java).apply {
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
```

- `private lateinit var buttonCall: ImageButton`: Declara una variable para un botón de llamada de emergencia.
- `private lateinit var buttonUrl: ImageButton`: Declara una variable para un botón que abre una URL.
- `private lateinit var buttonAlarm: ImageButton`: Declara una variable para un botón que configura una alarma.
- `private lateinit var buttonEmail: ImageButton`: Declara una variable para un botón que inicia la composición de 
   un correo electrónico.
- `private lateinit var intent: Intent`: Declara una variable de tipo `Intent` para manejar las acciones.

Luego, en el método `onCreate`, se establece el contenido de la actividad y se inicializan los eventos de clic en los 
botones mediante el método `initEvent()`.

- `buttonCall.setOnClickListener { ... }`: Configura un evento de clic para el botón de llamada de emergencia. Cuando 
   se hace clic, crea un intent para iniciar otra actividad llamada `ActivitySecond` y pasa un mensaje "LLamar a 
   Emergencias" como dato adicional.
- `buttonUrl.setOnClickListener { ... }`: Configura un evento de clic para el botón de apertura de URL. Cuando se 
   hace clic, crea un intent para abrir una URL en un navegador web.
- `buttonAlarm.setOnClickListener { ... }`: Configura un evento de clic para el botón de configuración de alarma. 
   Cuando se hace clic, crea un intent para configurar una alarma con un mensaje y hora especificados.
- `buttonEmail.setOnClickListener { ... }`: Configura un evento de clic para el botón de composición de correo 
   electrónico. Cuando se hace clic, crea un intent para enviar un correo electrónico a la dirección 
  "clase2Dam@educand.es" con un asunto y contenido específicos.

## Clase ActivitySecond:

La clase `ActivitySecond` es otra actividad de la aplicación que se inicia cuando se hace clic en el botón de llamada 
de emergencia en la actividad principal. Esta actividad se encarga de mostrar el nombre del contacto al que se va a 
llamar y permite al usuario realizar la llamada. A continuación, se explica el funcionamiento de esta clase:


```kotlin
class ActivitySecond : AppCompatActivity() {
    private lateinit var buttonCall2: ImageButton
    private lateinit var txtName: TextView
    companion object{
        const val PHONE = "623260768"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
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
```

- `private lateinit var buttonCall2: ImageButton`: Declara una variable para un botón de llamada.
- `private lateinit var txtName: TextView`: Declara una variable para un elemento de texto que muestra el nombre del 
   contacto.

En el método `onCreate`, se establece el contenido de la actividad y se inicializan los eventos de clic en los botones 
mediante el método `initEvent()`. Luego, se muestra el nombre del contacto utilizando el método `showData()`.

- `showData()`: Recupera el nombre del contacto pasado como dato adicional desde la actividad principal y lo muestra en
   el elemento de texto.
- `buttonCall2.setOnClickListener { ... }`: Configura un evento de clic para el botón de llamada. Cuando se hace clic,
   verifica los permisos de llamada y realiza la llamada si los permisos son válidos.

Además, la clase `ActivitySecond` maneja la solicitud de permisos de llamada y muestra un mensaje si los permisos no 
se conceden.

# Descripción del Archivo Manifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="com.android.alarm.permission.SET_ALARM"/>
    <uses-feature
        android:name="android.hardware.telephony"
        android:required="false" />
    <uses-permission android:name="android.permission.CALL_PHONE" />


    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/logo"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/logo_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Proyecto1"
        tools:targetApi="31">
        <activity
            android:name=".ActivitySecond"
            android:exported="false" />
        <activity
            android:name=".ActivityPrincipal"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>

</manifest>
```

El archivo de manifiesto de una aplicación de Android. El archivo de manifiesto es un 
componente fundamental de cualquier aplicación de Android y proporciona información importante sobre la aplicación, 
sus permisos y sus actividades. A continuación, se describe lo que se realizó en este archivo manifest:

## Permisos
La sección de permisos (`<uses-permission>`) enumera los permisos que la aplicación requiere para funcionar 
correctamente. Estos permisos son necesarios para que la aplicación acceda a ciertas características del dispositivo. 
En este archivo manifest, se han especificado los siguientes permisos:

1. `android.permission.INTERNET`: Permite que la aplicación acceda a Internet.
2. `com.android.alarm.permission.SET_ALARM`: Permite que la aplicación configure alarmas en el dispositivo.
3. `android.permission.CALL_PHONE`: Permite que la aplicación realice llamadas telefónicas.

## Características de Hardware
La sección `<uses-feature>` se utiliza para declarar las características de hardware que la aplicación puede requerir.
En este caso, se declara que la aplicación no requiere hardware de telefonía, ya que la propiedad `android:required` se
establece en "false".

## Aplicación
La sección de la aplicación (`<application>`) contiene información específica de la aplicación. Aquí se detallan 
algunas de las configuraciones clave:

- `android:allowBackup="true"`: Permite realizar copias de seguridad de los datos de la aplicación.
- `android:dataExtractionRules="@xml/data_extraction_rules"`: Hace referencia a un archivo XML llamado 
  "data_extraction_rules".
- `android:fullBackupContent="@xml/backup_rules"`: Hace referencia a un archivo XML llamado "backup_rules".
- `android:icon="@mipmap/logo"`: Establece el icono de la aplicación.
- `android:label="@string/app_name"`: Define el nombre de la aplicación.
- `android:roundIcon="@mipmap/logo_round"`: Establece un icono redondo para la aplicación.
- `android:supportsRtl="true"`: Indica que la aplicación admite texto en dirección de derecha a izquierda (RTL).
- `android:theme="@style/Theme.Proyecto1"`: Asigna un tema personalizado a la aplicación.
- `tools:targetApi="31"`: Define la versión de destino de la API de Android para herramientas de desarrollo.

## Actividades
La sección de actividades (`<activity>`) declara las actividades que componen la aplicación. Cada actividad tiene 
un nombre, y en este archivo manifest se declaran dos actividades: **ActivitySecond** y **ActivityPrincipal**. Se 
utiliza un `<intent-filter>` para marcar la actividad **ActivityPrincipal** como la actividad principal de inicio de 
la aplicación.

- `android:name`: El nombre de la actividad.
- `android:exported`: Indica si la actividad puede ser accedida por otras aplicaciones. "true" significa que la 
- actividad es accesible desde fuera de la aplicación, mientras que "false" significa que no es accesible.

En resumen, este archivo manifest define los permisos, las características del hardware, las configuraciones de la 
aplicación y las actividades que componen la aplicación Android. Es esencial para que el sistema operativo Android 
comprenda cómo interactuar con la aplicación y qué permisos son necesarios para su funcionamiento.

#### Dejo el enlace al repositorio
[Repositorio: https://github.com/johnlopez0505/proyecto1.git](https://github.com/johnlopez0505/proyecto1.git)