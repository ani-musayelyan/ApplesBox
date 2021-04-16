package com.example.applesbox

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Insets.subtract
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.Global
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_apple.*
import kotlinx.android.synthetic.main.fragment_apple.view.*
import kotlinx.coroutines.*
import kotlin.text.toInt as toInt1


class AppleFragment : Fragment() {

    var valueMax: Int? = null
    var valueInitial: Int? = null
    var resetValue: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_apple, container, false)
        valueMax = arguments?.getString("maxInput").toString().toInt1()
        valueInitial = arguments?.getString("initialInput").toString().toInt1()

        view.maxVal.text = valueMax.toString()
        view.initVal.text = valueInitial.toString()

        resetValue = arguments?.getString("initialInput").toString().toInt1()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val progressDialog = ProgressDialog(view.context)


        suspend fun addCount(): String {
            delay(500)
            if (valueInitial != valueMax)
                valueInitial = valueInitial?.plus(1)
            return valueInitial.toString()
        }

        suspend fun subtract(): String {
            if (valueInitial != 0)
                valueInitial = valueInitial?.minus(1)
            delay(500)
            return valueInitial.toString()
        }

        fun check() {
            if (valueInitial == 0 || valueInitial == valueMax) {
                view.reset.visibility = View.VISIBLE
            }
        }
        reset.setOnClickListener {
            view.reset.visibility = View.INVISIBLE
            valueInitial = resetValue
            view.initVal.text = resetValue.toString()
        }


        plus.setOnClickListener {
            view.reset.visibility = View.INVISIBLE
            GlobalScope.launch(Dispatchers.Main) {
                progressDialog.setTitle("Counting")
                progressDialog.setMessage("Loading...")
                progressDialog.show()

                val count = withContext(Dispatchers.Default) {
                    addCount()
                }
                view.initVal.text = count
                progressDialog.dismiss()
            }
            check()

        }

        minus.setOnClickListener {
            view.reset.visibility = View.INVISIBLE
            GlobalScope.launch(Dispatchers.Main) {
                progressDialog.setTitle("Counting")
                progressDialog.setMessage("Loading...")
                progressDialog.show()

                val count = withContext(Dispatchers.Default) {
                    subtract()
                }
                view.initVal.text = count
                progressDialog.dismiss()
            }
            check()

        }
    }
}
