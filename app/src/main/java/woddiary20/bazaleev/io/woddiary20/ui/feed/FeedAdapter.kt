package woddiary20.bazaleev.io.woddiary20.ui.feed

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_feed.view.*
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.storage.model.Exercise
import java.text.SimpleDateFormat

/**
 * Created by max on 11/22/16.
 */
class FeedAdapter constructor(var exercises: List<Exercise>) : Adapter<FeedAdapter.FeedHolder>() {

    val formatter = SimpleDateFormat.getDateInstance()

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        val exercise = exercises[position]
        holder.name.text = exercise.exerciseName
        holder.date.text = formatter.format(exercise.exerciseDate)
        holder.description.text = exercise.description()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedHolder {
        return FeedHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_feed, parent, false))
    }

    class FeedHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name = view.name
        var date = view.date
        var description = view.description
    }

    fun updateExercises(newExercises: List<Exercise>){
        exercises = newExercises
        notifyDataSetChanged()
    }
}