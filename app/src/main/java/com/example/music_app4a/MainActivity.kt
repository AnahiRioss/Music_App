package com.example.music_app4a

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var etCorreo: EditText
    private lateinit var etPass: EditText
    private lateinit var btnInicio: Button
    private lateinit var dpHelper : DatabaseHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)

        etCorreo = findViewById(R.id.etCorreo)
        etPass = findViewById(R.id.etPassword)
        btnInicio = findViewById(R.id.btnIngresar)

        dpHelper = DatabaseHelper(this)


        //evento de para iniciar sesion
        btnInicio.setOnClickListener {

            iniciarSesion()

        }





        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }





    }



    //Metodo para iniciar sesion
    private fun iniciarSesion(){

        //accedemos a la informacion que escribe el usuario
         val correo = etCorreo.text.toString().trim()
         val pass = etPass.text.toString().trim()

        if (correo.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        //LLAMAR AL METODO DE INCIO DE SESION EN LA BD
        val resultado = dpHelper.iniciarSesion(correo, pass)
        if (resultado){
            // si la sesion es conrrecta
            Toast.makeText(this, "Bienvenido :3", Toast.LENGTH_SHORT).show()

            //mandar a otra pantalla opcional
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            //cerrar la pantalla actual
            finish()

        }else{
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
        }



    }
}