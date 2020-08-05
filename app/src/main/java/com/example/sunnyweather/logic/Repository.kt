package com.example.sunnyweather.logic


import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException


/**
 * @projectName SunnyWeather
 * @author JT
 * @since 2020/8/4 21:16
 * @version 1.0
 * @description $
 **/
//仓库层：决定了调用本地数据还是网络数据
//在本应用中 全部为网络数据
object Repository {
    //指定线程参数类型Dispatchers.IO 为子线程
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        //emit类似于调用liveData的setValue()方法来通知数据变化
        emit(result)
    }
}