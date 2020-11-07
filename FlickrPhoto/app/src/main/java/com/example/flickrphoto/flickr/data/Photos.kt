package com.example.flickrphoto.flickr.data

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<Photo>
) {
    fun getLinks(): List<String> {
        var list = ArrayList<String>()
        for (p in photo)
            list.add(p.getLink())
        return list
    }
}