package woddiary20.bazaleev.io.woddiary20.storage.model

import android.content.ContentValues
import android.database.Cursor

/**
 * Created by max on 11/21/16.
 */

interface DBEntity {
    fun toContentValues(): ContentValues

    fun fromCursor(cursor: Cursor)
}
