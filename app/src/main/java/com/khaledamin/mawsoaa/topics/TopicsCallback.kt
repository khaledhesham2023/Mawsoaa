package com.khaledamin.mawsoaa.topics

import com.khaledamin.mawsoaa.models.Topic

interface TopicsCallback {

    fun onTopicClicked(topic: Topic?)
}