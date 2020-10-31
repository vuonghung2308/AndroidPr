package com.example.recycleview.adapter

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
import java.lang.ref.WeakReference

class LocationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val TYPE_ITEM = 1
        val TYPE_BUTTON = 2
    }

    private val list = ArrayList<Location>()

    interface Listenner {
        fun onButtonClick()
    }

    var listenner: Listenner? = null
        set(value) {
            field = value
        }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_location)
        val nameLabel: TextView = view.findViewById(R.id.textview_name)
        val latLabel: TextView = view.findViewById(R.id.textview_lagitude)
        val lonLabel: TextView = view.findViewById(R.id.textview_longitude)
    }

    inner class ButtonHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val buttonAdd: Button
        val listennerRef: WeakReference<Listenner>

        init {
            buttonAdd = view.findViewById(R.id.button_addMore)
            listennerRef = WeakReference(listenner)
            buttonAdd.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listennerRef.get()?.onButtonClick()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ITEM -> {
                val view = inflater.inflate(R.layout.item_location, parent, false)
                ItemHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_button, parent, false)
                ButtonHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> {
                (holder as ItemHolder).apply {
                    nameLabel.text = list[position].name
                    latLabel.text = list[position].lat
                    lonLabel.text = list[position].lon
                    Glide.with(imageView).load(list[position].link).into(imageView)
                }
            }
            else -> {
                (holder as ButtonHolder).buttonAdd.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount() = if (!list.isEmpty()) list.size else 0
    override fun getItemViewType(position: Int) =
        if (position < list.size) TYPE_ITEM else TYPE_BUTTON

    fun reloadView(newList: List<Location>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun addView(newList: List<Location>) {
        list.addAll(newList)
        notifyItemRangeInserted(list.size - newList.size, newList.size)
    }
}