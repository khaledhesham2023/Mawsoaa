package com.khaledamin.mawsoaa.models.requests

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("email")
    val email:String?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("password")
    val password:String?,
    @SerializedName("firstname")
    val firstname:String?,
    @SerializedName("lastName")
    val lastname:String?,
    @SerializedName("phoneNumber")
    val phoneNumber:String?,
    )