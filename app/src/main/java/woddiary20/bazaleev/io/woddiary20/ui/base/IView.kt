package woddiary20.bazaleev.io.woddiary20.ui.base

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.StringRes

/**
 * Created by Max Bazaleev on 2/27/16.
 */
interface IView {
    fun getContext(): Context

    fun showMessage(message: Int)

    fun showMessage(message: String)

    fun showMessage(message: Int, listener: DialogInterface.OnClickListener)

    fun showProgress()

    fun hideProgress()

    fun showProgress(@StringRes header: Int)

    fun isProgressShowing(): Boolean
}
