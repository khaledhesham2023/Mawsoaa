package com.khaledamin.mawsoaa.models.responses

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String?
)