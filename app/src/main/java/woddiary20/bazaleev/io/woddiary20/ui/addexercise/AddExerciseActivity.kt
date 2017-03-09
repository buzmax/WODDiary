package woddiary20.bazaleev.io.woddiary20.ui.addexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add_exercise.*
import kotlinx.android.synthetic.main.add_exercise_layout.*
import kotlinx.android.synthetic.main.item_set.view.*
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.WODDiaryApplication
import woddiary20.bazaleev.io.woddiary20.ui.base.BaseActivity

class AddExerciseActivity : BaseActivity<AddExercisePresenter, AddExerciseView>(), AddExerciseView {
    var addExercisePresenter: AddExercisePresenter? = null
    var setsController: SetController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WODDiaryApplication.getAppComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        setUpActionBar()
        setUpSets()
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpSets() {
        setsController = SetController(sets_container)
        addSet()
//        setsController!!.addSet()
        ib_plus.setOnClickListener {
            addSet()
            //            setsController!!.addSet()
            scroll_container.post {
                scroll_container.scrollTo(0, scroll_container.bottom)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_exercise, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_save -> {
                addExercisePresenter!!.saveExercise()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setDate(date: String) {
        tv_date.text = date
    }

//    override fun getSetsList(): List<Set>? = setsController!!.getSetsList()

    override fun getName(): String = et_name.text.toString()

    override fun getPresenter(): AddExercisePresenter = addExercisePresenter!!

    override fun getView(): AddExerciseView = this

    override fun onExerciseSaved() = onBackPressed()

    fun addSet() {
        val view = LayoutInflater.from(this).inflate(R.layout.item_set, sets_container, false)
        view.tag = sets_container.childCount
        view.ib_remove.setOnClickListener({})
        sets_container.addView(view)
        sets_container.requestLayout()
        view.et_reps.requestFocus()
    }
}
