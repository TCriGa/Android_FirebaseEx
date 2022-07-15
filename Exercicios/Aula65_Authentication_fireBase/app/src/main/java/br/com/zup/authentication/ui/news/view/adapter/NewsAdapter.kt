package br.com.zup.authentication.ui.news.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.authentication.R
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.data.datasource.model.NewsGoogleResponse
import br.com.zup.authentication.databinding.NewsListItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(
    private var newsList: MutableList<Article>
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsList = newsList[position]
        holder.showInformationNews(newsList)
    }

    fun updateMovieList(newList: MutableList<Article>) {
        newsList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = newsList.size

    class ViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun showInformationNews(article: Article) {
            binding.textTitleNews.text = article.title
            binding.textDetailNews.text = article.description

            if (article.urlToImage?.isEmpty() == true) {
                Picasso.get().load(article.urlToImage).placeholder(R.drawable.imagem_indisponivel)
            } else{
                Picasso.get().load(article.urlToImage)
                    .into(binding.imageNews)


            }

        }
    }
}