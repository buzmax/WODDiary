package woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr

import android.net.Uri
import android.provider.BaseColumns
import java.util.*


/**
 * Created by max on 11/17/16.
 */

interface WODDBConstants {

    class Exercise : BaseColumns {
        companion object {
            val TABLE_NAME = "exercises"
            val SCHEME = "content://"
            val BASE_PATH = "exercises"
            val CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + "/" + BASE_PATH)!!
            val CONTENT_ID_URI_BASE = Uri.parse("$SCHEME$AUTHORITY/$BASE_PATH/")!!
            val COLUMN_EXERCISE_NAME = "exercise_name"
            val COLUMN_EXERCISE_TYPE = "exercise_type"
            val COLUMN_EXERCISE_DURATION = "exercise_duration"
            val COLUMN_EXERCISE_DATE = "exercise_date"
            val EXERCISE_MATCHER_CODE = 1101
            val COLUMN_EXERCISES_NAME_SELECTION = COLUMN_EXERCISE_NAME + " = ?"
            val COLUMN_EXERCISES_ID_SELECTION = BaseColumns._ID + " = ?"
            val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.woddiary.exercises"
        }
    }

    class Set : BaseColumns {
        companion object {

            val TABLE_NAME = "sets"
            val SCHEME = "content://"
            val BASE_PATH = "sets"
            val CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + "/" + BASE_PATH)!!
            val CONTENT_ID_URI_BASE = Uri.parse("$SCHEME$AUTHORITY/$BASE_PATH/")!!
            val COLUMN_TIME = "time"
            val COLUMN_ORDER = "_order_"
            val COLUMN_REPS_COUNT = "reps_count"
            val COLUMN_AMOUNT = "amount"
            val COLUMN_EXERCISE_ID = "exercise_id"
            val SETS_MATCHER_CODE = 1102
            val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.woddiary.sets"
            val COLUMN_EXERCISES_ID_SELECTION = COLUMN_EXERCISE_ID + " = ?"
        }
    }

    class ExerciseSet : BaseColumns {
        companion object {
            val SCHEME = "content://"
            val BASE_PATH = Exercise.BASE_PATH + Set.BASE_PATH
            val CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + "/" + BASE_PATH)!!
            val CONTENT_ID_URI_BASE = Uri.parse("$SCHEME$AUTHORITY/$BASE_PATH/")!!
            val EXERCISES_SETS_MATCHER_CODE = 1103
            val projectionMap = HashMap<String, String>()
            val ID = "${Exercise.TABLE_NAME}.${BaseColumns._ID}"

            val COLUMN_EXERCISES_ID_SELECTION = ID + " = ?"

            init {
                projectionMap.put(BaseColumns._ID, ID)
                projectionMap.put(Exercise.COLUMN_EXERCISE_NAME, Exercise.COLUMN_EXERCISE_NAME)
                projectionMap.put(Exercise.COLUMN_EXERCISE_TYPE, Exercise.COLUMN_EXERCISE_TYPE)
                projectionMap.put(Exercise.COLUMN_EXERCISE_DURATION, Exercise.COLUMN_EXERCISE_DURATION)
                projectionMap.put(Exercise.COLUMN_EXERCISE_DATE, Exercise.COLUMN_EXERCISE_DATE)
                projectionMap.put(Set.COLUMN_TIME, Set.COLUMN_TIME)
                projectionMap.put(Set.COLUMN_ORDER, Set.COLUMN_ORDER)
                projectionMap.put(Set.COLUMN_REPS_COUNT, Set.COLUMN_REPS_COUNT)
                projectionMap.put(Set.COLUMN_AMOUNT, Set.COLUMN_AMOUNT)
            }

            val EXERCISES_SETS_TABLE_CREATE =
                    "${WODDBConstants.Exercise.TABLE_NAME} LEFT JOIN " +
                            "${WODDBConstants.Set.TABLE_NAME} ON" +
                            " ${WODDBConstants.Set.TABLE_NAME}.${WODDBConstants.Set.COLUMN_EXERCISE_ID}" +
                            "=${WODDBConstants.Exercise.TABLE_NAME}.${BaseColumns._ID}"

        }
    }

    companion object {

        val AUTHORITY = "com.buzmax.io.woddiary.provider"
    }
}
