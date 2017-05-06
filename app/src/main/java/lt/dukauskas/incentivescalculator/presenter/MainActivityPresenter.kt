package lt.dukauskas.incentivescalculator.presenter

import android.widget.EditText
import android.widget.TextView
import lt.dukauskas.incentivescalculator.contracts.MainActivityContract

/**
 * Mindaugas Dukauskas mindaugas@dukauskas.lt
 * Created: 2/15/2017.
 */
class MainActivityPresenter constructor(v : MainActivityContract.View) {
    private var view:MainActivityContract.View = v

    fun calculateBonus(percentage_text: EditText, sph_text: EditText) {
        var sph: String = sph_text.text.toString()
        if (sph.isNullOrEmpty()) sph = "0.0"

        var perc: String = percentage_text.text.toString()
        if (perc.isNullOrEmpty()) perc = "1.0"

        view.setNewResult(calculateSPH(sph.toFloat(), perc.toFloat()))
    }

    private fun calculateSPH(sph: Float, percentage: Float): String {
        val s = "£"
        val sign = s
        var calculatedSum = 0.0

        // Level 1 with 0.00 Pound
        var sphLeft = sph - (25f * percentage)
        if(sphLeft <= 0.0f)
            return generateAmountString(sign, calculatedSum)

        //Level 2 with 3.30 Pound
        var barrier = 9f * percentage
        if(sphLeft < barrier){
            calculatedSum += sphLeft * 3.30f
            return generateAmountString(sign, calculatedSum)
        }else{
            sphLeft -= barrier
            calculatedSum += barrier * 3.30f
        }

        //Level 3 with 5.20 Pound
        barrier = 6f * percentage
        if(sphLeft < barrier){
            calculatedSum += sphLeft * 5.20f
            return generateAmountString(sign, calculatedSum)
        }else{
            sphLeft -= barrier
            calculatedSum += barrier * 5.20f
        }

        //Level 4 with 6.20 Pound
        barrier = 4f * percentage
        if(sphLeft < barrier){
            calculatedSum += sphLeft * 6.20f
            return generateAmountString(sign, calculatedSum)
        }else{
            sphLeft -= barrier
            calculatedSum += barrier * 6.20f
        }

        //Level 5 with 7.70 Pound
        if(sphLeft > 0.0) {
            calculatedSum += sphLeft * 7.70f
            return generateAmountString(sign, calculatedSum)
        }

        return generateAmountString(sign, calculatedSum)
    }

    private fun generateAmountString(sign: String, calculatedSum: Double) = sign + " " + String.format("%.2f", calculatedSum)
}
