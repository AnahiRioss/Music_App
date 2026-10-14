package com.example.music_app4a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegistrar: Button


    //CREAR OBJETO DE LA CLASE DE LA BASE DE DATOS
    private lateinit var dbHelper: DatabaseHelper




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        //HAREMOS LA CONEXION

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword)

        btnRegistrar = findViewById(R.id.btnRegistrar)


        //INICIAR EL OBJETO DE LA BASE DE DATOS
        dbHelper = DatabaseHelper(this)


        //EVENTO PARA REGISTRAR A UN USUARIO
        btnRegistrar.setOnClickListener {
            //LLamar al metodo de registrar usuario
            registrarUsuario()

        }



    }

    //METODO PARA REGISTRAR A UN USUARIO
    private fun registrarUsuario() {

        //Obtener la informacion que escribe el usario
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()

        //Validar que los campos tengan iformacion
        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        //Llamar a nuestra base de datos y llamar a la insderccion
        val resultado = dbHelper.registrarUsuario(nombre, correo, password)
        if (resultado){
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
            //Regresar al usuario
            finish()


        }else{
            Toast.makeText(this, "Usuario no registradi", Toast.LENGTH_SHORT).show()


        }






    }
}