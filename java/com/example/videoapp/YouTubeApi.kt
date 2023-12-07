package com.example.videoapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {
    @GET("videos")
    fun getVideoDetails(
        @Query("part") part: String = "snippet",
        @Query("id") videoId: String,
        @Query("key") apiKey: String
    ): Call<YouTubeVideoResponse>
}