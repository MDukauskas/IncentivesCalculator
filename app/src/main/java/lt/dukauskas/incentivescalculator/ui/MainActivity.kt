package lt.dukauskas.incentivescalculator.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import lt.dukauskas.incentivescalculator.R
import lt.dukauskas.incentivescalculator.contracts.MainActivityContract
import lt.dukauskas.incentivescalculator.presenter.MainActivityPresenter
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(),MainActivityContract.View {

    var mPresenter:MainActivityPresenter? = null
    var sph_text:EditText? = null
    var percentage_text:EditText? = null
    var result_text:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = MainActivityPresenter(this)
        setContentView(getUI());

    }

    private fun getUI(): LinearLayout {
        return verticalLayout {
            padding = dip(32)
            textView(context.getString(R.string.sphHint))
            sph_text = editText {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = context.getString(R.string.sphHint)
            }
            textView(context.getString(R.string.percentageHint))
            percentage_text = editText("1.025") {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = context.getString(R.string.percentageHint)
            }

            result_text = textView {
                text = ""
                gravity = Gravity.CENTER_HORIZONTAL
                textSize = 50f

            }
            button("Calculate") {
                onClick {
                    (mPresenter as MainActivityPresenter).calculateBonus(percentage_text as EditText, sph_text as EditText)
                }
            }
        }
    }

    override fun setNewResult(bonus: String) {
        result_text?.setText(bonus)
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
