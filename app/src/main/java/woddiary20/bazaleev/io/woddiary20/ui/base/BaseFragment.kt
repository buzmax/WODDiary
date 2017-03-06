package woddiary20.bazaleev.io.woddiary20.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import woddiary20.bazaleev.io.woddiary20.R


/**
 * Created by Max Bazaleev on 2/27/16.
 */
abstract class BaseFragment<out P : BasePresenter<V>, V : IView> : Fragment(), IView {

    private val progressDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().attachView(getPresentation())
        if (savedInstanceState != null) {
            getPresenter().restoreState(savedInstanceState)
        }
    }

    override fun onStart() {
        super.onStart()
        getPresenter().registerListeners()
    }

    override fun onStop() {
        super.onStop()
        getPresenter().unregisterListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter().detachView()
    }

    override fun showMessage(message: Int) {
        AlertDialog.Builder(context).setTitle(R.string.error).setMessage(message).show()
    }

    override fun showMessage(message: String) {
        AlertDialog.Builder(context).setTitle(R.string.error).setMessage(message).show()
    }

    override fun showMessage(message: Int, listener: DialogInterface.OnClickListener) {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showProgress(@StringRes header: Int) {

    }

    override fun isProgressShowing(): Boolean {
        return false
    }

    abstract fun getPresenter(): P

    //not the best name for this method, but getView already taken by Fragment
    abstract fun getPresentation(): V
}
