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

class ProgramFragment : BaseFragment<ProgramPresenter, ProgramView>(), ProgramView {

    companion object

    var programPresenter: ProgramPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WODDiaryApplication.getAppComponent(context).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.program, container, false)
    }

    override fun getPresenter(): ProgramPresenter {
        return programPresenter!!
    }

    override fun getPresentation(): ProgramView {
        return this
    }
}

fun newProgramInstance(): ProgramFragment {
    return ProgramFragment()
}


