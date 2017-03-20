package woddiary20.bazaleev.io.woddiary20.storage.model

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr.WODDBConstants
import kotlin.comparisons.compareValues

/**
 * Created by max on 11/21/16.
 */

class Set constructor() : DBEntity, Comparable<Set> {

    constructor(cursor: Cursor) : this() {
        fromCursor(cursor)
    }

    var time: Long? = null
    var order: Int? = null
    var repsCount: Int? = null
    var amount: Double? = null
    var exerciseId: Long? = null

    fun describe(): String = "${repsCount} reps of ${amount} kg"

    override fun toContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(WODDBConstants.Set.COLUMN_TIME, time)
        contentValues.put(WODDBConstants.Set.COLUMN_ORDER, order)
        contentValues.put(WODDBConstants.Set.COLUMN_REPS_COUNT, repsCount)
        contentValues.put(WODDBConstants.Set.COLUMN_AMOUNT, amount)
        contentValues.put(WODDBConstants.Set.COLUMN_EXERCISE_ID, exerciseId)
        return contentValues
    }

    override fun fromCursor(cursor: Cursor) {
        time = cursor.getLong(cursor.getColumnIndex(WODDBConstants.Set.COLUMN_TIME))
        order = cursor.getInt(cursor.getColumnIndex(WODDBConstants.Set.COLUMN_ORDER))
        repsCount = cursor.getInt(cursor.getColumnIndex(WODDBConstants.Set.COLUMN_REPS_COUNT))
        amount = cursor.getDouble(cursor.getColumnIndex(WODDBConstants.Set.COLUMN_AMOUNT))
        exerciseId = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Set

        if (time != other.time) return false
        if (order != other.order) return false
        if (repsCount != other.repsCount) return false
        if (amount != other.amount) return false
        if (exerciseId != other.exerciseId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = time?.hashCode() ?: 0
        result = 31 * result + (order ?: 0)
        result = 31 * result + (repsCount ?: 0)
        result = 31 * result + (amount?.hashCode() ?: 0)
        result = 31 * result + (exerciseId?.hashCode() ?: 0)
        return result
    }

    override fun compareTo(other: Set): Int {
       return compareValues(other.order, order)
    }


}
