package com.khaledamin.mawsoaa.topics

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.base.BaseFragmentWithViewModel
import com.khaledamin.mawsoaa.databinding.FragmentTopicsBinding
import com.khaledamin.mawsoaa.models.Topic
import com.khaledamin.mawsoaa.models.requests.GetTopicsByCategoryIdRequest
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.setBackgroundByCategory
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.startMusicByCategory
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : BaseFragmentWithViewModel<FragmentTopicsBinding, TopicsViewModel>(),
    TopicsCallback {
    override val layout: Int
        get() = R.layout.fragment_topics

    private lateinit var title: String
    private lateinit var topicList: ArrayList<Topic>
    private lateinit var topicAdapter: TopicAdapter
    private var mediaPlayer: MediaPlayer? = null
    private var categoryId: Long? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        title = containerViewModel.getCategory()!!.name!!
//        categoryId = containerViewModel.getCategory()!!.id
        title = TopicsFragmentArgs.fromBundle(requireArguments()).category.name!!
        categoryId = TopicsFragmentArgs.fromBundle(requireArguments()).category.id
        topicList = ArrayList()
        configureTheAdapter()
        setTheBackground()
        viewBinding.lifecycleOwner = this
        viewBinding.topicTitle.text = title
        viewModel.getCategoryTopics(
            GetTopicsByCategoryIdRequest(
                categoryId!!,
                sharedPreferencesRepo.getCurrentLang()
            )
        )
        setupListeners()
    }



    override fun onResume() {
        super.onResume()
        configureTheMusic()


    }

    private fun configureTheAdapter() {
        topicAdapter = TopicAdapter(topicList, this)
        viewBinding.topicsList.adapter = topicAdapter
        viewBinding.topicsList.layoutManager = LinearLayoutManager(requireContext())
    }

    override val viewModelClass: Class<TopicsViewModel>
        get() = TopicsViewModel::class.java

    override fun setupObservers() {
        viewModel.topicsByCategoryIdLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is ViewState.Loading -> {
                    viewBinding.emptyView.visibility = View.GONE
                    viewBinding.nonEmptyView.visibility = View.GONE
                    viewBinding.errorGroup.visibility = View.GONE
                    loadingDialog.dismiss()
                }
                is ViewState.Success -> {
                    if (it.data.data!!.isNotEmpty()) {
                        viewBinding.emptyView.visibility = View.GONE
                        viewBinding.nonEmptyView.visibility = View.VISIBLE
                        topicAdapter.updateDataSet(it.data.data)
                        loadingDialog.dismiss()
                    } else {
                        viewBinding.emptyView.visibility = View.VISIBLE
                        viewBinding.nonEmptyView.visibility = View.GONE
                        loadingDialog.dismiss()
                    }
                }
                is ViewState.Error -> {
                    viewBinding.nonEmptyView.visibility = View.GONE
                    viewBinding.errorGroup.visibility = View.VISIBLE
                    viewBinding.errorText.text = getString(R.string.error_loading_topics)
                    loadingDialog.dismiss()
                }
            }

        })
    }

    private fun configureTheMusic() {
        mediaPlayer =
            MediaPlayer.create(requireContext(), startMusicByCategory(requireContext(), title))
        mediaPlayer!!.start()
        mediaPlayer!!.setOnCompletionListener {
            it.start()
        }
    }

    private fun setTheBackground() {
        viewBinding.topicLayout.setBackgroundResource(
            setBackgroundByCategory(
                requireContext(),
                title
            )
        )
    }

    override fun onTopicClicked(topic: Topic?) {
        findNavController().navigate(
            TopicsFragmentDirections.actionTopicsFragmentToTopicDetailsFragment(
                topic!!
            )
        )
    }

    override fun onStop() {
        super.onStop()
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer = null
        }
    }

    private fun setupListeners(){
        viewBinding.errorGroup.setOnClickListener {
            viewModel.getCategoryTopics(GetTopicsByCategoryIdRequest(categoryId!!,sharedPreferencesRepo.getCurrentLang()))
        }
    }
}