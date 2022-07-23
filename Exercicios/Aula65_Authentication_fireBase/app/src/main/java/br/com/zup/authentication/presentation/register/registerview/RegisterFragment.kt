package br.com.zup.authentication.presentation.register.registerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.authentication.R
import br.com.zup.authentication.databinding.FragmentRegisterBinding
import br.com.zup.authentication.domain.model.User
import br.com.zup.authentication.presentation.register.viewmodel.RegisterViewModel
import br.com.zup.authentication.utillity.KEY_BUNDLE
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButtonRegister()
        initObserve()
    }

    private fun clickButtonRegister() {
        binding.buttonRegister.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }
    }

    private fun getDataUser(): User {
        return User(
            binding.editNameRegister.text.toString(),
            binding.editEmailRegister.text.toString(),
            binding.editPasswordRegister.text.toString()
        )
    }

    private fun initObserve() {
        viewModel.registerState.observe(this.viewLifecycleOwner) {
            goToNews(it)
        }

        viewModel.errorState.observe(this.viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun goToNews(user: User) {
        val bundle = bundleOf(KEY_BUNDLE to user)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_newsFragment, bundle)
    }
}