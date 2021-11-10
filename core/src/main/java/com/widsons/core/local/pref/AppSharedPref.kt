package com.widsons.core.local.pref

import android.content.Context

/**
 * Created on : November/10/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */
class AppSharedPref(private val context  : Context) {

    val sharedPref = context.getSharedPreferences("shared-pref-name", Context.MODE_PRIVATE)

    fun set(key : String, value : String) {
        sharedPref.edit().putString(key, value).apply()
    }

    fun get(key : String, default : String = "") : String {
        return sharedPref.getString(key, default)?: default
    }

    fun set(key : String, value : Int) {
        sharedPref.edit().putInt(key, value).apply()
    }

    fun get(key : String, value : Int = 0) : Int {
        return sharedPref.getInt(key, value)
    }
}