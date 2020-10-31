package com.example.recycleview.data

data class PlacePhotos(
    val photos: Photos,
    val sta: String
) {
    fun getLinks(num: Int) = photos.getLinks(num)
}