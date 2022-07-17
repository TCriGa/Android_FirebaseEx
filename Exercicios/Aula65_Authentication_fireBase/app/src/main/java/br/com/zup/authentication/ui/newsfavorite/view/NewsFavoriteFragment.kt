package br.com.zup.authentication.ui.newsfavorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.data.datasource.model.NewsGoogleResponse
import br.com.zup.authentication.databinding.FragmentNewsFavoriteBinding
import br.com.zup.authentication.ui.newsfavorite.viewmodel.NewsFavoriteViewModel


class NewsFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentNewsFavoriteBinding

    private val adapter: AdapterNewsFavorite by lazy {
        AdapterNewsFavorite(arrayListOf(), ::removeFavoriteNews)
    }

    private val viewModel: NewsFavoriteViewModel by lazy {
        ViewModelProvider(this)[NewsFavoriteViewModel:: class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListFavorite()
        setUpRecyclerView()
        initObserver()
    }

    private fun setUpRecyclerView() {
        binding.rvFavoriteNews.adapter = adapter
    }

    private fun initObserver(){
        viewModel.favoriteListState.observe(this.viewLifecycleOwner){
            adapter.updateMovieList(it.toMutableList())
        }

        viewModel.messageState.observe(this.viewLifecycleOwner){
            loadMessage(it)
        }
    }
    private fun loadMessage(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun removeFavoriteNews(remove : String) {
       viewModel.removeFavorite(remove)
    }

}