package com.example.hackathon.di

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application() {
    private val PREF_NAME = "pref"

    companion object {
        lateinit var mPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        mPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

}