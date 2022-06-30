package com.flandre.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.flandre.android.dto.Human

class HumanRecyclerAdapter(val humanList: List<Human>) :
    RecyclerView.Adapter<HumanRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val humanImage: ImageView = view.findViewById(R.id.humanImage)
        val humanText: TextView = view.findViewById(R.id.humanName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.human_item, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val human = humanList[position]
            Toast.makeText(parent.context, "You clicked view ${human.name}", Toast.LENGTH_SHORT)
                .show()
        }

        viewHolder.humanImage.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val human = humanList[position]
            Toast.makeText(parent.context, "You clicked image ${human.name}", Toast.LENGTH_SHORT)
                .show()
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val human = humanList[position]
        holder.humanImage.setImageResource(human.imageId)
        holder.humanText.setText(human.name)
    }

    override fun getItemCount() = humanList.size
}