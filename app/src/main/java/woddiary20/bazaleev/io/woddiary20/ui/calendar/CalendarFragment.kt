package woddiary20.bazaleev.io.woddiary20.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.WODDiaryApplication
import woddiary20.bazaleev.io.woddiary20.ui.base.BaseFragment

/**
 * Created by max on 11/22/16.
 */

class CalendarFragment : BaseFragment<CalendarPresenter, CalendarView>(), CalendarView {

    companion object

    var calendarPresenter: CalendarPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WODDiaryApplication.getAppComponent(context).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.calendar, container, false)
    }

    override fun getPresenter(): CalendarPresenter {
        return calendarPresenter!!
    }

    override fun getPresentation(): CalendarView {
        return this
    }
}

fun newCalendarInstance(): CalendarFragment {
    return CalendarFragment()
}


