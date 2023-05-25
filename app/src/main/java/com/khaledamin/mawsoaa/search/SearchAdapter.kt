package com.khaledamin.mawsoaa.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.databinding.ItemTopicBinding
import com.khaledamin.mawsoaa.models.Topic
import com.khaledamin.mawsoaa.topics.TopicsCallback

class SearchAdapter(var data:ArrayList<Topic>, val callback:TopicsCallback) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    inner class SearchViewHolder(val binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                callback.onTopicClicked(data[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_topic,parent,false))
    }

    override fun getItemCount(): Int = data.size



    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.topic = data[position]
    }
    fun updateDataSet(data: ArrayList<Topic>){
        this.data = data
        notifyDataSetChanged()
    }

}