package com.khaledamin.mawsoaa.models.requests

import com.google.gson.annotations.SerializedName

data class GetTopicsByCategoryIdRequest(
    @SerializedName("categoryId")
    val categoryId:Long,
    @SerializedName("language")
    val language:String?
)