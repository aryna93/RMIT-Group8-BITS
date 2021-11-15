package com.syncstorm.hability.ui.calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.TaskModelClass
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalDayAdapter (private val mList: List<TaskModelClass>) : RecyclerView.Adapter<CalDayAdapter.ViewHolder>() {
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
        //val today = LocalDateTime.now()
        //val todayDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        val TaskModelClass = mList[position]

        //if (TaskModelClass.taskStartDate == todayDate) {
            when (TaskModelClass.taskCategory) {
                "General" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_misc)
                "Studying" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_studying)
                "Reading" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_reading)
                "Leisure" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_leisure)
                "Gym" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_gym)
                "Cooking" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_cooking)
                "Business" -> holder.imageViewCategory.setImageResource(R.drawable.taskcategory_business)
            }

            holder.textViewTaskTitle.text = TaskModelClass.taskName
            holder.textViewTaskDescription.text = TaskModelClass.taskDescription
            holder.textViewStartLabel.text = TaskModelClass.taskStartTime
            holder.textViewEndLabel.text = TaskModelClass.taskEndTime
        //} else {
            //holder.imageViewCategory.setImageResource(R.drawable.taskcategory_misc)
            //holder.textViewTaskTitle.text = "No Events to Show"
           //holder.textViewTaskDescription.text = "Nothing scheduled for today"
           //holder.textViewStartLabel.text = "Use the button at the bottom to add a task!"
            //holder.textViewEndLabel.text = ""
        }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageViewCategory: ImageView = itemView.findViewById(R.id.imageViewCategory)
        val textViewTaskTitle: TextView = itemView.findViewById(R.id.textViewTaskTitle)
        val textViewTaskDescription: TextView = itemView.findViewById(R.id.textViewTaskDescription)
        val textViewStartLabel: TextView = itemView.findViewById(R.id.textViewStartLabel)
        val textViewEndLabel: TextView = itemView.findViewById(R.id.textViewEndLabel)
    }
}