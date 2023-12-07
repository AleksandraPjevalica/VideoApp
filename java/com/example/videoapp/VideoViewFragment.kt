package com.example.videoapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.example.videoapp.databinding.FragmentVideoViewBinding

class VideoViewFragment : Fragment() {
    private lateinit var binding: FragmentVideoViewBinding
    private lateinit var viewModel: YouTubeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVideoViewBinding.inflate(inflater, container, false)
        val videoId = "Yl9Vrbe6l2Y"

        viewModel = ViewModelProvider(this, YouTubeViewModelFactory(VideoRepository())).get(YouTubeViewModel::class.java)
        viewModel.loadVideoDetails(videoId)

        viewModel.videoDetails.observe(viewLifecycleOwner) { videoDetails ->
            Log.d("SASKA", "onCreate:  VIDEOO ")
            if (videoDetails != null && videoDetails.items.isNotEmpty()) {
                val videoUrl = "https://www.youtube.com/embed/Yl9Vrbe6l2Y"
                val firstVideo = videoDetails.items.first()
                val title = firstVideo.snippet.title
                val description = firstVideo.snippet.description
                binding.titleTextView.text = title
                binding.descriptionTextView.text = description
                Log.d("SASKA", "onCreate:  TEKSTOVIIII" + binding.titleTextView.text +" ??? "+ title)

                binding.webView.apply {
                    settings.javaScriptEnabled = true
                    settings.loadsImagesAutomatically = true
                    settings.domStorageEnabled = true
                    settings.allowFileAccess = true
                    settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
                    settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    settings.setSupportMultipleWindows(true)
                    settings.allowContentAccess = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = false
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Enable mixed content (HTTP and HTTPS) for Android 5.0+
                        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    }

                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            // Execute JavaScript to start playing the video
                            evaluateJavascript("playVideo();") { result ->
                                // Handle result if needed
                            }
                        }
                    }
                    loadUrl(videoUrl)
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}