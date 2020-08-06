package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @projectName SunnyWeather
 * @author JT
 * @since 2020/8/6 15:15
 * @version 1.0
 * @description $
 **/
//把所有的数据模型都定义在RealtimeResponse内部，这样可以防止出现其他接口的数据模型类有同名冲突
class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String, val temperature: Float
        , @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}