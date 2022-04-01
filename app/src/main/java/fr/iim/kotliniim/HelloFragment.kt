package fr.iim.kotliniim

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class HelloFragment : Fragment() {
    private var varLoginMail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            varLoginMail = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hello, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.fragHelloTxtView).text = getString(R.string.hello, varLoginMail)

        val buttonCity = view.findViewById<Button>(R.id.buttonValidCity)
        buttonCity.setOnClickListener {
            val cityValue = view.findViewById<EditText>(R.id.editTextCity).extString()
            val intent = Intent(context, MapsActivity::class.java).putExtra("City", cityValue)
            startActivity(intent)
        }
    }

    companion object {
        private const val ARG_EMAIL = "varLoginMail"

        @JvmStatic
        fun newInstance(varLoginMail: String) =
            HelloFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, varLoginMail)
                }
            }
    }
}