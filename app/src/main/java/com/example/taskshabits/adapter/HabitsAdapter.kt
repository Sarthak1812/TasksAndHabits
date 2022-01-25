package com.example.taskshabits.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskshabits.R
import com.example.taskshabits.data.Habits
import com.example.taskshabits.util.HabitsDiffCallback

class HabitsAdapter :RecyclerView.Adapter<HabitsAdapter.ViewHolder>(){

    var mHabitsList = emptyList<Habits>()

    var mHabitsItemClick: ((Habits)-> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the layout_card_habits
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card_habits, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habitsItemViewModel = mHabitsList[position]
        holder.habitsTitle.text = habitsItemViewModel.cardTitle
        holder.habitsDaysCount.text = habitsItemViewModel.cardDaysCompleted.toString()
        setTagImage(habitsItemViewModel.cardTag, holder)
        val progressHabit= (habitsItemViewModel.cardDaysCompleted).toDouble()/(habitsItemViewModel.cardDayGoal)
        holder.habitProgressBar.progress = (progressHabit*100).toInt()
    }

    override fun getItemCount(): Int {
        //return number of items in the list
        return mHabitsList.size
    }


    fun setHabits(habits : List<Habits>) {
        val diffUtil = HabitsDiffCallback(mHabitsList, habits)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.mHabitsList = habits
        diffResult.dispatchUpdatesTo(this)
    }


    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val habitsTitle: TextView = itemView.findViewById(R.id.tv_habits_card_title)
        val habitsDaysCount: TextView = itemView.findViewById(R.id.tv_habits_card_day_count)
        val habitsImage: ImageView = itemView.findViewById(R.id.iv_habits_card)
        val habitProgressBar: ProgressBar = itemView.findViewById(R.id.pb_habits_card)
        // on item click
        init {
            itemView.setOnClickListener{
                mHabitsItemClick?.invoke(mHabitsList[adapterPosition])
            }
        }
    }

    private fun setTagImage(tagChoice: String, holder: ViewHolder) {
        when(tagChoice) {
            "Workout/Fitness" -> holder.habitsImage.setImageResource(R.drawable.ic_tags_workout)
            "Work/Task" -> holder.habitsImage.setImageResource(R.drawable.ic_tags_work)
            "Yoga/Meditation" -> holder.habitsImage.setImageResource(R.drawable.ic_tags_yoga)
            "Default-Target" -> holder.habitsImage.setImageResource(R.drawable.ic_tags_default_target)
            else -> {
                holder.habitsImage.setImageResource(R.drawable.ic_tags_default_target)
            }
        }
    }

}
