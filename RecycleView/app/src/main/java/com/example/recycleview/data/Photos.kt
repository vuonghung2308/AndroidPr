package com.example.recycleview.data

import com.example.recycleview.data.Photo

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<Photo>
) {
    fun getLinks(num: Int): List<String> {
        var list = ArrayList<String>()
        for (i in 0..num - 1)
            list.add(photo[i].getLink())
        return list
    }
}