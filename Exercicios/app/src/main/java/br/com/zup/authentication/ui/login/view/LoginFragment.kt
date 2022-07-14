package br.com.zup.authentication.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.authentication.R
import br.com.zup.authentication.databinding.FragmentLoginBinding
import br.com.zup.authentication.domain.model.User

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun getDataUser(): User {
        return User(
            email = binding.editEmailLogin.text.toString(),
            password = binding.editPassword.text.toString()
        )
    }


}