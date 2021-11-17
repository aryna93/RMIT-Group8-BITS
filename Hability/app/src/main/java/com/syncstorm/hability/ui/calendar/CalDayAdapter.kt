package com.syncstorm.hability.ui.calendar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHandler
import com.syncstorm.hability.database.TaskModelClass
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalDayAdapter (private val mList: MutableList<TaskModelClass>) : RecyclerView.Adapter<CalDayAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_day_recyclerview, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val TaskModelClass = mList[position]

        when (TaskModelClass.taskCategory) {
                "General" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_misc)
                "Studying" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_studying)
                "Reading" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_reading)
                "Leisure" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_leisure)
                "Gym" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_gym)
                "Cooking" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_cooking)
                "Business" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_business)
        }

        holder.textViewTaskStatus.text = TaskModelClass.taskStatus
        holder.textViewTaskTitle.text = TaskModelClass.taskName
        holder.textViewTaskDescription.text = TaskModelClass.taskDescription
        holder.textViewStartLabel.text = "START: " + TaskModelClass.taskStartTime
        holder.textViewEndLabel.text = "END: " + TaskModelClass.taskEndTime

        val context = holder.textViewButtonDeleteTask.getContext()
        val db = DatabaseHandler(context)

        holder.textViewButtonDeleteTask.setOnClickListener {
            mList.get(position).taskID?.let { it1 -> db.deleteTask(it1.toInt()) }
            removeItem(position)

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
        val imageViewCategory: ImageView = itemView.findViewById(R.id.imageViewCategory)
        val textViewTaskTitle: TextView = itemView.findViewById(R.id.textViewTaskTitle)
        val textViewTaskDescription: TextView = itemView.findViewById(R.id.textViewTaskDescription)
        val textViewStartLabel: TextView = itemView.findViewById(R.id.textViewStartLabel)
        val textViewEndLabel: TextView = itemView.findViewById(R.id.textViewEndLabel)
        val textViewTaskStatus: TextView = itemView.findViewById(R.id.textViewTaskStatus)
        val textViewButtonDeleteTask: Button = itemView.findViewById(R.id.buttonDeleteTask)
    }
}