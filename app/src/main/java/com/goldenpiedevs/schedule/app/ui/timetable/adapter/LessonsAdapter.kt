package com.goldenpiedevs.schedule.app.ui.timetable.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goldenpiedevs.schedule.app.R
import com.goldenpiedevs.schedule.app.core.dao.timetable.DaoLessonModel
import kotlinx.android.synthetic.main.day_list_lesson_item.view.*

class LessonsAdapter() : androidx.recyclerview.widget.RecyclerView.Adapter<LessonsAdapter.ViewHolder>() {
    lateinit var listener: (String) -> Unit

    var data: List<DaoLessonModel> = listOf()
        set(value) {
        field = value
        notifyDataSetChanged()
    }

    constructor(listener: (String) -> Unit) : this() {
        this.listener = listener
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        return LessonsAdapter.ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.day_list_lesson_item, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = data[position]

        holder.apply {
            hasNote.visibility = if (model.hasNote) View.VISIBLE else View.INVISIBLE
            lessonTitle.text = model.lessonName
            time.text = model.getTime()
            location.text = "${model.lessonRoom} ${model.lessonType}"
            number.text = model.lessonNumber
            itemView.setOnClickListener { listener(model.id) }
        }
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val hasNote = itemView.widget_current_lesson!!
        val number = itemView.widget_lesson_number!!
        val lessonTitle = itemView.widget_lesson_title!!
        val time = itemView.widget_lesson_time!!
        val location = itemView.widget_lesson_location!!
    }
}

