package com.example.videoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class YouTubeViewModelFactory(private val repository: VideoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(YouTubeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return YouTubeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}