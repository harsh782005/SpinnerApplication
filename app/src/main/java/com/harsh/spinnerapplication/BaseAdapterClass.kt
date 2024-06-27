package com.harsh.spinnerapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BaseAdapterClass(var array : ArrayList<String>) : BaseAdapter(){
    override fun getCount(): Int {
        return array.size
    }

    override fun getItem(position: Int): Any {
       return array[position]
    }

    override fun getItemId(position: Int): Long {
        return 1L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val view = LayoutInflater.from(parent?.context)
           .inflate(R.layout.item_base_adapter,
               parent,
              false)
        var tvName = view.findViewById<TextView>(R.id.tvName)
        var tvRollNo = view.findViewById<TextView>(R.id.tvRollNo)
        tvName.setText(array[position])
        return view
    }
}