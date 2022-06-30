package com.flandre.android

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.flandre.android.dto.Human

class HumanAdapter(activity: Activity, val resourceId: Int, data: List<Human>) :
    ArrayAdapter<Human>(activity, resourceId, data) {

    inner class ViewHolder(val humanImage: ImageView, val humanName: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (null == convertView) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val humanImage: ImageView = view.findViewById(R.id.humanImage)
            val humanName: TextView = view.findViewById(R.id.humanName)
            viewHolder = ViewHolder(humanImage, humanName)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val human = getItem(position)
        human?.let {
            viewHolder.humanImage.setImageResource(human.imageId)
            viewHolder.humanName.setText(human.name)
        }

        return view
    }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view: View
//        if (null == convertView) {
//            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
//        } else {
//            view = convertView
//        }
//
//        val humanImage: ImageView = view.findViewById(R.id.humanImage)
//        val humanName: TextView = view.findViewById(R.id.humanName)
//        val human = getItem(position)
//        human?.let {
//            humanImage.setImageResource(human.imageId)
//            humanName.text = human.name
//        }
//        return view
//    }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
//        val humanImage: ImageView = view.findViewById(R.id.humanImage)
//        val humanName: TextView = view.findViewById(R.id.humanName)
//        val human = getItem(position)
//        human?.let {
//            humanImage.setImageResource(human.imageId)
//            humanName.text = human.name
//        }
//        return view
//    }
}