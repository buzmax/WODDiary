package woddiary20.bazaleev.io.woddiary20.storage.observer

import android.content.ContentResolver
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.os.Looper
import rx.Observable
import rx.Subscriber
import rx.subscriptions.Subscriptions

/**
 * Created by Max Bazaleev on 7/28/16.
 */
class RXContentObserver<T>(private val contentResolver: ContentResolver,
                           private val uri: Uri,
                           private val projection: Array<String>?,
                           private val selection: String?,
                           private val selectionArgs: Array<String>?,
                           private val sortOrder: String?,
                           private val transformer: TransformCursor<T>) : Observable.OnSubscribe<T> {

    override fun call(subscriber: Subscriber<in T>?) {
        val observer = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean) {
                onChange(selfChange, null)
            }

            override fun onChange(selfChange: Boolean, uri: Uri?) {
                val result = queryAndTransform()
                if (subscriber != null && !subscriber.isUnsubscribed) {
                    subscriber.onNext(result)
                }
            }
        }

        contentResolver.registerContentObserver(uri, true, observer)

        val result = queryAndTransform()
        subscriber!!.onNext(result)
        subscriber.add(Subscriptions.create { contentResolver.unregisterContentObserver(observer) })
    }

    private fun queryAndTransform(): T? {
        var result: T? = null
        val cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = transformer.transform(cursor)
            }
            cursor.close()
        }
        return result
    }

    interface TransformCursor<out T> {
        fun transform(cursor: Cursor): T
    }
}