package woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr

import android.content.ContentResolver
import android.database.Cursor
import rx.Observable
import woddiary20.bazaleev.io.woddiary20.storage.Storage
import woddiary20.bazaleev.io.woddiary20.storage.model.Exercise
import woddiary20.bazaleev.io.woddiary20.storage.model.Set
import woddiary20.bazaleev.io.woddiary20.storage.observer.RXContentObserver
import java.util.*


/**
 * Created by max on 11/17/16.
 */
class Storage_CntntPrvdrImpl(private val contentResolver: ContentResolver) : Storage {

    override fun getExercises(): Observable<List<Exercise>> {
        return Observable.create(RXContentObserver(contentResolver,
                WODDBConstants.ExerciseSet.CONTENT_URI, null, null, null, null,
                object : RXContentObserver.TransformCursor<List<Exercise>> {
                    override fun transform(cursor: Cursor): List<Exercise> {
                        val exercisesList = HashSet<Exercise>()
                        val setsList = ArrayList<Set>()

                        do {
                            setsList.add(Set(cursor))
                            exercisesList.add(Exercise(cursor))
                        } while (cursor.moveToNext())

                        exercisesList.forEach {
                            exercise: Exercise ->
                            exercise.sets = ArrayList<Set>()
                            setsList.forEach {
                                if (it.exerciseId == exercise.id) {
                                    (exercise.sets as MutableList<Set>).add(it)
                                }
                            }
                        }

                        return ArrayList<Exercise>(exercisesList)
                    }
                }
        ))
    }

    override fun saveExercise(exercise: Exercise): Observable<*> {
        return Observable.just(exercise)
                .doOnNext { exercise ->
                    contentResolver.insert(WODDBConstants.Exercise.CONTENT_URI, exercise.toContentValues())
                    val sets = exercise.setsToContentValues()
                    if (sets != null)
                        contentResolver.bulkInsert(WODDBConstants.Set.CONTENT_URI, sets)
                }
    }
}