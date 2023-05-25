package com.khaledamin.mawsoaa.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val userId:Long,
    @SerializedName("email")
    val userEmail:String?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("role")
    val userRole:String?,
    @SerializedName("firstName")
    val firstName:String?,
    @SerializedName("lastName")
    val lastName:String?,
    @SerializedName("token")
    val userToken:String?,
    @SerializedName("userPhone")
    val userPhone:String?,
)