package com.gforeroc.reign.domain.helpers

interface ILocalStorageHelper {

    fun saveData(key: String, value: ArrayList<Long>)

    fun getData(key: String): Array<Long>

    fun remove(key: String)

    fun clear()
}
