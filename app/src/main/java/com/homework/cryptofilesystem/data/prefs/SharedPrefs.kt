package com.homework.cryptofilesystem.data.prefs

import android.content.Context.MODE_PRIVATE
import com.homework.cryptofilesystem.App

object SharedPrefs {
    private const val SHARED_PREF_NAME = "AUTH"

    const val USERNAME = "username"
    const val PASSWORD = "password"


    /**
     * set value for shared preference
     * @param key is the key by which the value will be put
     * @param value is value which will be put
     */
    fun setValueToSharedPreference(key: String, value: String) {
        val sharedPreferences = App.appContext.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun setValueToSharedPreference(key: String, value: Long) {
        val sharedPreferences = App.appContext.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        sharedPreferences.edit().putLong(key, value).apply()
    }

    /**
     * get value from shared pref by key
     * @param key is the key by which the value will be get
     */
    fun getSharedPreferenceString(key: String): String {
        val sharedPreferences = App.appContext.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun getSharedPreferenceLong(key: String): Long {
        val sharedPreferences = App.appContext.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        return sharedPreferences.getLong(key, 0)
    }
}
