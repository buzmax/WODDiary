package woddiary20.bazaleev.io.woddiary20.storage.model

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr.WODDBConstants

/**
 * Created by max on 11/17/16.
 */

class Exercise constructor() : DBEntity {
    var id: Long? = null
    var exerciseName: String? = null
    var exerciseType: Int? = null
    var exerciseDuration: Int? = null
    var exerciseDate: Long? = null
    var sets: List<Set>? = null


    constructor(cursor: Cursor) : this() {
        fromCursor(cursor)
    }

    override fun toContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(BaseColumns._ID, exerciseDate)
        contentValues.put(WODDBConstants.Exercise.COLUMN_EXERCISE_NAME, exerciseName)
        contentValues.put(WODDBConstants.Exercise.COLUMN_EXERCISE_TYPE, exerciseType)
        contentValues.put(WODDBConstants.Exercise.COLUMN_EXERCISE_DURATION, exerciseDuration)
        contentValues.put(WODDBConstants.Exercise.COLUMN_EXERCISE_DATE, exerciseDate)
        return contentValues
    }

    override fun fromCursor(cursor: Cursor) {
        id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))
        exerciseName = cursor.getString(cursor.getColumnIndex(WODDBConstants.Exercise.COLUMN_EXERCISE_NAME))
        exerciseType = cursor.getInt(cursor.getColumnIndex(WODDBConstants.Exercise.COLUMN_EXERCISE_TYPE))
        exerciseDuration = cursor.getInt(cursor.getColumnIndex(WODDBConstants.Exercise.COLUMN_EXERCISE_DURATION))
        exerciseDate = cursor.getLong(cursor.getColumnIndex(WODDBConstants.Exercise.COLUMN_EXERCISE_DATE))
    }

    fun setsToContentValues(): Array<ContentValues?>? {
        if (sets == null)
            return null
        val array = arrayOfNulls<ContentValues>(sets!!.size)
        sets!!.forEachIndexed { i, set ->
            array[i] = set.toContentValues()
        }
        return array
    }

    fun description(): String {
        if (sets == null) {
            return ""
        }
        val builder = StringBuilder()
        sets!!.forEach {
            builder.append(it.describe())
            builder.append("\n")
        }
        return builder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Exercise

        if (id != other.id) return false
        if (exerciseName != other.exerciseName) return false
        if (exerciseType != other.exerciseType) return false
        if (exerciseDuration != other.exerciseDuration) return false
        if (exerciseDate != other.exerciseDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (exerciseName?.hashCode() ?: 0)
        result = 31 * result + (exerciseType ?: 0)
        result = 31 * result + (exerciseDuration ?: 0)
        result = 31 * result + (exerciseDate?.hashCode() ?: 0)
        return result
    }
}