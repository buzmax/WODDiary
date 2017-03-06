package woddiary20.bazaleev.io.woddiary20.ui.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import woddiary20.bazaleev.io.woddiary20.R


/**
 * Created by Max Bazaleev on 2/27/16.
 */
abstract class BaseActivity<out P : BasePresenter<V>, V : IView> : AppCompatActivity(), IView {

    abstract fun getPresenter(): P

    abstract fun getView(): V

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().attachView(getView())
        getPresenter().restoreState(savedInstanceState)
    }
    override fun onDestroy() {
        super.onDestroy()
        getPresenter().detachView()
    }

    override fun onStart() {
        super.onStart()
        getPresenter().registerListeners()
    }

    override fun onStop() {
        super.onStop()
        getPresenter().unregisterListeners()
    }

    override fun getContext(): Context {
        return this
    }

    override fun showMessage(message: Int) {
        AlertDialog.Builder(this).setTitle(R.string.error).setMessage(message).show()
    }

    override fun showMessage(message: String) {
        AlertDialog.Builder(this).setTitle(R.string.error).setMessage(message).show()
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
}
