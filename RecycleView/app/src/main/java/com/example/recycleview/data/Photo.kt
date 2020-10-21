package com.example.recycleview.data

data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: Int,
    val farm: Int,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
) {
    fun getLink() = "https://live.staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg"
}