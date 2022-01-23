package com.example.taskshabits.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskshabits.R
import com.example.taskshabits.data.Habits
import com.example.taskshabits.util.HabitsCallback
import java.util.*

class HabitsAdapter :RecyclerView.Adapter<HabitsAdapter.ViewHolder>(){

    private var mHabitsList = emptyList<Habits>()

    private lateinit var mHabitsItemClickListener: OnHabitsItemClickListener

    interface OnHabitsItemClickListener {
        fun onHabitsItemClick(position: Int)
    }

    fun setOnHabitsItemClickListener(listener: OnHabitsItemClickListener){
        mHabitsItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the layout_card_habits
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card_habits, parent, false)
        return ViewHolder(view, mHabitsItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habitsItemViewModel = mHabitsList[position]
        holder.habitsTitle.text = habitsItemViewModel.cardTitle
        holder.habitsDaysCount.text = habitsItemViewModel.cardDaysCompleted.toString()
        //        holder.habitsImage.setImageResource(R.drawable.ic_tags_yoga)
        setTagImage(habitsItemViewModel.cardTag, holder)

//        val calendar =  Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, 0)
//        calendar.set(Calendar.MINUTE, 0)
//        calendar.set(Calendar.SECOND, 0)
//        calendar.set(Calendar.MILLISECOND, 0)
//        calendar.add(Calendar.DAY_OF_YEAR, 1)
//        val tomorrow = calendar.time
//
//        if (tomorrow.after(habitsItemViewModel.cardDateLastCompleted))
//            Log.d("date", "onBindViewHolder: true")
    }

    override fun getItemCount(): Int {
        //return number of items in the list
        return mHabitsList.size
    }


    fun setHabits(habits : List<Habits>) {
        val diffUtil = HabitsCallback(mHabitsList, habits)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.mHabitsList = habits
        diffResult.dispatchUpdatesTo(this)
    }


    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: OnHabitsItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val habitsTitle: TextView = itemView.findViewById(R.id.tv_habits_card_title)
        val habitsDaysCount: TextView = itemView.findViewById(R.id.tv_habits_card_day_count)
        val habitsImage: ImageView = itemView.findViewById(R.id.iv_habits_card)

        // on item click
        init {
            itemView.setOnClickListener{
                listener.onHabitsItemClick(adapterPosition)
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
