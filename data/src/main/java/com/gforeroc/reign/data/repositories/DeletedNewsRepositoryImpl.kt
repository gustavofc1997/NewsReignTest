package com.gforeroc.reign.data.repositories

import com.gforeroc.reign.data.utils.EXCLUDED_NEWS
import com.gforeroc.reign.domain.helpers.ILocalStorageHelper
import com.gforeroc.reign.domain.preferences.IDeletedNewsRepository

class DeletedNewsRepositoryImpl(
    private val localStorageHelper: ILocalStorageHelper,
) : IDeletedNewsRepository {
    override suspend fun deleteItem(id: Long) {
        val items = getItemsDeleted()
        items.add(id)
        localStorageHelper.saveData(EXCLUDED_NEWS, items)
    }

    override suspend fun getItemsDeleted(): ArrayList<Long> {
        val array = arrayListOf<Long>()
        localStorageHelper.getData(
            EXCLUDED_NEWS
        ).forEach {
            array.add(it)
        }
        return array
    }

}