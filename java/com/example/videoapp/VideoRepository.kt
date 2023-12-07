package com.example.videoapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideoRepository {

    private val apiKey = "AIzaSyBs1-BiKGD8DG0THSW0HmS6iXEQHsEtRRs" // Replace with your actual YouTube API key
    private val api: YouTubeApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(YouTubeApi::class.java)
    }

    fun getVideoDetails(videoId: String, callback: (YouTubeVideoResponse?) -> Unit) {
        api.getVideoDetails(videoId = videoId, apiKey = apiKey).enqueue(object :
            Callback<YouTubeVideoResponse> {
            override fun onResponse(call: Call<YouTubeVideoResponse>, response: Response<YouTubeVideoResponse>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<YouTubeVideoResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}