package com.example.applesbox

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.lang.Integer.*

class MainFragment : Fragment() {

    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main , container , false)
        communicator = activity as Communicator

        view.start.setOnClickListener {
            if (max.text.isNotEmpty() && initial.text.isNotEmpty()) {
                communicator.passData(view.max.text.toString() , view.initial.text.toString())

            } else {
                Toast.makeText(context , "Input your count" , Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }
}
