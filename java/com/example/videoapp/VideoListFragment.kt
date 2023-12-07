package com.example.videoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.videoapp.databinding.FragmentVideoListBinding

class VideoListFragment : Fragment() {
    private lateinit var videoListBinding: FragmentVideoListBinding
    private lateinit var navController: NavController
    private var videoList = mutableListOf<VideoModel>()
    private lateinit var videoGridAdapter: VideoGridAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        videoListBinding = FragmentVideoListBinding.inflate(inflater, container, false)
        val numRows = 10
        val rowHeight = calculateRowHeight(numRows)
        videoListBinding.gridView.layoutParams.height = rowHeight * numRows
        Log.d("SASKA", "onCreateView: ")
        return videoListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        val videoId = "U2SyxJzw80o"

        val viewModel = ViewModelProvider(
            this,
            YouTubeViewModelFactory(VideoRepository())
        ).get(YouTubeViewModel::class.java)
        viewModel.loadVideoDetails(videoId)

        viewModel.videoDetails.observe(viewLifecycleOwner) { videoDetails ->
            if (videoDetails != null && videoDetails.items.isNotEmpty()) {
                val firstVideo =
                    videoDetails.items.first() // Assuming you are dealing with the first video
                val title = firstVideo.snippet.title
                val description = ""
                val thumbnailUrl = firstVideo.snippet.thumbnails.medium.url
                var videoItem = VideoModel(title, description, thumbnailUrl)
                Log.d("SASKA", "URL: " + thumbnailUrl)
                Log.d("SASKA", "OBSERVE: ")

                for (i in 0..40) {
                    videoList.add(videoItem)
                }
                videoGridAdapter = VideoGridAdapter(requireContext(), videoList)
                videoListBinding.gridView.adapter = videoGridAdapter

            }
        }


        videoListBinding.gridView.setOnItemClickListener { _, _, position, _ ->
            // Handle item click here
            val selectedVideo = videoList[position]

            navController.navigate(R.id.action_videoListFragment_to_videoViewFragment)


        }


    }

    private fun calculateRowHeight(numRows: Int): Int {
        // Calculate the height of each row based on your requirements
        // For example, you can divide the total height by the number of rows
        val totalHeight =
            resources.displayMetrics.heightPixels // Get the total height of the screen
        return totalHeight / numRows
    }
}