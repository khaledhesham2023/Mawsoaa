package com.khaledamin.mawsoaa.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//class Topic(
//    var description: String?,
//    var details: String?,
//    var image: String?,
//    var mapImage: String?,
//    var name: String?,
//    var smallImage: String?,
//    var titleImage: String?
//): Serializable

data class Topic(
    @SerializedName("id")
    val topicId:Long,
    @SerializedName("title")
    val topicTitle:String?,
    @SerializedName("description")
    val topicDescription:String?,
    @SerializedName("details")
    val topicDetails:String?,
    @SerializedName("image")
    val topicImage:String?,
    @SerializedName("smallImage")
    val topicSmallImage:String?,
    @SerializedName("mapImage")
    val topicMapImage:String?,
    @SerializedName("titleImage")
    val topicTitleImage:String?,
    @SerializedName("category_name")
    val categoryName:String?
):Serializable {

}