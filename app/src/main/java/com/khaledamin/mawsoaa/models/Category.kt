package com.khaledamin.mawsoaa.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//class Category(
//    val background: String?,
//    val categoryId: Long?,
//    val image: String?,
//    val list: List<Topic>?,
//    val title: String?,
//    val music: String?
//
//): Serializable {
//
//     constructor():this(null,null,null,null,null,null)
//}

data class Category(
    @SerializedName("id")
    val id:Long,
    @SerializedName("title")
    val name:String?,
    @SerializedName("thumbnail")
    val thumbnail:String?,
    ):Serializable

