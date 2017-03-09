package woddiary20.bazaleev.io.woddiary20.ui.addexercise

import android.text.TextUtils
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.storage.Storage
import woddiary20.bazaleev.io.woddiary20.ui.base.BasePresenter
import java.text.SimpleDateFormat

/**
 * Created by max on 11/23/16.
 */
class AddExercisePresenter constructor(val storage: Storage) : BasePresenter<AddExerciseView>() {

    private val simpleDateFormat = SimpleDateFormat.getDateInstance()
    private var time = System.currentTimeMillis()

    override fun registerListeners() {
        super.registerListeners()
        view.setDate(simpleDateFormat.format(time))
    }

    fun saveExercise() {
        val name = view.getName()
        if (TextUtils.isEmpty(name)) {
            view.showMessage(R.string.fill_name)
            return
        }
//        val setsList = view.getSetsList()

//        if (setsList == null){
//            view.showMessage(R.string.fill_sets)
//            return
//        }
//
//        val exercise = Exercise()
//
//        exercise.exerciseName = name
//        exercise.exerciseDate = time
//        exercise.exerciseType = 0
//        exercise.exerciseDuration = 0
//        exercise.id = time
//
//        setsList.forEach {
//            it.exerciseId = time
//        }
//
//        exercise.sets = setsList
//
//        storage.saveExercise(exercise)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.onExerciseSaved()
//                }, {
//                    view.showMessage(it.message!!)
//                })
//
    }
}