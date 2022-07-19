package br.com.zup.authentication.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.authentication.R
import br.com.zup.authentication.databinding.FragmentLoginBinding
import br.com.zup.authentication.domain.model.User
import br.com.zup.authentication.ui.login.viewmodel.LoginViewModel
import br.com.zup.authentication.utillity.KEY_BUNDLE
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickRegister()
        clickButtonLogin()
        initObserve()
        showToken()
        showNotification()
    }

    private fun getDataUser(): User {
        return User(
            email = binding.editEmailLogin.text.toString(),
            password = binding.editPassword.text.toString()
        )
    }

    private fun clickRegister() {
        binding.tvRegisterNow.setOnClickListener {
            goToRegister()
        }
    }

    private fun clickButtonLogin() {
        binding.buttonLogin.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }
    }

    private fun goToRegister() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_registerFragment2)
    }

    private fun initObserve() {
        viewModel.loginState.observe(this.viewLifecycleOwner) {
            goToHome(it)
        }

        viewModel.errorState.observe(this.viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToHome(user: User) {
        val bundle = bundleOf(KEY_BUNDLE to user)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_newsFragment, bundle)
    }

    private fun showToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                binding.textToken.text = task.result

            }
        )
    }

    private fun showNotification() {

        val message = RemoteMessage(bundleOf())
        FirebaseMessaging.getInstance().setNotificationDelegationEnabled(true).addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                binding.textLastNotification.text = message.notification?.title.toString()
                handleMessage(message)
            })
    }
    private  fun  handleMessage (remoteMessage: RemoteMessage ) {
        //1
        val handler = Handler(Looper.getMainLooper())
        remoteMessage.notification?.let {
            val intent = Intent( "Meus Dados" )
            intent.putExtra( "message" , remoteMessage. data [ "texto" ]);
            val emissary = LocalBroadcastManager.getInstance( this.requireContext() )
            emissary?.sendBroadcast(intent)

            val bundle = intent.extras
            if (bundle != null ) {
               binding.textTextNotification.text = remoteMessage.notification!!.body
            }
        }

    }
}



