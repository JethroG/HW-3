package com.yourcompany.bro.hi.ck

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Adapter(
    private val items: MutableList<MessageText>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val firstUserView = 0
    private val secondUserView = 1
    private val headerView = 2

    override fun getItemViewType(position: Int): Int {
        if (items[position] is UFMess) return firstUserView
        if (items[position] is USMess) return secondUserView
        return headerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = when (viewType) {
            firstUserView -> inflater.inflate(R.layout.firstperson, parent, false)
            else -> inflater.inflate(R.layout.secondperson, parent, false)
        }
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.messageView?.text = item.messageText
    }
    override fun getItemCount(): Int {
        return items.size
    }
    fun addMessageItem(user: String, text: String) {
        var item: MessageText? = null
        when (user) {
            "1" -> item = UFMess().apply {

                this.messageText = text
            }
            "2" -> item = USMess().apply {

                this.messageText = text
            }
        }
        items.add(item!!)
        notifyItemInserted(items.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageView = itemView.findViewById<TextView>(R.id.message_view)!!



    }

    class UFMess:MessageText()
    class USMess:MessageText()
}