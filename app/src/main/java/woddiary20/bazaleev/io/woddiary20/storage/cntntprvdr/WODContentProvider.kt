package woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import java.util.*


/**
 * Created by max on 11/17/16.
 */

class WODContentProvider : ContentProvider() {

    var database: WODDB? = null
    var uriMatcher: UriMatcher? = null

    init {
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher!!.addURI(WODDBConstants.AUTHORITY, WODDBConstants.Exercise.BASE_PATH, WODDBConstants.Exercise.EXERCISE_MATCHER_CODE)
        uriMatcher!!.addURI(WODDBConstants.AUTHORITY, WODDBConstants.Set.BASE_PATH, WODDBConstants.Set.SETS_MATCHER_CODE)
        uriMatcher!!.addURI(WODDBConstants.AUTHORITY, WODDBConstants.ExerciseSet.BASE_PATH, WODDBConstants.ExerciseSet.EXERCISES_SETS_MATCHER_CODE)
    }

    override fun onCreate(): Boolean {
        database = WODDB(context)
        return false
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val match = uriMatcher!!.match(uri)
        val queryBuilder = SQLiteQueryBuilder()
        when (match) {
            WODDBConstants.Exercise.EXERCISE_MATCHER_CODE -> queryBuilder.tables = WODDBConstants.Exercise.TABLE_NAME
            WODDBConstants.Set.SETS_MATCHER_CODE -> queryBuilder.tables = WODDBConstants.Set.TABLE_NAME
            WODDBConstants.ExerciseSet.EXERCISES_SETS_MATCHER_CODE -> {
                queryBuilder.tables = WODDBConstants.ExerciseSet.EXERCISES_SETS_TABLE_CREATE
                queryBuilder.setProjectionMap(WODDBConstants.ExerciseSet.projectionMap)
            }
        }
        val cursor = queryBuilder.query(database!!.readableDatabase, projection, selection, selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context.contentResolver, uri)
        return cursor
    }

    //  SQLiteQuery: SELECT amount, time, exercise_name, exercise_date, reps_count, _order_, exercise_type, exercises._id, exercise_duration FROM exercises LEFT JOIN sets ON sets.exercise_id=exercises._id
    override fun getType(uri: Uri): String? {
        when (uriMatcher!!.match(uri)) {
            WODDBConstants.Exercise.EXERCISE_MATCHER_CODE -> return WODDBConstants.Exercise.CONTENT_TYPE
            WODDBConstants.Set.SETS_MATCHER_CODE -> return WODDBConstants.Set.CONTENT_TYPE
        }
        return null
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val writableDatabase = database!!.writableDatabase
        var rowUri: Uri? = null
        var rowId: Long = -1
        var contentUri: Uri? = null
        when (uriMatcher!!.match(uri)) {
            WODDBConstants.Exercise.EXERCISE_MATCHER_CODE -> {
                rowId = writableDatabase.insert(WODDBConstants.Exercise.TABLE_NAME, null, contentValues)
                contentUri = WODDBConstants.Exercise.CONTENT_ID_URI_BASE
            }
            WODDBConstants.Set.SETS_MATCHER_CODE -> {
                rowId = writableDatabase.insert(WODDBConstants.Set.TABLE_NAME, null, contentValues)
                contentUri = WODDBConstants.Set.CONTENT_ID_URI_BASE
            }
        }
        if (rowId > 0) {
            rowUri = ContentUris.withAppendedId(contentUri, rowId)
            context.contentResolver.notifyChange(uri, null)
        }

        return rowUri
    }

    override fun bulkInsert(uri: Uri?, values: Array<out ContentValues>?): Int {
        when (uriMatcher!!.match(uri)) {
            WODDBConstants.Exercise.EXERCISE_MATCHER_CODE -> insertValues(values!!, WODDBConstants.Exercise.TABLE_NAME)
            WODDBConstants.Set.SETS_MATCHER_CODE -> insertValues(values!!, WODDBConstants.Set.TABLE_NAME)
        }

        context.contentResolver.notifyChange(uri, null)

        return values!!.size
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val writableDatabase = database!!.writableDatabase
        var tableName: String = ""
        when (uriMatcher!!.match(uri)) {
            WODDBConstants.Exercise.EXERCISE_MATCHER_CODE -> {
                tableName = WODDBConstants.Exercise.TABLE_NAME
            }
            WODDBConstants.Set.SETS_MATCHER_CODE -> {
                tableName = WODDBConstants.Set.TABLE_NAME
            }
        }

        return writableDatabase.delete(tableName, selection, selectionArgs)
    }

    override fun update(uri: Uri, contentValues: ContentValues?, s: String?, strings: Array<String>?): Int {
        return 0
    }

    override fun applyBatch(operations: ArrayList<ContentProviderOperation>?): Array<out ContentProviderResult> {
        val results: Array<ContentProviderResult>
        val db = database!!.writableDatabase
        db.beginTransaction()
        try {
            results = super.applyBatch(operations)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return results
    }

    private fun insertValues(values: Array<out ContentValues>, tableName: String) {
        val writableDatabase = database!!.writableDatabase
        writableDatabase.beginTransaction()
        try {
            values.forEach {
                writableDatabase.insertOrThrow(tableName, null, it)
            }
            writableDatabase.setTransactionSuccessful()
        } finally {
            writableDatabase.endTransaction()
        }
    }
}