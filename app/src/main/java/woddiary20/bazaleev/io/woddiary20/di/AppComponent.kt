package woddiary20.bazaleev.io.woddiary20.di

import woddiary20.bazaleev.io.woddiary20.ui.addexercise.AddExerciseActivity
import woddiary20.bazaleev.io.woddiary20.ui.calendar.CalendarFragment
import woddiary20.bazaleev.io.woddiary20.ui.calendar.ProgramFragment
import woddiary20.bazaleev.io.woddiary20.ui.feed.FeedFragment
import woddiary20.bazaleev.io.woddiary20.ui.main.MainActivity

/**
 * Created by Max Bazaleev on 7/28/16.
 */
interface AppComponent {
    fun inject(activity: MainActivity)

    fun inject(feedFragment: FeedFragment)

    fun inject(calendarFragment: CalendarFragment)

    fun inject(programFragment: ProgramFragment)

    fun inject(addExerciseActivity: AddExerciseActivity)
}
