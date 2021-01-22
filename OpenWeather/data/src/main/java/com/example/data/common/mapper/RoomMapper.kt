package com.example.data.common.mapper

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}