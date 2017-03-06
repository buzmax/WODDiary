package woddiary20.bazaleev.io.woddiary20.ui.base

import android.os.Bundle
import rx.subscriptions.CompositeSubscription

import java.lang.ref.WeakReference

/**
 * Created by Max Bazaleev on 2/27/16.
 */
open class BasePresenter<T : IView> : IPresenter<T> {

    val subscriptions: CompositeSubscription = CompositeSubscription()

    protected var uiView: WeakReference<T>? = null

    override fun attachView(component: T) {
        this.uiView = WeakReference(component)
    }

    override fun detachView() {
        if (uiView!!.get() != null) {
            uiView!!.clear()
        }
        uiView = null
    }

    override fun registerListeners() {
        
    }

    override fun unregisterListeners() {
        if (subscriptions.hasSubscriptions() && !subscriptions.isUnsubscribed)
            subscriptions.clear()
    }

    override fun restoreState(savedInstanceState: Bundle?) {

    }

    override fun saveState(outState: Bundle?) {

    }

    override fun onViewCreated(view: T) {

    }

    val isViewAttached: Boolean
        get() = uiView != null && uiView!!.get() != null


    val view: T
        get() {
            if (isViewAttached)
                return uiView!!.get()
            else
                throw NullPointerException()
        }

    interface CallView<T> {
        fun call(view: T)
    }
}
