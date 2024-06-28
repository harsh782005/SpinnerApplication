package com.harsh.spinnerapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter(var studentList: ArrayList<StudentDataClass>) : BaseAdapter() {
    override fun getCount(): Int {
        return studentList.size
    }

    override fun getItem(position: Int): Any {
        return studentList[position]
    }

    override fun getItemId(position: Int): Long {
        return studentList[position].rollNo?.toLong() ?: 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_student_adapter,
                parent, false
            )
        val tvRollNo = view.findViewById<TextView>(R.id.tvRollNo)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvCourse = view.findViewById<TextView>(R.id.tvCourse)
        tvRollNo.setText(studentList[position].rollNo.toString())
        tvName.setText(studentList[position].name)
        tvCourse.setText(studentList[position].course)
        return view

    }
}