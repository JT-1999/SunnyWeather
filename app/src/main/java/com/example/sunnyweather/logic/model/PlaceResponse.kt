package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @projectName SunnyWeather
 * @author JT
 * @since 2020/8/4 16:06
 * @version 1.0
 * @description $
 **/
//根据搜索城市数据接口返回的JSON格式来定义的
data class PlaceResponse(val status: String, val places: List<Place>)

//由于JSON中一些字段的命名可能与Kotlin的命名规范不太一致，
// 因此这里采用了@SerializedName注解的方式，来让JSON字段和Kotlin字段之间建立映射关系
data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)