package com.syncstorm.hability.ui.statistics
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.ui.goals.GoalsViewModel

class StatsGoalsFragment : Fragment() {

    private lateinit var viewModel: StatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats_goals, container, false)
        viewModel = StatisticsViewModel()
        viewModel.goalsDB = DatabaseHelper(requireContext())
        viewModel.pieChartGoals = view.findViewById(R.id.pieChartGoals)


        viewModel.pieChartVisitors = mutableListOf()
        viewModel.pieChartVisitors?.add(PieEntry(countGoalsDifficultyTrivial(), "Trivial"))
        viewModel.pieChartVisitors?.add(PieEntry(countGoalsDifficultyEasy(), "Easy"))
        viewModel.pieChartVisitors?.add(PieEntry(countGoalsDifficultyMedium(), "Medium"))
        viewModel.pieChartVisitors?.add(PieEntry(countGoalsDifficultyHard(), "Hard"))

        viewModel.difficultyDataSet = PieDataSet(viewModel.pieChartVisitors, "")
        viewModel.difficultyDataSet?.setColors(*ColorTemplate.COLORFUL_COLORS)
        viewModel.difficultyDataSet?.valueTextColor = Color.WHITE
        viewModel.difficultyDataSet?.valueTextSize = 16f

        viewModel.difficultyData = PieData(viewModel.difficultyDataSet)
        viewModel.pieChartGoals?.data = viewModel.difficultyData
        viewModel.pieChartGoals?.description?.isEnabled = false
        viewModel.pieChartGoals?.centerText = "Goals Difficulty"

        viewModel.pieChartGoals?.animate()

        return view
    }

    private fun countGoalsDifficultyTrivial(): Float {
        val cursor = viewModel.goalsDB?.readDataGoalsDifficultyTrivial()
        var count = 0f

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No data", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                count++
            }

        }
        return count
    }

    private fun countGoalsDifficultyEasy(): Float {
        val cursor = viewModel.goalsDB?.readDataGoalsDifficultyEasy()
        var count: Float = 0f

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No data", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                count++
            }

        }
        return count
    }

    private fun countGoalsDifficultyMedium(): Float {
        val cursor = viewModel.goalsDB?.readDataGoalsDifficultyMedium()
        var count: Float = 0f

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No data", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                count++
            }

        }
        return count
    }

    private fun countGoalsDifficultyHard(): Float {
        val cursor = viewModel.goalsDB?.readDataGoalsDifficultyHard()
        var count: Float = 0f

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No data", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                count++
            }

        }
        return count
    }
}