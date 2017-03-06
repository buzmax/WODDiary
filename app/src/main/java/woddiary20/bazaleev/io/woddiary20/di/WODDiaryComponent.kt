package woddiary20.bazaleev.io.woddiary20.di

import woddiary20.bazaleev.io.woddiary20.ui.addexercise.AddExerciseActivity
import woddiary20.bazaleev.io.woddiary20.ui.calendar.CalendarFragment
import woddiary20.bazaleev.io.woddiary20.ui.calendar.ProgramFragment
import woddiary20.bazaleev.io.woddiary20.ui.feed.FeedFragment
import woddiary20.bazaleev.io.woddiary20.ui.main.MainActivity

/**
 * Created by Max Bazaleev on 7/28/16.
 */
class WODDiaryComponent(private val appModule: AppModule) : AppComponent {

    override fun inject(addExerciseActivity: AddExerciseActivity) {
        addExerciseActivity.addExercisePresenter = appModule.provideAddExercisePresenter()
    }

    override fun inject(calendarFragment: CalendarFragment) {
        calendarFragment.calendarPresenter = appModule.provideCalendarPresenter()
    }

    override fun inject(programFragment: ProgramFragment) {
        programFragment.programPresenter = appModule.provideProgramPresenter()
    }

    override fun inject(activity: MainActivity) {
        activity.mainPresenter = appModule.provideMainPresenter()
    }

    override fun inject(feedFragment: FeedFragment) {
        feedFragment.feedPresenter = appModule.provideFeedPresenter()
    }
}
