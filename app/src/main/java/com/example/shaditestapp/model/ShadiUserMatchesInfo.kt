package com.example.shaditestapp.model


data class ShadiUserMatchesInfo(
    val id:Int=0,
    val name: String,
    val email: String,
    val age: Int,
    val picture: String,
    var accept: String="default"

)
