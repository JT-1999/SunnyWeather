package com.example.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @projectName SunnyWeather
 * @author JT
 * @since 2020/8/4 20:49
 * @version 1.0
 * @description $
 **/
//网络数据源层 调用Retrofit构建器create来进行网络请求
object SunnyWeatherNetwork {
    private val placeService = ServiceCreator.create(PlaceService::class.java)
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()


    /*   suspend关键字可以将函数声明成挂起函数
       suspendCoroutine函数必须在协程作用域或挂起函数中才能调用
       接受一个Lambda表达式，主要作用是将当前协程立即挂起，然后在一个普通的线程中执行Lambda表达式中的代码
       Lambda表达式参数会传入一个Continuation参数，调用resume或resumeWithException可以让协程恢复执行*/

    //返回值是服务器响应的数据
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

            })
        }
    }
}