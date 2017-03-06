package woddiary20.bazaleev.io.woddiary20.ui.base;

import android.os.Bundle;

/**
 * Created by Max Bazaleev on 3/2/16.
 */
public interface IPresenter<V extends IView> {
    void attachView(V view);

    void onViewCreated(V view);

    void detachView();

    void registerListeners();

    void unregisterListeners();

    void restoreState(Bundle savedInstanceState);

    void saveState(Bundle outState);
}