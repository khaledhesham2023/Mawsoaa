package com.khaledamin.mawsoaa.topicdetails

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.base.BaseFragment
import com.khaledamin.mawsoaa.databinding.FragmentTopicDetailsBinding
import com.khaledamin.mawsoaa.models.Topic
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.setBackgroundByCategory
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.startMusicByCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicDetailsFragment : BaseFragment<FragmentTopicDetailsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_topic_details

    private var mediaPlayer: MediaPlayer? = null
    private var isInPlay = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topic = TopicDetailsFragmentArgs.fromBundle(requireArguments()).topic
        configureTheMusic(topic)
        viewBinding.background.setBackgroundResource(
            setBackgroundByCategory(
                requireContext(),
                topic.categoryName!!
            )
        )
        viewBinding.lifecycleOwner = this
        viewBinding.topic = topic
        setupListeners()


    }


    private fun configureTheMusic(topic: Topic) {
        viewBinding.play.isEnabled = false
        mediaPlayer = MediaPlayer.create(
            requireContext(),
            startMusicByCategory(requireContext(), topic.categoryName!!)
        )
        mediaPlayer!!.start()
        mediaPlayer!!.setOnCompletionListener {
            it.start()
        }
    }

    private fun setupListeners() {
        viewBinding.pause.setOnClickListener {
            viewBinding.pause.isEnabled = false
            viewBinding.play.isEnabled = true
            mediaPlayer!!.pause()
        }
        viewBinding.play.setOnClickListener {
            viewBinding.pause.isEnabled = true
            viewBinding.play.isEnabled = false
            mediaPlayer!!.start()
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer!!.stop()
        mediaPlayer = null
    }

}