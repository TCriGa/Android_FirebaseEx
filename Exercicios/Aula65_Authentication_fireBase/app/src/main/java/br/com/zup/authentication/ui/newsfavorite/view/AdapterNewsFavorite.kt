package br.com.zup.authentication.ui.newsfavorite.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.databinding.NewsListItemBinding
import com.squareup.picasso.Picasso

class AdapterNewsFavorite(
    private var newsFavoriteList: MutableList<String>,
    private val onClickFavoriteDetail: (favorite: String) -> Unit

) : RecyclerView.Adapter<AdapterNewsFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteList = newsFavoriteList[position]
        holder.showFavoriteNews(favoriteList)
        holder.binding.textDetailNews.setOnClickListener {
            onClickFavoriteDetail(favoriteList)
        }
    }

    override fun getItemCount(): Int = newsFavoriteList.size

    fun updateMovieList(newList: MutableList<String>) {
        newsFavoriteList = newList
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showFavoriteNews(article:String) {
            binding.textTitleNews.text = article
        }
    }
}
