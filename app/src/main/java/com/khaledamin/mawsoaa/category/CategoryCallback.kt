package com.khaledamin.mawsoaa.category

import com.khaledamin.mawsoaa.models.Category

interface CategoryCallback {

    fun onCategoryClicked(category: Category)
}