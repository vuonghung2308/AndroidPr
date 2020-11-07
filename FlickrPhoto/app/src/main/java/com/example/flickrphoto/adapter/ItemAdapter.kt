package com.example.flickrphoto.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrphoto.R

class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<String>()
    private var address = ""
    private val TYPE_IMAGE = 1
    private val TYPE_LOAD = 2

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (value!!) {
                notifyItemInserted(list.size)
            } else notifyItemRemoved(list.size)
        }

    private inner class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view)
        val addressText: TextView = view.findViewById(R.id.address_text)
    }

    private inner class LoadHolder(view: View) : RecyclerView.ViewHolder(view) {
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_IMAGE -> {
                val view = inflater.inflate(R.layout.item_image, parent, false)
                ImageHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_load, parent, false)
                LoadHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_IMAGE -> {
                (holder as ImageHolder).addressText.text = address
                Glide.with(holder.imageView).load(list[position]).into(holder.imageView)
            }
            else -> {
                (holder as LoadHolder).progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position < list.size)
            return TYPE_IMAGE
        return TYPE_LOAD
    }

    override fun getItemCount(): Int {
        if (isLoading)
            return list.size + 1
        else return list.size
    }

    fun reloadView(newList: List<String>, ad: String) {
        address = ad
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun addView(newList: List<String>, ad: String) {
        address = ad
        list.addAll(newList)
        notifyItemRangeInserted(list.size - newList.size, newList.size)
    }

}