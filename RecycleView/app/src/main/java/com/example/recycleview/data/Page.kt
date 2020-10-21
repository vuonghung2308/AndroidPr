package com.example.recycleview.data

data class Page(
    val photos: Photos,
    val sta: String
) {
    fun getResult(num: Int) = photos.getLinks(num)
}