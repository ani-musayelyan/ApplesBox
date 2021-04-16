package com.example.applesbox

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit()
    }

        override fun passData(maxInput: String, initialInput: String) {
        val bundle = Bundle()
        bundle.putString("maxInput" , maxInput)
        bundle.putString("initialInput" , initialInput)

        val transaction = this.supportFragmentManager.beginTransaction()
        val appleFragment = AppleFragment()
        appleFragment.arguments = bundle
        transaction.replace(R.id.container , appleFragment)
        transaction.commit()
    }



}

