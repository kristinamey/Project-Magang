package com.example.sinow.helper

import android.content.Context
import android.content.SharedPreferences
import java.nio.channels.spi.AbstractSelectionKey

class preferenceshelper (context: Context) {
    private val PREFS_NAME = "SINOWlogin"
    private var sharedpref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedpref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedpref.edit()
    }

    fun put(key: String, value: String){
        editor.putString(key, value)
            .apply()
    }

    fun getString (key: String) : String? {
        return sharedpref.getString(key, null)
    }

    fun put(key:String, value: Boolean){
        editor.putBoolean(key, value)
            .apply()
    }
    fun getBoolean(key: String) : Boolean{
        return sharedpref.getBoolean(key, false)
    }
}
