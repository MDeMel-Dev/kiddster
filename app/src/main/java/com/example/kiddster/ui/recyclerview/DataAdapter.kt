package com.example.kiddster.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kiddster.Network.Joke
import com.example.kiddster.R

class DataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    var content = "type"

    var data =  ArrayList<String>()
        set(value) {
            field = value
            content = "type"
            notifyDataSetChanged()
        }

    var dataunits =  ArrayList<Joke>()
        set(value) {
            field = value
            content = "units"
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        if(content.equals("type"))
            return data.size
        else
            return dataunits.size
    }

    private inner class TypeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var typetext: TextView = itemView.findViewById(R.id.type)
        fun bind(position: Int) {
            val typestring = data[position]
            typetext.text = typestring
        }
    }

    private inner class UnitViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var idtext: TextView = itemView.findViewById(R.id.jk_id)
        var desctext: TextView = itemView.findViewById(R.id.jk_desc)
        fun bind(position: Int) {
            val joke = dataunits[position]
            idtext.text = joke.id
            desctext.text = joke.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        if(content.equals("type")) {
            return TypeViewHolder(
                layoutInflater.inflate(R.layout.type_view, parent, false)
            )
        }
        else
        {
            return UnitViewHolder(
                layoutInflater.inflate(R.layout.joke_view, parent, false)
            )
        }


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (content.equals("type")) {
            (holder as TypeViewHolder).bind(position)
        } else {
            (holder as UnitViewHolder).bind(position)
        }
    }



}