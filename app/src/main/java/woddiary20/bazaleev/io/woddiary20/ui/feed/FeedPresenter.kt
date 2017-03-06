package woddiary20.bazaleev.io.woddiary20.ui.feed

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.storage.Storage
import woddiary20.bazaleev.io.woddiary20.ui.base.BasePresenter

/**
 * Created by max on 11/21/16.
 */

class FeedPresenter constructor(var storage: Storage) : BasePresenter<FeedView>() {

    override fun registerListeners() {
        getExercisesList()
    }

    fun getExercisesList() {
        val subscribe = storage.getExercises()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null)
                        view.showExercisesList(it)
                    else
                        view.showEmptyState()
                }, {
                    view.showMessage(R.string.error)
                })
        subscriptions.add(subscribe)
    }
}
