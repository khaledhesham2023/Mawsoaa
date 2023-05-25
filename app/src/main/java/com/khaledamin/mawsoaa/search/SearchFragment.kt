package com.khaledamin.mawsoaa.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.authentication.AuthenticationActivity
import com.khaledamin.mawsoaa.base.BaseFragment
import com.khaledamin.mawsoaa.base.BaseFragmentWithViewModel
import com.khaledamin.mawsoaa.databinding.FragmentSearchBinding
import com.khaledamin.mawsoaa.models.Topic
import com.khaledamin.mawsoaa.models.requests.FindTopicRequest
import com.khaledamin.mawsoaa.topics.TopicsCallback
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragmentWithViewModel<FragmentSearchBinding, SearchViewModel>(),
    TopicsCallback {
    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java

    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        searchAdapter = SearchAdapter(ArrayList(), this)
        viewBinding.topicsList.adapter = searchAdapter
        viewBinding.topicsList.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun setupObservers() {
        viewModel.searchTopicLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is ViewState.Loading -> {
                    loadingDialog.show()
                }
                is ViewState.Success -> {
                    if (it.data.data!!.isEmpty()) {
                        viewBinding.emptyView.visibility = View.VISIBLE
                        viewBinding.topicsList.visibility = View.GONE
                        viewBinding.searchText.text = getString(R.string.error_search)
                        loadingDialog.dismiss()
                    } else {
                        viewBinding.emptyView.visibility = View.GONE
                        viewBinding.topicsList.visibility = View.VISIBLE
                        searchAdapter.updateDataSet(it.data.data)
                        loadingDialog.dismiss()
                    }
                }
                is ViewState.Error -> {
                    viewBinding.emptyView.visibility = View.VISIBLE
                    viewBinding.topicsList.visibility = View.GONE
                    viewBinding.searchText.text = getString(R.string.error_loading_search_results)
                    loadingDialog.dismiss()
                }
            }

        })
    }

    private fun setupListeners() {
        viewBinding.searchTab.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val queryText = query.toString()
                viewModel.findTopic(
                    FindTopicRequest(
                        queryText,
                        sharedPreferencesRepo.getCurrentLang()
                    )
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override val layout: Int
        get() = R.layout.fragment_search

    override fun onTopicClicked(topic: Topic?) {
        if (sharedPreferencesRepo.isLoggedIn()) {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToTopicDetailsFragment(
                    topic!!
                )
            )
        } else {
            startActivity(Intent(requireActivity(), AuthenticationActivity::class.java))
        }
    }
}