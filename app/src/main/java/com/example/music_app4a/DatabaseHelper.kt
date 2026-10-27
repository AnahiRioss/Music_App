package com.example.music_app4a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,

) {
    companion object {
        private const val DATABASE_NAME = "musiC.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USUARIOS = "usuarios"
        private const val COL_ID = "id"
        private const val COL_Nombre = "nombre"
        private const val COL_CORREO = "correo"
        private const val COL_PASSWORD = "password"
    }

    //METODO PARA CREAR LA BASE DE DATOS
    override fun onCreate(p0: SQLiteDatabase?) {

        val crearTablaUsuarios = """
            CREATE TABLE $TABLE_USUARIOS (
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_Nombre TEXT NOT NULL,
            $COL_CORREO TEXT NOT NULL UNIQUE,
            $COL_PASSWORD TEXT NOT NULL)
            """.trimIndent()


        //CREAR TABLA DE SQL
        p0?.execSQL(crearTablaUsuarios)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(p0)

    }

    //METODO PARA INSERTAR UN REGISTRO
    fun registrarUsuario(nombre: String, correo : String, password : String): Boolean{

        //Crear variable para que la Base de datos se pueda modificar
        val db = writableDatabase
        //crear variable para recibir los datos que escribe el usuario
        val datos = ContentValues().apply {
            put(COL_Nombre, nombre)
            put(COL_CORREO, correo)
            put(COL_PASSWORD, password)

        }

        //Insertar datos en la tabla
        val resultado = db.insert(TABLE_USUARIOS, null, datos)

        //cerrar la conexion
        db.close()

        //Comparar el valor del resultado de la inserccion y regresarlo como boolean
        return resultado != 1L

    }

    //metodo psrs consultar a un usuario
    fun iniciarSesion(correo: String, password: String): Boolean{
        val db = readableDatabase
        val query_sql ="""
            SELECT * FROM $TABLE_USUARIOS
            WHERE $COL_CORREO = ? 
             AND $COL_PASSWORD = ? 
        """.trimIndent()

        //Crear cursor para correr la informacion
        val cursor = db.rawQuery(
            query_sql,
            arrayOf(correo, password)

        )
        //crear variable de tipo Boolean que permita saber si el cursor encontro un registro
        val usuarioEncontrado = cursor.count > 0
        cursor.close()
        db.close()
        return usuarioEncontrado




    }

}