package com.example.taskshabits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitsAdapter(private val mHabitsList: List<HabitsItemViewModel>) :RecyclerView.Adapter<HabitsAdapter.ViewHolder>(){

    private lateinit var mHabitsItemClickListener: onHabitsItemClickListener

    interface onHabitsItemClickListener {
        fun onHabitsItemClick(position: Int)
    }

    fun setOnHabitsItemClickListener(listener: onHabitsItemClickListener){
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

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: onHabitsItemClickListener) : RecyclerView.ViewHolder(ItemView) {
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