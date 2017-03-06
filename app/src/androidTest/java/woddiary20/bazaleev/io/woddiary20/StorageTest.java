package woddiary20.bazaleev.io.woddiary20;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr.WODDBConstants;
import woddiary20.bazaleev.io.woddiary20.storage.model.Exercise;
import woddiary20.bazaleev.io.woddiary20.storage.model.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by max on 12/2/16.
 */

public class StorageTest {
    private ContentResolver contentResolver = InstrumentationRegistry.getContext().getContentResolver();


    @Test
    public void storageTest() {

        Exercise exercise = new Exercise();

        exercise.setId(123456L);
        exercise.setExerciseDate(123456L);
        exercise.setExerciseDuration(2);
        exercise.setExerciseType(3);
        exercise.setExerciseName("test");


        ArrayList<Set> arrayList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Set element = new Set();
            element.setTime(11111L);
            element.setAmount(55.5);
            element.setExerciseId(exercise.getId());
            element.setOrder(i);
            element.setRepsCount(i);
            arrayList.add(element);
        }

        exercise.setSets(arrayList);

        contentResolver.insert(WODDBConstants.Exercise.Companion.getCONTENT_URI(), exercise.toContentValues());

        ContentValues[] contentValues = exercise.setsToContentValues();

        assert contentValues != null;

        contentResolver.bulkInsert(WODDBConstants.Set.Companion.getCONTENT_URI(), contentValues);

        Cursor cursor = contentResolver.query(WODDBConstants.Exercise.Companion.getCONTENT_URI(), null,
                WODDBConstants.Exercise.Companion.getCOLUMN_EXERCISES_ID_SELECTION(), new String[]{"123456"}, null);

        assert cursor != null;
        assertTrue(cursor.moveToFirst());
        assertEquals(new Exercise(cursor), exercise);
        cursor.close();

        Cursor setCursor = contentResolver.query(WODDBConstants.Set.Companion.getCONTENT_URI(), null,
                WODDBConstants.Set.Companion.getCOLUMN_EXERCISES_ID_SELECTION(), new String[]{"123456"}, null);

        assert setCursor != null;
        assertTrue(setCursor.moveToFirst());
        assertEquals(setCursor.getCount(), 5);

        Cursor all = contentResolver.query(WODDBConstants.ExerciseSet.Companion.getCONTENT_URI(), null,
                WODDBConstants.ExerciseSet.Companion.getCOLUMN_EXERCISES_ID_SELECTION(), new String[]{"123456"}, null);

        assertNotNull(all);
        assertTrue(all.moveToFirst());
        ArrayList<Set> sets = new ArrayList<>();

        Exercise exerciseFromJoin = new Exercise(all);

        do {
            sets.add(new Set(all));
        } while (all.moveToNext());

        exerciseFromJoin.setSets(sets);

        assertEquals(sets, arrayList);
        assertEquals(exerciseFromJoin, exercise);
        setCursor.close();
    }

    @After
    public void doOnComplete() {
        contentResolver.delete(WODDBConstants.Exercise.Companion.getCONTENT_URI(),
                WODDBConstants.Exercise.Companion.getCOLUMN_EXERCISES_ID_SELECTION(), new String[]{"123456"});

        contentResolver.delete(WODDBConstants.Set.Companion.getCONTENT_URI(),
                WODDBConstants.Set.Companion.getCOLUMN_EXERCISES_ID_SELECTION(), new String[]{"123456"});
    }
}
