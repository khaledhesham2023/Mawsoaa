package com.khaledamin.mawsoaa.category

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.authentication.AuthenticationActivity
import com.khaledamin.mawsoaa.base.BaseFragmentWithViewModel
import com.khaledamin.mawsoaa.databinding.FragmentCategoryBinding
import com.khaledamin.mawsoaa.models.Category
import com.khaledamin.mawsoaa.models.requests.GetAllCategoriesRequest
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragmentWithViewModel<FragmentCategoryBinding, CategoryViewModel>(),
    CategoryCallback {
    override val layout: Int
        get() = R.layout.fragment_category

    override val viewModelClass: Class<CategoryViewModel>
        get() = CategoryViewModel::class.java

    private lateinit var adapter: CategoryAdapter
    private var mediaPlayer: MediaPlayer? = null

    override fun onResume() {
        super.onResume()
        configureTheMusic()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureTheAdapter()
        setupListeners()
        viewBinding.lifecycleOwner = this

        viewModel.getMainCategories(GetAllCategoriesRequest(sharedPreferencesRepo.getCurrentLang()))


    }

    override fun onCategoryClicked(category: Category) {
        if (sharedPreferencesRepo.isLoggedIn()) {
            findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToTopicsFragment(category)
            )
        } else {
            startActivity(Intent(requireActivity(), AuthenticationActivity::class.java))
        }

    }

    private fun configureTheAdapter() {
        adapter = CategoryAdapter(ArrayList(), this)
        viewBinding.categoriesList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.categoriesList.adapter = adapter
    }

    override fun setupObservers() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Loading -> {
                    viewBinding.errorGroup.isVisible = false
                    viewBinding.defaultGroup.isVisible = false
                    loadingDialog.show()
                }

                is ViewState.Success -> {
                    if (it.data.data!!.isNotEmpty()) {
                        viewBinding.errorGroup.isVisible = false
                        viewBinding.defaultGroup.isVisible = true
                        adapter.updateDataSet(it.data.data)
                        loadingDialog.dismiss()
                    } else {
                        viewBinding.errorGroup.isVisible = true
                        viewBinding.defaultGroup.isVisible = false
                        viewBinding.errorText.text = getString(R.string.no_category_error)
                    }
                }
                is ViewState.Error -> {
                    viewBinding.errorGroup.isVisible = true
                    viewBinding.defaultGroup.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        })
    }

    private fun configureTheMusic() {
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.categories)
        mediaPlayer!!.start()
        mediaPlayer!!.setOnCompletionListener {
            it.start()
        }
    }

    override fun onStop() {
        super.onStop()
        stopTheMusic()
    }

    override fun onPause() {
        super.onPause()
        stopTheMusic()
    }

    private fun stopTheMusic() {
        mediaPlayer!!.stop()
        mediaPlayer = null
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.app_music)
    }
    private fun setupListeners(){
        viewBinding.errorGroup.setOnClickListener {
            viewModel.getMainCategories(GetAllCategoriesRequest(sharedPreferencesRepo.getCurrentLang()))
        }
    }
}

