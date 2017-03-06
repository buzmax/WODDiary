package woddiary20.bazaleev.io.woddiary20.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.WODDiaryApplication
import woddiary20.bazaleev.io.woddiary20.ui.base.BaseActivity
import woddiary20.bazaleev.io.woddiary20.ui.calendar.newCalendarInstance
import woddiary20.bazaleev.io.woddiary20.ui.calendar.newProgramInstance
import woddiary20.bazaleev.io.woddiary20.ui.feed.newFeedInstance

class MainActivity : BaseActivity<MainPresenter, MainView>(), MainView {
    var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WODDiaryApplication.getAppComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        bottom_navigation.setOnNavigationItemSelectedListener({
//            if (bottom_navigation.menu.findItem(it.itemId).isChecked)
//                return@setOnNavigationItemSelectedListener false
//            when (it.itemId) {
//                R.id.action_feed -> loadFeed()
//                R.id.action_calendar -> loadCalendar()
//                R.id.action_program_builder -> loadProgram()
//            }
//            true
//        })
    }

    override fun getPresenter(): MainPresenter {
        return mainPresenter!!
    }

    override fun getView(): MainView {
        return this
    }

    override fun loadFeed() {
        loadFragment(newFeedInstance())
    }

    override fun loadCalendar() {
        loadFragment(newCalendarInstance())
    }

    override fun loadProgram() {
        loadFragment(newProgramInstance())
    }

    private fun loadFragment(newInstance: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, newInstance, newInstance.javaClass.canonicalName).commit()
    }
}
