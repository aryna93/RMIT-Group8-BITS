package com.syncstorm.hability.ui.goals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.syncstorm.hability.R


class RecyclerAdapterGoals(
    context: Context?,
    goalID: MutableList<String>,
    goalTitle: MutableList<String>,
    goalDescription: MutableList<String>,
    goalStartDate: MutableList<String>,
    goalDifficulty: MutableList<String>,
    goalCategory: MutableList<String>,
) : RecyclerView.Adapter<RecyclerAdapterGoals.MyViewHolder>() {

    private var context = context
    private var goalID = mutableListOf<String>()
    private var goalTitle = mutableListOf<String>()
    private var goalDescription = mutableListOf<String>()
    private var goalStartDate = mutableListOf<String>()
    private var goalDifficulty = mutableListOf<String>()
    private var goalCategory = mutableListOf<String>()

    init {
        this.goalID = goalID
        this.goalTitle = goalTitle
        this.goalDescription = goalDescription
        this.goalStartDate = goalStartDate
        this.goalDifficulty = goalDifficulty
        this.goalCategory = goalCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_view_row_goals, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textViewTitle.text = goalTitle[position]
        holder.textViewDescription.text = goalDescription[position]
        holder.textViewStartDate.text = goalStartDate[position]
        holder.chipDifficulty.text = goalDifficulty[position]
        holder.textViewCategory.text = goalCategory[position]

        holder.linearLayoutGoals.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", goalID[position])
            bundle.putString("title", goalTitle[position])
            bundle.putString("description", goalDescription[position])
            bundle.putString("startDate", goalStartDate[position])
            bundle.putString("difficulty", goalDifficulty[position])
            bundle.putString("category", goalCategory[position])
            it.findNavController().navigate(R.id.updateGoal, bundle)
        }
    }

    override fun getItemCount(): Int {
        return goalID.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitleGoal)
        var textViewDescription: TextView = itemView.findViewById(R.id.textViewDescriptionGoal)
        var textViewStartDate: TextView = itemView.findViewById(R.id.textViewStartDateGoal)
        var chipDifficulty: Chip = itemView.findViewById(R.id.chipDifficultyGoal)
        var textViewCategory: TextView = itemView.findViewById(R.id.textViewCategoryGoal)
        var linearLayoutGoals: LinearLayout = itemView.findViewById(R.id.cardViewGoals)

    }
}