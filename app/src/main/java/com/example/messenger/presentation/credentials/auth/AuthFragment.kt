package com.example.messenger.presentation.credentials.auth

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.messenger.R
import com.example.messenger.presentation.credentials.ICredentialsActivity
import com.example.messenger.presentation.main.MainActivity
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment() {

//    abstract fun getViewId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegistration.setOnClickListener {
            activity.let {
                if (it is ICredentialsActivity) it.showRegistration()
            }
        }

        btnLogin.setOnClickListener {
            MainActivity.show()
        }

    }
}
