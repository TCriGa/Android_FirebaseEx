package br.com.zup.authentication.presentation.news.fragmentview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.authentication.R
import br.com.zup.authentication.data.response.ArticleResponse
import br.com.zup.authentication.databinding.NewsListItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(
    private var listFavorite: MutableList<ArticleResponse>,
    private val onClick: (click: ArticleResponse) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNews = listFavorite[position]
        holder.showInformationNews(itemNews)
        holder.binding.cvNews.setOnClickListener {
            onClick(itemNews)
        }
    }

    fun updateMovieList(newList: MutableList<ArticleResponse>) {
        listFavorite = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listFavorite.size

    class ViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInformationNews(listArticle: ArticleResponse) {
                binding.textTitleNews.text = listArticle.title
                binding.textDetailNews.text = listArticle.description
                Picasso.get().load(listArticle.urlToImage)
                    .placeholder(R.drawable.imagem_indisponivel).into(binding.imageNews)

            }
        }
    }


