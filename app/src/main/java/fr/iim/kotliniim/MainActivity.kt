package fr.iim.kotliniim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity(), HomeFragment.HomeFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, HomeFragment.newInstance())
            .commitNow()
    }

    override fun loginListener(varLoginMail: String) {
        Log.d(LOG_TAG, "click event sent $varLoginMail")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, HelloFragment.newInstance(varLoginMail))
            .commitNow()
    }

    companion object {
        private const val LOG_TAG = "MainActivity"
    }
}