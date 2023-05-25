package com.khaledamin.mawsoaa.utils

import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.khaledamin.mawsoaa.R
import java.util.Locale

class DisplayManager {
    companion object {
        fun showConfirmationDialog(
            context: Context,
            message: Int,
            positiveWord: Int,
            negativeWord: Int,
            callback: DialogInterface.OnClickListener,
        ) {
            val alertDialog = AlertDialog.Builder(context).setMessage(message).setCancelable(false)
                .setPositiveButton(positiveWord, callback)
                .setNegativeButton(negativeWord, null)
                .create()
            alertDialog.show()
        }

        fun setLocale(context: Context, lang: String) {
            val locale = Locale(lang)
            Locale.setDefault(locale)
            val resources = context.resources
            val displayMetrics = resources.displayMetrics
            val configuration = resources.configuration
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, displayMetrics)
        }

        fun removeErrorWhenEditing(vararg textInputs: TextInputLayout) {
            for (textInput in textInputs) {
                textInput.editText!!.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int,
                    ) {
//                        TODO("Not yet implemented")
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int,
                    ) {
                        textInput.isErrorEnabled = false
                    }

                    override fun afterTextChanged(s: Editable?) {
//                        TODO("Not yet implemented")
                    }

                })
            }
        }

        fun setBackgroundByCategory(context: Context,title: String): Int {
            val map: Map<String, Int> = mutableMapOf(
                context.getString(R.string.animals) to R.drawable.animals,
                context.getString(R.string.birds) to R.drawable.birds,
                context.getString(R.string.diseases) to R.drawable.diseases,
                context.getString(R.string.fish) to R.drawable.fish,
                context.getString(R.string.flowers) to R.drawable.flowers,
                context.getString(R.string.fruits) to R.drawable.fruits,
                context.getString(R.string.gems) to R.drawable.gems,
                context.getString(R.string.islamics) to R.drawable.islamics,
                context.getString(R.string.proverbs) to R.drawable.proverbs,
                context.getString(R.string.vegetables) to R.drawable.vegetables,
                context.getString(R.string.famous) to R.drawable.famous,
                context.getString(R.string.astronomy) to R.drawable.astronomy,
                context.getString(R.string.medicine) to R.drawable.medicine,
                context.getString(R.string.computer) to R.drawable.computer,
                context.getString(R.string.monuments) to R.drawable.monuments,
                context.getString(R.string.disasters) to R.drawable.disasters,
                context.getString(R.string.desserts) to R.drawable.desserts
            )
            return map[title]!!
        }

        fun startMusicByCategory(context: Context,title: String): Int {
            val map: Map<String, Int> = mutableMapOf(
                context.getString(R.string.animals) to R.raw.animals,
                context.getString(R.string.birds) to R.raw.birds,
                context.getString(R.string.diseases) to R.raw.diseases,
                context.getString(R.string.fish) to R.raw.fish,
                context.getString(R.string.flowers) to R.raw.flowers,
                context.getString(R.string.fruits) to R.raw.fruits,
                context.getString(R.string.gems) to R.raw.gems,
                context.getString(R.string.islamics) to R.raw.islamics,
                context.getString(R.string.proverbs) to R.raw.proverbs,
                context.getString(R.string.vegetables) to R.raw.vegetables,
                context.getString(R.string.famous) to R.raw.famous,
                context.getString(R.string.astronomy) to R.raw.astronomy,
                context.getString(R.string.medicine) to R.raw.anatomy,
                context.getString(R.string.computer) to R.raw.computer,
                context.getString(R.string.monuments) to R.raw.monuments,
                context.getString(R.string.disasters) to R.raw.disasters,
                context.getString(R.string.desserts) to R.raw.desserts
            )
            return map[title]!!
        }


    }
}