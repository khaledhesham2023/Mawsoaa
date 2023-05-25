package com.khaledamin.mawsoaa.models.responses

import com.google.gson.annotations.SerializedName
import com.khaledamin.mawsoaa.models.Topic

data class GetTopicsResponse(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String?,
    @SerializedName("data")
    val data:ArrayList<Topic>? = null
)
