package br.com.zup.authentication.presentation.newsfavorite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.authentication.data.response.ArticleResponse
import br.com.zup.authentication.databinding.FavoriteItemBinding
import br.com.zup.authentication.databinding.NewsListItemBinding
import com.squareup.picasso.Picasso
import kotlin.reflect.KFunction0

class AdapterNewsFavorite(
    private var newsFavoriteList: MutableList<ArticleResponse>,
    private val onClickFavoriteDetail: KFunction0<Unit>

) : RecyclerView.Adapter<AdapterNewsFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteList = newsFavoriteList[position]
        holder.showFavoriteNews(favoriteList)
        holder.binding.cvFavoriteNews.setOnClickListener {
            onClickFavoriteDetail()
            notifyItemRemoved(position)
        }
    }


    override fun getItemCount(): Int = newsFavoriteList.size

    fun updateFavoriteList(newList: MutableList<ArticleResponse>) {
        newsFavoriteList = newList
        notifyDataSetChanged()

    }


    class ViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showFavoriteNews(article: ArticleResponse) {
            binding.textTitleFavoriteNews.text = article.title
            binding.textDetailFavoriteNews.text = article.description.length.toString()
            Picasso.get().load(article.urlToImage).into(binding.imageFavoriteNews)
        }
    }
}

