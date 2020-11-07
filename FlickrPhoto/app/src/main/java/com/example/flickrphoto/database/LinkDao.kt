package com.example.flickrphoto.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.flickrphoto.entity.Link

@Dao
interface LinkDao {
    @Insert
    fun insertLink(vararg links: Link)

    @Insert
    fun insertLink(links: List<Link>)

    @Query("SELECT link FROM links where pid = :id")
    fun getLinks(id: Int): List<String>
}