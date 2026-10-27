package com.example.music_app4a.model

//ARCHIVO QIE MANEJA LOS DATOS PROVENIENTES DEL API
data class Album(
    val wrapperType: String?,
    val collectionType: String?,

    val artistId: Long?,
    val collectionId: Long?,

    val artistName: String?,
    val collectionName: String?,

    val artistViewUrl: String?,
    val collectionViewUrl: String?,

    val artworkUrl100: String?,

    val collectionPrice: Double?,
    val trackCount: Int?,
    val releaseDate: String?,
    val primaryGenreName: String?,
    val country: String?,
    val currency: String?
){
    //metodo para pbtener la imagen grande
    fun onbtenerImagenGrande(): String{
        return artworkUrl100?.replace("100x100.jpg","600 x 600bb.jpg")?:""
    }
}
