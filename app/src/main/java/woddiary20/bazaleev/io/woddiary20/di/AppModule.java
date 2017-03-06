package woddiary20.bazaleev.io.woddiary20.di;

import woddiary20.bazaleev.io.woddiary20.storage.Storage;
import woddiary20.bazaleev.io.woddiary20.ui.addexercise.AddExercisePresenter;
import woddiary20.bazaleev.io.woddiary20.ui.calendar.CalendarPresenter;
import woddiary20.bazaleev.io.woddiary20.ui.calendar.ProgramPresenter;
import woddiary20.bazaleev.io.woddiary20.ui.feed.FeedPresenter;
import woddiary20.bazaleev.io.woddiary20.ui.main.MainPresenter;

/**
 * Created by Max Bazaleev on 7/28/16.
 */
public interface AppModule {
    Storage provideStorage();
    MainPresenter provideMainPresenter();
    FeedPresenter provideFeedPresenter();
    CalendarPresenter provideCalendarPresenter();
    ProgramPresenter provideProgramPresenter();
    AddExercisePresenter provideAddExercisePresenter();
}
