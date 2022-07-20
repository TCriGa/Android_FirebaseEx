package br.com.zup.authentication.presentation.notification.fragmentview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.zup.authentication.R
import br.com.zup.authentication.databinding.FragmentNotificationBinding
import br.com.zup.authentication.presentation.login.viewmodel.LoginViewModel
import br.com.zup.authentication.presentation.notification.viewmodel.NotificationViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    private val viewModel: NotificationViewModel by lazy {
        ViewModelProvider(this)[NotificationViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        showToken()
    }

    private fun initObserver() {
        viewModel.notificationState.observe(this.viewLifecycleOwner) {
            binding.textTextNotification.text = it.title
            binding.textLastNotification.text = it.body

        }
        viewModel.currentToken.observe(viewLifecycleOwner) {
            binding.textToken.text = it
        }
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
}