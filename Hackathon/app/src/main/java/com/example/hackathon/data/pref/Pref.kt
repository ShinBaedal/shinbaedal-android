package com.example.hackathon.data.pref

import com.example.hackathon.di.HiltApplication

object Pref {
    var token
        get() = HiltApplication.mPreferences.getString("token", "")
        set(value) {
            HiltApplication.mPreferences.edit().putString("token", value).apply()
        }

    var isOwner
        get() = HiltApplication.mPreferences.getBoolean("isOwner", false)
        set(value) {
            HiltApplication.mPreferences.edit().putBoolean("isOwner", value).apply()
        }
}