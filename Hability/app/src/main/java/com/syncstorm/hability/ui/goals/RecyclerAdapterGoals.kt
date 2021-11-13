package com.syncstorm.hability.ui.goals

import android.content.Context
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.databinding.FragmentUpdateGoalBinding
import java.util.zip.Inflater


class RecyclerAdapterGoals(
    context: Context?,
    task_title: MutableList<String>,
    task_description: MutableList<String>,
    task_start_date: MutableList<String>,
    task_end_date: MutableList<String>,
    task_time: MutableList<String>,
    task_category: MutableList<String>
) : RecyclerView.Adapter<RecyclerAdapterGoals.MyViewHolder>() {

    private var context = context
    private var task_title = mutableListOf<String>()
    private var task_description = mutableListOf<String>()
    private var task_start_date = mutableListOf<String>()
    private var task_end_date = mutableListOf<String>()
    private var task_time = mutableListOf<String>()
    private var task_category = mutableListOf<String>()

    init {
        this.task_title = task_title
        this.task_description = task_description
        this.task_start_date = task_start_date
        this.task_end_date = task_end_date
        this.task_time = task_time
        this.task_category = task_category

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_view_row_goals, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title_text.text = task_title[position]
        holder.description_text.text = task_description[position]
        holder.start_date_text.text = task_start_date[position]
        holder.end_date_text.text = task_end_date[position]
        holder.time_text.text = task_time[position]
        holder.category_text.text = task_category[position]
    }

    override fun getItemCount(): Int {
        return task_title.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title_text: TextView = itemView.findViewById(R.id.textViewTitle)
        var description_text: TextView = itemView.findViewById(R.id.textViewDescription)
        var start_date_text: TextView = itemView.findViewById(R.id.textViewStartDate)
        var end_date_text: TextView = itemView.findViewById(R.id.textViewEndDate)
        var time_text: TextView = itemView.findViewById(R.id.textViewTime)
        var category_text: TextView = itemView.findViewById(R.id.textViewCategory)

    }
}