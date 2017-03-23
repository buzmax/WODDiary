package woddiary20.bazaleev.io.woddiary20.ui.addexercise

import woddiary20.bazaleev.io.woddiary20.ui.base.IView

/**
 * Created by max on 11/23/16.
 */

interface AddExerciseView : IView {
    fun getName(): String
    fun setDate(date: String)
    fun getSetsList(): List<woddiary20.bazaleev.io.woddiary20.storage.model.Set>?
    fun onExerciseSaved()

}