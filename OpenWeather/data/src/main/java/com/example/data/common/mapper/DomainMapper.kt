package com.example.data.common.mapper

interface DomainMapper<out T : Any> {
    fun mapToDomainEntity(): T
}