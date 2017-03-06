package woddiary20.bazaleev.io.woddiary20.di

import android.app.Application
import android.content.Context

import woddiary20.bazaleev.io.woddiary20.storage.Storage
import woddiary20.bazaleev.io.woddiary20.storage.cntntprvdr.Storage_CntntPrvdrImpl
import woddiary20.bazaleev.io.woddiary20.ui.addexercise.AddExercisePresenter
import woddiary20.bazaleev.io.woddiary20.ui.calendar.CalendarPresenter
import woddiary20.bazaleev.io.woddiary20.ui.calendar.ProgramPresenter
import woddiary20.bazaleev.io.woddiary20.ui.feed.FeedPresenter
import woddiary20.bazaleev.io.woddiary20.ui.main.MainPresenter

/**
 * Created by Max Bazaleev on 7/28/16.
 */
class AppModuleImpl(private val applicationContext: Context) : AppModule {
    init {
        if (applicationContext !is Application) {
            throw IllegalArgumentException("Wrong type of context")
        }
    }

    override fun provideStorage(): Storage = Storage_CntntPrvdrImpl(applicationContext.contentResolver)

    override fun provideMainPresenter(): MainPresenter = MainPresenter(provideStorage())

    override fun provideFeedPresenter(): FeedPresenter = FeedPresenter(provideStorage())

    override fun provideCalendarPresenter(): CalendarPresenter? = CalendarPresenter()

    override fun provideProgramPresenter(): ProgramPresenter? = ProgramPresenter()

    override fun provideAddExercisePresenter(): AddExercisePresenter = AddExercisePresenter(provideStorage())
}
