package com.example.videoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YouTubeViewModel(private val repository: VideoRepository) : ViewModel() {
    private val _videoDetails = MutableLiveData<YouTubeVideoResponse>()
    val videoDetails: LiveData<YouTubeVideoResponse> get() = _videoDetails

    fun loadVideoDetails(videoId: String) {
        repository.getVideoDetails(videoId) { response ->
            _videoDetails.postValue(response)
        }
    }
}


