package com.example.recyleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LocationAdapter(val list: List<Location>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val locationView = inflater.inflate(R.layout.item_location, parent, false)
        return ViewHolder(locationView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = list.get(position)
        holder.apply {
            textViewName.text = location.name
            textViewLagitude.text = location.lagitude
            textViewLongitude.text = location.longitude
        }
        Glide.with(holder.imageLocation).load(location.link).into(holder.imageLocation)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val imageLocation = itemView.findViewById<ImageView>(R.id.image_location)
        val textViewName = itemView.findViewById<TextView>(R.id.textview_name)
        val textViewLagitude = itemView.findViewById<TextView>(R.id.textview_lagitude)
        val textViewLongitude = itemView.findViewById<TextView>(R.id.textview_longitude)
    }
}