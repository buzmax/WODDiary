package woddiary20.bazaleev.io.woddiary20.storage

import rx.Observable
import woddiary20.bazaleev.io.woddiary20.storage.model.Exercise

/**
 * Created by Max Bazaleev on 7/28/16.
 */
interface Storage {

    fun getExercises(): Observable<List<Exercise>>

    fun saveExercise(exercise: Exercise): Observable<*>
}
