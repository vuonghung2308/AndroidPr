package com.example.recycleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview.R
import com.example.recycleview.data.Location

class LocationAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val LOCATION_ITEM = 0
        val BUTTON_ITEM = 1
    }

    var list: ArrayList<Location> = ArrayList()


    fun reloadView(newList: List<Location>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun getItemViewType(position: Int) =
        if (position < list.size) LOCATION_ITEM else BUTTON_ITEM

    inner class ItemHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val imageLocation = itemView.findViewById<ImageView>(R.id.image_location)
        val textViewName = itemView.findViewById<TextView>(R.id.textview_name)
        val textViewLagitude = itemView.findViewById<TextView>(R.id.textview_lagitude)
        val textViewLongitude = itemView.findViewById<TextView>(R.id.textview_longitude)
    }

    inner class ButtonHolder(view: View) : RecyclerView.ViewHolder(view) {
        val addButton = view.findViewById<Button>(R.id.button_addMore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            LOCATION_ITEM -> {
                val locationView = inflater.inflate(R.layout.item_location, parent, false)
                return ItemHolder(locationView)
            }
            else -> {
                val buttonView = inflater.inflate(R.layout.button, parent, false)
                return ButtonHolder(buttonView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            LOCATION_ITEM -> {
                val location = list.get(position)
                (holder as ItemHolder).apply {
                    textViewName.text = location.name
                    textViewLagitude.text = location.lagitude
                    textViewLongitude.text = location.longitude
                }
                Glide.with(holder.imageLocation).load(location.link).into(holder.imageLocation)
            }
            else -> {
//                (holder as ButtonHolder).addButton.addOnCli
            }
        }
    }
}