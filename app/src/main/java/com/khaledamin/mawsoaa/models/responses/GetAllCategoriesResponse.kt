package com.khaledamin.mawsoaa.models.responses

import com.google.gson.annotations.SerializedName
import com.khaledamin.mawsoaa.models.Category

data class GetAllCategoriesResponse(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String?,
    @SerializedName("data")
    val data:ArrayList<Category>? = null
) {

    override fun toString(): String {
        return "status: $status, message: $message"
    }
}