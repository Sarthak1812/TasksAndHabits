package com.example.taskshabits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitsAdapter(private val mHabitsList: List<HabitsItemViewModel>) :RecyclerView.Adapter<HabitsAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the layout_card_habits
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card_habits, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val HabitsItemViewModel = mHabitsList[position]
        holder.habitsTitle.text = HabitsItemViewModel.cardTitle
        holder.habitsDaysCount.text = HabitsItemViewModel.cardDaysCount
        holder.habitsImage.setImageResource(HabitsItemViewModel.cardImage)
    }

    override fun getItemCount(): Int {
        //return number of items in the list
        return mHabitsList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val habitsTitle: TextView = itemView.findViewById(R.id.tv_habits_card_title)
        val habitsDaysCount: TextView = itemView.findViewById(R.id.tv_habits_card_day_count)
        val habitsImage: ImageView = itemView.findViewById(R.id.iv_habits_card)
    }
}