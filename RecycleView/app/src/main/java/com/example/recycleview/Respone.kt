package com.example.recyleview

data class Respone(
    val photos: Photos,
    val sta: String
) {
    fun getResult(num: Int) = photos.getLinks(num)
}