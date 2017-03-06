package woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * Created by max on 11/17/16.
 */

class WODDB(context: Context) : SQLiteOpenHelper(context, WODDB.DATABASE_NAME, null, WODDB.DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(EXERCISES_TABLE_CREATE)
        sqLiteDatabase.execSQL(SETS_TABLE_CREATE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    companion object {

        private val DATABASE_NAME = "woddiary.db"
        private val DATABASE_VERSION = 1

        private val EXERCISES_TABLE_CREATE =
                "CREATE TABLE ${WODDBConstants.Exercise.TABLE_NAME} " +
                        "(${BaseColumns._ID} INTEGER PRIMARY KEY," +
                        "${WODDBConstants.Exercise.COLUMN_EXERCISE_NAME} TEXT NOT NULL," +
                        "${WODDBConstants.Exercise.COLUMN_EXERCISE_TYPE} INTEGER NOT NULL," +
                        "${WODDBConstants.Exercise.COLUMN_EXERCISE_DATE} INTEGER NOT NULL," +
                        "${WODDBConstants.Exercise.COLUMN_EXERCISE_DURATION} INTEGER NOT NULL);"

        private val SETS_TABLE_CREATE =
                "CREATE TABLE ${WODDBConstants.Set.TABLE_NAME} " +
                        "(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "${WODDBConstants.Set.COLUMN_AMOUNT} TEXT," +
                        "${WODDBConstants.Set.COLUMN_TIME} INTEGER," +
                        "${WODDBConstants.Set.COLUMN_REPS_COUNT} INTEGER," +
                        "${WODDBConstants.Set.COLUMN_ORDER} INTEGER NOT NULL," +
                        "${WODDBConstants.Set.COLUMN_EXERCISE_ID} INTEGER NOT NULL,FOREIGN KEY (" +
                        "${WODDBConstants.Set.COLUMN_EXERCISE_ID}) REFERENCES " +
                        "${WODDBConstants.Exercise.TABLE_NAME}(${BaseColumns._ID}));"
    }
}
