package woddiary20.bazaleev.io.woddiary20.ui.main

import woddiary20.bazaleev.io.woddiary20.storage.Storage
import woddiary20.bazaleev.io.woddiary20.ui.base.BasePresenter

/**
 * Created by Max Bazaleev on 7/28/16.
 */
class MainPresenter(var storage: Storage) : BasePresenter<MainView>() {

    override fun attachView(component: MainView) {
        super.attachView(component)
        view.loadFeed()
    }
}
