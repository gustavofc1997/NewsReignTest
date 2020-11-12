package com.gforeroc.reign.data.helpers

import android.content.SharedPreferences
import com.gforeroc.reign.domain.helpers.ILocalStorageHelper
import com.google.gson.Gson


class LocalStorageHelper(
    private val sharedPreferences: SharedPreferences
) : ILocalStorageHelper {
    override fun saveData(
        key: String,
        value: ArrayList<Long>
    ) {
        val editor = sharedPreferences.edit()
        val jsonText: String = Gson().toJson(value)
        editor.putString(key, jsonText)
        editor.apply()
    }

    override fun getData(
        key: String,
    ): Array<Long> {
        val string = sharedPreferences.getString(key, null)
        if (string.isNullOrEmpty()) {
            return emptyArray()
        }
        return Gson().fromJson(string, Array<Long>::class.java)
    }

    override fun remove(key: String) {
        if (sharedPreferences.contains(key)) {
            sharedPreferences.edit()
                .remove(key)
                .apply()
        }
    }

    override fun clear() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}
