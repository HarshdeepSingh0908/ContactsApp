package com.harsh.contactassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactAdapter(private val contacts: List<Pair<String, String>>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val (name, number) = contacts[position]
        holder.tvName.text = name
        holder.tvNumber.text = number
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvNumber: TextView = itemView.findViewById(R.id.tvNumber)
    }
}
