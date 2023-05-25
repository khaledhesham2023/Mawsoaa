package com.khaledamin.mawsoaa.category

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.databinding.ItemCategoryBinding
import com.khaledamin.mawsoaa.models.Category

class CategoryAdapter(private var data: List<Category>, private val callback: CategoryCallback) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                callback.onCategoryClicked(data[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        Log.i("TAGG", "OnCreateViewHolder called")
        return CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Log.i("TAGG", "OnBindViewHolder called")
        holder.binding.category = data[position]
    }

    override fun getItemCount(): Int = data.size

    fun updateDataSet(data: List<Category>) {
        this.data = data
        notifyDataSetChanged()
        Log.i("TAGG", "updateDataSet called")
    }
}