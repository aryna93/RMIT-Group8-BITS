package com.syncstorm.hability.ui.calendar

import android.content.Intent.getIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHandler
import com.syncstorm.hability.database.TaskModelClass
import java.lang.IndexOutOfBoundsException

class CalMonthAdapter (private val mList: MutableList<TaskModelClass>) : RecyclerView.Adapter<CalMonthAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_month_recyclerview, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val TaskModelClass = mList[position]

        when (TaskModelClass.taskCategory) {
            "General" -> holder.imageViewMonthCategory.setImageResource(R.drawable.taskcategory_misc)
            "Studying" -> holder.imageViewMonthCategory.setImageResource(R.drawable.taskcategory_studying)
            "Reading" -> holder.imageViewMonthCategory.setImageResource(R.drawable.taskcategory_reading)
            "Leisure" -> holder.imageViewMonthCategory.setImageResource(R.drawable.taskcategory_leisure)
            "Gym" -> holder.imageViewMonthCategory.setImageResource(R.drawable.taskcategory_gym)
            "Cooking" -> holder.imageViewMonthCategory.setImageResource(R.drawable.taskcategory_cooking)
            "Business" -> holder.imageViewMonthCategory.setImageResource(R.drawable.taskcategory_business)
        }

        holder.textViewMonthTaskStatus.text = TaskModelClass.taskStatus
        holder.textViewMonthTaskTitle.text = TaskModelClass.taskName
        holder.textViewMonthTaskDescription.text = TaskModelClass.taskDescription
        holder.textViewMonthStartLabel.text = "START: " + TaskModelClass.taskStartTime + " (" + TaskModelClass.taskStartDate + ")"
        holder.textViewMonthEndLabel.text = "END: " + TaskModelClass.taskEndTime + " (" + TaskModelClass.taskEndDate + ")"

        holder.cardViewCalMonthRecycler.setOnClickListener {

        }

        val context = holder.textViewButtonMonthDeleteTask.getContext()
        val db = DatabaseHandler(context)

        holder.textViewButtonMonthDeleteTask.setOnClickListener {
            mList.get(position).taskID?.let { it1 -> db.deleteTask(it1.toInt()) }
            removeItem(position)
            notifyDataSetChanged()
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    private var onClickListener : OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener

    }

    interface OnClickListener {
        fun onClick(position: Int){
        }
    }
    private fun removeItem(position: Int){
        mList.removeAt(position)
        notifyItemRemoved(position)
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardViewCalMonthRecycler: CardView = itemView.findViewById(R.id.cardViewCalMonthRecycler)
        val imageViewMonthCategory: ImageView = itemView.findViewById(R.id.imageViewMonthCategory)
        val textViewMonthTaskTitle: TextView = itemView.findViewById(R.id.textViewMonthTaskTitle)
        val textViewMonthTaskDescription: TextView = itemView.findViewById(R.id.textViewMonthTaskDescription)
        val textViewMonthStartLabel: TextView = itemView.findViewById(R.id.textViewMonthStartLabel)
        val textViewMonthEndLabel: TextView = itemView.findViewById(R.id.textViewMonthEndLabel)
        val textViewMonthTaskStatus: TextView = itemView.findViewById(R.id.textViewMonthTaskStatus)
        val textViewButtonMonthDeleteTask: Button = itemView.findViewById(R.id.buttonMonthDeleteTask)
    }
}