package com.example.taskshabits.adapter

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
        holder.habitsDaysCount.text = habitsItemViewModel.cardDaysCount
        holder.habitsImage.setImageResource(habitsItemViewModel.cardImage)
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

}
