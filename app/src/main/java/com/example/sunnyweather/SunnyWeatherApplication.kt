package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @projectName SunnyWeather
 * @author JT
 * @since 2020/8/4 15:56
 * @version 1.0
 * @description $
 **/
class SunnyWeatherApplication : Application() {
    companion object {
        const val TOKEN = "HHE3TXWGRmLvWkC7"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}