package woddiary20.bazaleev.io.woddiary20.ui.addexercise

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_exercise.*
import kotlinx.android.synthetic.main.add_exercise_layout.*
import woddiary20.bazaleev.io.woddiary20.R
import woddiary20.bazaleev.io.woddiary20.WODDiaryApplication
import woddiary20.bazaleev.io.woddiary20.ui.base.BaseActivity

class AddExerciseActivity : BaseActivity<AddExercisePresenter, AddExerciseView>(), AddExerciseView {
    var addExercisePresenter: AddExercisePresenter? = null
//    var setsController: SetController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WODDiaryApplication.getAppComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        setUpActionBar()
        setUpControls()
        setUpDefaults()
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

    private fun addSet() {
//        val view = LayoutInflater.from(this).inflate(R.layout.item_set, sets_container, false)
//        view.tag = sets_container.childCount
//        view.ib_remove.setOnClickListener({})
//        sets_container.addView(view)
//        sets_container.requestLayout()
//        view.et_reps.requestFocus()
    }


    private fun setUpDefaults() {
        tv_amount.text = "5"
        tv_sets.text = "3"
        tv_reps.text = "8"

        sets_container.adapter = SetsAdapter(3, 8, 5.0)
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpControls() {
        addSet()

        ib_reps_minus.setOnClickListener {
            subtractNum(tv_reps)
        }

        ib_reps_plus.setOnClickListener {
            addNum(tv_reps)
        }

        ib_sets_minus.setOnClickListener {
            subtractNum(tv_sets)
        }

        ib_sets_plus.setOnClickListener {
            addNum(tv_sets)
        }


        sb_amount.max = 300
        sb_amount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_amount.text = String.format("%s kg", progress.toDouble() / 2.0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }

    private fun addNum(textView: TextView) {
        try {
            val num = Integer.parseInt(textView.text.toString())
            textView.text = String.format("%d", num + 1)
        } catch (e: NumberFormatException) {

        }
    }

    private fun subtractNum(textView: TextView) {
        try {
            val num = Integer.parseInt(textView.text.toString())
            if (num > 1)
                textView.text = String.format("%d", num - 1)
        } catch (e: NumberFormatException) {

        }
    }
}
