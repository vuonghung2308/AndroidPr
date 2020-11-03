package com.example.recycleview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
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
        val TYPE_PROGRESS_BAR = 3
    }

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (value) {
                notifyItemInserted(list.size)
            } else {
                notifyItemRemoved(list.size)
            }
        }

    private val list = ArrayList<Location>()

    interface Listenner {
        fun onButtonClick()
    }

    var listenner: Listenner? = null
        set(value) {
            field = value
        }

    inner class ProgressHolder(view: View) : RecyclerView.ViewHolder(view) {
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_location)
        val nameLabel: TextView = view.findViewById(R.id.textview_name)
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
//            TYPE_BUTTON -> {
//                val view = inflater.inflate(R.layout.item_button, parent, false)
//                ButtonHolder(view)
//            }
            else -> {
                val view = inflater.inflate(R.layout.item_loading, parent, false)
                ProgressHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> {
                (holder as ItemHolder).apply {
                    nameLabel.text = list[position].name
                    Glide.with(imageView).load(list[position].link).into(imageView)
                }
            }
//            TYPE_BUTTON -> {
//                (holder as ButtonHolder).buttonAdd.visibility = View.VISIBLE
//            }
            else -> {
                (holder as ProgressHolder).progressBar.visibility = View.VISIBLE
            }

        }
    }

    override fun getItemCount(): Int {
        if (list.isEmpty())
            return 0;
        else if (isLoading) return list.size + 1;
        else return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in 0..list.size - 1 -> TYPE_ITEM
//            list.size -> TYPE_BUTTON
            else -> TYPE_PROGRESS_BAR
        }
    }

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