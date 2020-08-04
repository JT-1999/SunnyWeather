package com.example.sunnyweather.logic.network

import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @projectName SunnyWeather
 * @author JT
 * @since 2020/8/4 16:28
 * @version 1.0
 * @description $
 **/
//Retrofit接口以功能种类名开头，以Service结尾
interface PlaceService {

/*    @GET注解 表示调用searchPlaces时会发起一条GET请求
    请求的地址就是我们在@GET注解中传入的具体参数 注意这里只需要传入请求地址的相对路径即可

    返回值必须必须声明成Retrofit内置的Call类型
    搜索城市数据的API中只有query这个参数是需要动态指定的，使用@Query注解的方式来实现
    其他两个参数固定不变，因此直接卸载@GET注解中即可*/
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query")query: String): Call<PlaceResponse>
}
//Next：创建一个Retrofit构建器 ServiceCreator