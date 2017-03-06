package woddiary20.bazaleev.io.woddiary20.ui.feed

import woddiary20.bazaleev.io.woddiary20.storage.model.Exercise
import woddiary20.bazaleev.io.woddiary20.ui.base.IView

/**
 * Created by max on 11/21/16.
 */

interface FeedView : IView {
    fun showExercisesList(exercises: List<Exercise>?)
    fun showEmptyState()
}
