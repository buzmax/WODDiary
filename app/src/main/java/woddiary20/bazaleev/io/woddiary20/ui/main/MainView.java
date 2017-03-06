package woddiary20.bazaleev.io.woddiary20.ui.main;

import woddiary20.bazaleev.io.woddiary20.ui.base.IView;

/**
 * Created by max on 11/21/16.
 */

public interface MainView extends IView {
    void loadFeed();

    void loadCalendar();

    void loadProgram();
}
