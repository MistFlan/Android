package com.flandre.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flandre.android.dto.Msg
import java.lang.IllegalArgumentException

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<MsgViewHolder>() {

    override fun getItemViewType(posotion: Int): Int {
        val msg = msgList[posotion]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == Msg.TYPE_RECEIVED) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            LeftViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            RightViewHolder(view)
        }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
        }
    }

    override fun getItemCount() = msgList.size
}

//class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
//    }
//
//    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
//    }
//
//    override fun getItemViewType(posotion: Int): Int {
//        val msg = msgList[posotion]
//        return msg.type
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        if (viewType == Msg.TYPE_RECEIVED) {
//            val view =
//                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
//            LeftViewHolder(view)
//        } else {
//            val view =
//                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
//            RightViewHolder(view)
//        }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val msg = msgList[position]
//        when (holder) {
//            is LeftViewHolder -> holder.leftMsg.text = msg.content
//            is RightViewHolder -> holder.rightMsg.text = msg.content
//            else -> throw IllegalArgumentException()
//        }
//    }
//
//    override fun getItemCount() = msgList.size
//}