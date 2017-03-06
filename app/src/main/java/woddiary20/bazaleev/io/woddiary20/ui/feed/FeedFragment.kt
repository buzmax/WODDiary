package woddiary20.bazaleev.io.woddiary20.ui.feed

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.feed.*
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.WODDiaryApplication
import woddiary20.bazaleev.io.woddiary20.storage.model.Exercise
import woddiary20.bazaleev.io.woddiary20.ui.addexercise.AddExerciseActivity
import woddiary20.bazaleev.io.woddiary20.ui.base.BaseFragment

/**
 * Created by max on 11/21/16.
 */

class FeedFragment : BaseFragment<FeedPresenter, FeedView>(), FeedView {
    companion object

    var feedPresenter: FeedPresenter? = null
    var feedAdapter: FeedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WODDiaryApplication.getAppComponent(context).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater!!.inflate(R.layout.feed, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_exercises.layoutManager = LinearLayoutManager(context)
        fab_add.setOnClickListener {
            startActivity(Intent(context, AddExerciseActivity::class.java))
        }
    }

    override fun getPresenter(): FeedPresenter {
        return feedPresenter!!
    }

    override fun getPresentation(): FeedView {
        return this
    }

    override fun showExercisesList(exercises: List<Exercise>?) {
        tv_empty.visibility = View.GONE
        if (feedAdapter == null) {
            feedAdapter = FeedAdapter(exercises!!)
            rv_exercises.adapter = feedAdapter
        } else {
            feedAdapter!!.updateExercises(exercises!!)
        }
    }

    override fun showEmptyState() {
        tv_empty.visibility = View.VISIBLE
    }
}

fun newFeedInstance(): FeedFragment {
    return FeedFragment()
}
