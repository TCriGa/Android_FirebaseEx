package br.com.zup.authentication.presentation.newsfavorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.zup.authentication.data.response.ArticleResponse
import br.com.zup.authentication.databinding.FragmentNewsFavoriteBinding
import br.com.zup.authentication.presentation.newsfavorite.view.adapter.AdapterNewsFavorite
import br.com.zup.authentication.presentation.newsfavorite.viewmodel.NewsFavoriteViewModel


class NewsFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentNewsFavoriteBinding

    private val adapter: AdapterNewsFavorite by lazy {
        AdapterNewsFavorite(arrayListOf(), :: removeFavoriteNews)
    }

    private val viewModel: NewsFavoriteViewModel by lazy {
        ViewModelProvider(this)[NewsFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListFavorite()
        setUpRecyclerView()
        initObserver()
    }

    private fun setUpRecyclerView() {
        binding.rvFavoriteNews.adapter = adapter
    }

    private fun initObserver() {
        viewModel.favoriteListState.observe(this.viewLifecycleOwner) {
           adapter.updateFavoriteList(it as MutableList<ArticleResponse>)


        }

        viewModel.messageState.observe(this.viewLifecycleOwner) {
            loadMessage(it)
        }
    }

    private fun loadMessage(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    private fun removeFavoriteNews() {
      viewModel.removeFavorite()
    }

}