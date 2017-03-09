package woddiary20.bazaleev.io.woddiary20.ui.addexercise

import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_set.view.*
import woddiary20.bazaleev.io.woddiary20.storage.model.Set

/**
 * Created by max on 11/23/16.
 */

class SetController internal constructor(private val setsContainer: LinearLayout) : View.OnClickListener {

    private var currentCont = 1
    private var removeLister : OnRemoveClickListener? = null

//    fun addSet() {
//        val view = LayoutInflater.from(setsContainer.context).inflate(R.layout.item_set, setsContainer, false)
//        view.tag = currentCont
//        view.ib_remove.setOnClickListener(this)
//        setsContainer.addView(view)
//        setsContainer.requestLayout()
//        currentCont++
//        view.et_reps.requestFocus()
//    }

    override fun onClick(view: View?) {
//        if (removeLister != null) {
            removeLister!!.removeClicked()
//        }
//        setsContainer.removeView(setsContainer.findViewWithTag((view!!.parent as View).tag))
    }

//    fun getSetsList(): List<Set>? {
//        if (!isSetsValid())
//            return null
//        return (0..setsContainer.childCount - 1).map { fromView(setsContainer.getChildAt(it), it) }
//    }

//    private fun isSetsValid(): Boolean {
//        return if (setsContainer.childCount < 1) false else (0..setsContainer.childCount - 1).any { isSetValid(setsContainer.getChildAt(it)) }
//    }

    public fun isSetValid(view: View): Boolean {
        try {
            val et_amount = view.et_amount
            if (et_amount != null) java.lang.Double.parseDouble(et_amount.text.toString())
            val et_reps = view.et_reps
            if (et_reps != null) Integer.parseInt(et_reps.text.toString())
            return true
        } catch (e: Exception) {
            return false
        }
    }

    public fun getSet(view: View, position: Int): Set {
        val set = Set()

        set.amount = java.lang.Double.parseDouble(view.et_amount.text.toString())
        set.repsCount = Integer.parseInt(view.et_reps.text.toString())
//        set.order = position + 1
        set.time = 0

        return set
    }

    interface OnRemoveClickListener {
        fun removeClicked()
    }
}
