package woddiary20.bazaleev.io.woddiary20.ui.addexercise

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import woddiary20.bazaleev.io.woddiary20.R

import java.util.ArrayList

import woddiary20.bazaleev.io.woddiary20.storage.model.Set

/**
 * Created by macuser on 3/20/17.
 */

class SetsAdapter : RecyclerView.Adapter<SetsAdapter.SetsViewHolder> {

    val setList: List<Set>

    internal constructor(setList: List<Set>) {
        this.setList = setList
    }

    internal constructor(setsAmount: Int, repsCount: Int, amount: Double) {
        setList = ArrayList<Set>()
        for (i in 0..setsAmount - 1) {
            val set = Set()
            set.amount = amount
            set.order = i
            set.repsCount = repsCount
            set.time = 0
            setList.add(set)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder? {
        return SetsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_set, parent, false))
    }

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) {
        holder.bind(position, setList[position])
    }

    override fun getItemCount(): Int {
        return setList.size
    }

    class SetsViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val number: TextView = itemView.findViewById(R.id.set_number) as TextView
        val reps: TextView = itemView.findViewById(R.id.set_reps) as TextView
        val repsAmount: TextView = itemView.findViewById(R.id.set_reps_amount) as TextView
        val amount: TextView = itemView.findViewById(R.id.set_amount) as TextView
        val units: TextView = itemView.findViewById(R.id.set_units) as TextView

        fun bind(position: Int, item: Set) {
            number.text = String.format(number.context.getString(R.string.d_set), position + 1)
            reps.text = String.format("%d", item.repsCount)
            amount.text = String.format("%s", item.amount)
        }
    }
}
