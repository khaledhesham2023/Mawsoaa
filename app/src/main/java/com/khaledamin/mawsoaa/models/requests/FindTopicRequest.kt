package com.khaledamin.mawsoaa.models.requests

import com.google.gson.annotations.SerializedName

data class FindTopicRequest(
    @SerializedName("title")
    val title:String?,
    @SerializedName("lang")
    val lang:String?
)