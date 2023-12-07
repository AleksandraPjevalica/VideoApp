package com.example.videoapp

data class YouTubeVideoResponse(
    val items: List<YouTubeVideoItem>
)

data class YouTubeVideoItem(
    val snippet: YouTubeVideoSnippet
)

data class YouTubeVideoSnippet(
    val title: String,
    val description: String,
    val thumbnails: YouTubeVideoThumbnails
)

data class YouTubeVideoThumbnails(
    val medium: YouTubeVideoThumbnail
)

data class YouTubeVideoThumbnail(
    val url: String
)