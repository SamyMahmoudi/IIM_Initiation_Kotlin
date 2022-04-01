package fr.iim.kotliniim

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class HomeFragment : Fragment() {

    private lateinit var listener: HomeFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val varLoginMail = view.findViewById<EditText>(R.id.loginEmailText).extString()
            val varLoginPassword = view.findViewById<EditText>(R.id.loginPassword).extString()
            val varLoginCheckbox = view.findViewById<CheckBox>(R.id.loginCheckBox)

            if(varLoginMail.matches(Regex("^[a-zA-Z0-9.!#\$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$"))) {
                view.findViewById<TextView>(R.id.textErrEmail).text = ""

                if (varLoginPassword.matches(Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+\$).{4,}\$"))) {
                    view.findViewById<TextView>(R.id.textErrPassword).text = ""

                    if(varLoginCheckbox.isChecked) {
                      view.findViewById<TextView>(R.id.textErrCheckbox).text = ""
                      listener.loginListener(varLoginMail)

                    } else {
                      view.findViewById<TextView>(R.id.textErrCheckbox).text = "You have to accept the terms of use"
                    }

                } else {
                  view.findViewById<TextView>(R.id.textErrPassword).text = "Not valid password format at least : 8 characters, 1 letter | 1 number | 1 special character minimum"
                }

            } else {
                view.findViewById<TextView>(R.id.textErrEmail).text = "Not valid email format"
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is HomeFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement HomeFragment.HomeFragmentListener")
        }
    }

    interface HomeFragmentListener {
        fun loginListener(varLoginMail: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}