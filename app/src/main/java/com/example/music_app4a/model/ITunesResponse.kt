package com.example.music_app4a.model


//ESTE ES UN ARCHIVO PARA VERIFICAR SI TENEMOS UNA RESPUESTA DEL API
data class ITunesResponse(
    val resultCount: Int,
    val results: List<Album>
)
