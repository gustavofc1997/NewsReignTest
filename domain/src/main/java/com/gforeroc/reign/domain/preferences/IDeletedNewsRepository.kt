package com.gforeroc.reign.domain.preferences

interface IDeletedNewsRepository {
    suspend fun deleteItem(id: Long)
    suspend fun getItemsDeleted(): ArrayList<Long>
}