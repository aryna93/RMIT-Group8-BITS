package com.syncstorm.hability.ui.calendar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHandler
import com.syncstorm.hability.database.TaskModelClass
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalWeekAdapter (private val mList: MutableList<TaskModelClass>) : RecyclerView.Adapter<CalWeekAdapter.ViewHolder>() {
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
            "General" -> holder.imageViewWeekCategory.setImageResource(R.drawable.taskcategory_misc)
            "Studying" -> holder.imageViewWeekCategory.setImageResource(R.drawable.taskcategory_studying)
            "Reading" -> holder.imageViewWeekCategory.setImageResource(R.drawable.taskcategory_reading)
            "Leisure" -> holder.imageViewWeekCategory.setImageResource(R.drawable.taskcategory_leisure)
            "Gym" -> holder.imageViewWeekCategory.setImageResource(R.drawable.taskcategory_gym)
            "Cooking" -> holder.imageViewWeekCategory.setImageResource(R.drawable.taskcategory_cooking)
            "Business" -> holder.imageViewWeekCategory.setImageResource(R.drawable.taskcategory_business)
        }

        holder.textViewWeekTaskStatus.text = TaskModelClass.taskStatus
        holder.textViewWeekTaskTitle.text = TaskModelClass.taskName
        holder.textViewWeekTaskDescription.text = TaskModelClass.taskDescription
        holder.textViewWeekStartLabel.text = "START: " + TaskModelClass.taskStartTime
        holder.textViewWeekEndLabel.text = "END: " + TaskModelClass.taskEndTime

        holder.cardViewCalWeekRecycler.setOnClickListener {

        }

        val context = holder.textViewButtonWeekDeleteTask.getContext()
        val db = DatabaseHandler(context)

        holder.textViewButtonWeekDeleteTask.setOnClickListener {
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
        val cardViewCalWeekRecycler: CardView = itemView.findViewById(R.id.cardViewCalWeekRecycler)
        val imageViewWeekCategory: ImageView = itemView.findViewById(R.id.imageViewWeekCategory)
        val textViewWeekTaskTitle: TextView = itemView.findViewById(R.id.textViewWeekTaskTitle)
        val textViewWeekTaskDescription: TextView = itemView.findViewById(R.id.textViewWeekTaskDescription)
        val textViewWeekStartLabel: TextView = itemView.findViewById(R.id.textViewWeekStartLabel)
        val textViewWeekEndLabel: TextView = itemView.findViewById(R.id.textViewWeekEndLabel)
        val textViewWeekTaskStatus: TextView = itemView.findViewById(R.id.textViewWeekTaskStatus)
        val textViewButtonWeekDeleteTask: Button = itemView.findViewById(R.id.buttonWeekDeleteTask)
    }
}