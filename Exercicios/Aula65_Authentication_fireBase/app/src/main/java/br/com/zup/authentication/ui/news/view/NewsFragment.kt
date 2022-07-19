package br.com.zup.authentication.ui.news.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.authentication.R
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.data.datasource.model.MyFirebaseMessagingService
import br.com.zup.authentication.databinding.FragmentNewsBinding
import br.com.zup.authentication.ui.news.view.adapter.NewsAdapter
import br.com.zup.authentication.ui.news.viewmodel.NewsViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this)[NewsViewModel::class.java]
    }

    private val adapter: NewsAdapter by lazy {
        NewsAdapter(arrayListOf(), ::favoriteNews)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllInformation()
        setUpRecyclerView()
        showUserData()

        initObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {
                viewModel.logout()
                goToLogin()
                true
            }
            R.id.favoritos -> {
                goToNewsFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showUserData() {
        val name = viewModel.getUserName()
        val email = viewModel.getUserEmail()
        binding.textUseName.text = "$name - $email"
    }

    private fun initObserver() {
        viewModel.newsResponse.observe(this.viewLifecycleOwner) {
            adapter.updateMovieList(it.articles)
        }
        viewModel.message.observe(this.viewLifecycleOwner) {
            loadErrorMessage(it)
        }
        viewModel.loading.observe(this.viewLifecycleOwner) {
            binding.pbLoading.isVisible = it == true
        }
    }

    private fun loadErrorMessage(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun goToLogin() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_newsFragment_to_loginFragment)
    }

    private fun favoriteNews(article: Article) {
        viewModel.saveNewsFavorite(article)

    }


    private fun goToNewsFavorite() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_newsFragment_to_newsFavoriteFragment)
    }

    private fun setUpRecyclerView() {
        binding.rvNews.adapter = adapter
    }


}