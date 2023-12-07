package com.example.videoapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.videoapp.databinding.ItemVideoBinding
import com.squareup.picasso.Picasso
import java.lang.Integer.min


class VideoGridAdapter(private val context: Context, private val videoList: List<VideoModel>) : BaseAdapter() {

    private lateinit var binding: ItemVideoBinding

    override fun getCount(): Int = min(videoList.size, 20)

    override fun getItem(position: Int): Any = videoList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val videoModel = getItem(position) as VideoModel
        if (convertView == null) {
            binding = ItemVideoBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemVideoBinding
        }

        // Bind data to views in the itemView (ImageView, TextViews, etc.)
        binding.title.text = videoModel.title
        binding.description.text = videoModel.description
        Log.d("SASKA", "url 3 : " + videoModel.imageUrl )
        // Load image using Picasso
        Picasso.get()
            .load(videoModel.imageUrl)
            .fit()
            .centerCrop()
            .into(binding.thumb)

        return binding.root

        // Bind data to views in the itemView (ImageView, TextViews, etc.)
    }
}