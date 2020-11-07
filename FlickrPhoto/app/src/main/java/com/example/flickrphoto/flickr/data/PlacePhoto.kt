package com.example.flickrphoto.flickr.data

data class PlacePhoto(
    val photos: Photos,
    val sta: String
) {
    fun getLinks() = photos.getLinks()
}