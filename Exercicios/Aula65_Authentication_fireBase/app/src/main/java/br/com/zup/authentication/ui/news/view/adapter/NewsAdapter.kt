package br.com.zup.authentication.ui.news.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.authentication.R
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.databinding.NewsListItemBinding
import com.squareup.picasso.Picasso
import kotlin.reflect.KFunction0

class NewsAdapter(
    private var listFavorite: MutableList<Article>,
    private val onClick:(detail :String) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsList = listFavorite[position]
        holder.showInformationNews(newsList)
        holder.binding.textDetailNews.setOnClickListener {
            onClick
        }
    }

    fun updateMovieList(newList: MutableList<Article>) {
        listFavorite = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listFavorite.size

    class ViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInformationNews(article: Article) {
            binding.textTitleNews.text = article.title
            binding.textDetailNews.text = article.description
            Picasso.get().load(article.urlToImage).placeholder(R.drawable.imagem_indisponivel)
                .into(binding.imageNews)

        }

    }
}
