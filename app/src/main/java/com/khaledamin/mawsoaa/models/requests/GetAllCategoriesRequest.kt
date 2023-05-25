package com.khaledamin.mawsoaa.models.requests

import com.google.gson.annotations.SerializedName

data class GetAllCategoriesRequest(
    @SerializedName("languageCode")
    val languageCode:String?
)